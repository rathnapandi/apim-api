package com.axway.apimcassandra.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class APIMPasswordEncoder implements PasswordEncoder {

    private  boolean verifySaltedPassword(char[] plaintext, char[] digest)
        throws GeneralSecurityException {
        String[] split = String.valueOf(digest).split("\\$");
        if (split.length == 3) { // only salt + password
            System.out.println("only salt + password");
            //return verifySaltedPasswordLegacy(plaintext, digest);
        }
        if (split.length != 4) { // version + salt + password
            throw new GeneralSecurityException("unsupported password digest");
        }
        String version = split[1];
        String salt = split[2];
        String storedPwd = split[3];
        byte[] versionBytes = Base64.getDecoder().decode(version);
        if (versionBytes.length != 9) { // iterations + keylength + alg
            throw new GeneralSecurityException("unsupported password digest");
        }
        byte[] storedBytes = Base64.getDecoder().decode(storedPwd);
        int iterations = (versionBytes[0] & 0xFF) << 24
            | (versionBytes[1] & 0xFF) << 16
            | (versionBytes[2] & 0xFF) << 8
            | (versionBytes[3] & 0xFF); // ITERATIONS
        int keylength = (versionBytes[4] & 0xFF) << 24
            | (versionBytes[5] & 0xFF) << 16
            | (versionBytes[6] & 0xFF) << 8
            | (versionBytes[7] & 0xFF); // KEYLENGTH_BITS
        //  byte alg = versionBytes[8]; // ALG_VER
        byte[] providedBytes = getPasswordHash(plaintext, Base64.getDecoder().decode(salt), iterations, keylength);
        return Arrays.equals(providedBytes, storedBytes);
    }

    public   byte[] getPasswordHash(char[] password, byte[] salt, int iterations, int keylength)
        throws GeneralSecurityException {
        try {
            SecretKey sk = generatePBKDF2KeySHA256(password, salt, iterations, keylength);
            return sk.getEncoded();
        } catch (Exception e) {
            throw new GeneralSecurityException(e);
        }
    }

    public  SecretKey generatePBKDF2KeySHA256(char[] password, byte[] salt, int iterations, int keyLengthInBits) throws GeneralSecurityException {
        try {
            KeySpec spec = new PBEKeySpec(password, salt, iterations, keyLengthInBits);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return f.generateSecret(spec);
        } catch (NoSuchAlgorithmException | NullPointerException | IllegalArgumentException ex) {
            throw new GeneralSecurityException(ex);
        }
    }
    @Override
    public String encode(CharSequence charSequence) {
        return null;
    }

    @Override
    public boolean matches(CharSequence userPassword, String encodedPassword) {
        if (userPassword == null) {
            throw new IllegalArgumentException("userPassword cannot be null");
        }
        if (encodedPassword == null || encodedPassword.length() == 0) {
            //this.logger.warn("Empty encoded password");
            return false;
        }

        try {
            char[] password = new String(Base64.getMimeDecoder().decode(encodedPassword)).toCharArray();
            return verifySaltedPassword(userPassword.toString().toCharArray(), password);
        } catch (GeneralSecurityException e) {
            return false;
        }
    }
}
