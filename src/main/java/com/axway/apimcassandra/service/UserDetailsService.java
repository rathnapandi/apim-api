package com.axway.apimcassandra.service;

import com.axway.apimcassandra.ProxyController;
import com.axway.apimcassandra.model.UserEntity;
import com.axway.apimcassandra.model.UserRole;
import com.axway.apimcassandra.repo.UserRepository;
import com.axway.apimcassandra.repo.UserRoleRepository;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private  final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    public UserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository){
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

//    private Base64.Encoder encoder = Base64.getEncoder();
//
//    private  boolean verifySaltedPassword(char[] plaintext, char[] digest)
//            throws  GeneralSecurityException {
//        String[] split = String.valueOf(digest).split("\\$");
//        if (split.length == 3) { // only salt + password
//            System.out.println("only salt + password");
//            //return verifySaltedPasswordLegacy(plaintext, digest);
//        }
//        if (split.length != 4) { // version + salt + password
//            throw new GeneralSecurityException("unsupported password digest");
//        }
//        String version = split[1];
//        String salt = split[2];
//        String storedPwd = split[3];
//        byte[] versionBytes = Base64.getDecoder().decode(version);
//        if (versionBytes.length != 9) { // iterations + keylength + alg
//            throw new GeneralSecurityException("unsupported password digest");
//        }
//        byte[] storedBytes = Base64.getDecoder().decode(storedPwd);
//        int iterations = (versionBytes[0] & 0xFF) << 24
//                | (versionBytes[1] & 0xFF) << 16
//                | (versionBytes[2] & 0xFF) << 8
//                | (versionBytes[3] & 0xFF); // ITERATIONS
//        int keylength = (versionBytes[4] & 0xFF) << 24
//                | (versionBytes[5] & 0xFF) << 16
//                | (versionBytes[6] & 0xFF) << 8
//                | (versionBytes[7] & 0xFF); // KEYLENGTH_BITS
//      //  byte alg = versionBytes[8]; // ALG_VER
//        byte[] providedBytes = getPasswordHash(plaintext, Base64.getDecoder().decode(salt), iterations, keylength);
//        return Arrays.equals(providedBytes, storedBytes);
//    }

//
//    private  byte[] getPasswordHash(char[] password, byte[] salt, int iterations, int keylength)
//            throws GeneralSecurityException {
//        try {
//            SecretKey sk = generatePBKDF2KeySHA256(password, salt, iterations, keylength);
//            return sk.getEncoded();
//        } catch (Exception e) {
//            throw new GeneralSecurityException(e);
//        }
//    }


//    public  SecretKey generatePBKDF2KeySHA256(char[] password, byte[] salt, int iterations, int keyLengthInBits) throws GeneralSecurityException {
//        try {
//            KeySpec spec = new PBEKeySpec(password, salt, iterations, keyLengthInBits);
//            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//            return f.generateSecret(spec);
//        } catch (NoSuchAlgorithmException | NullPointerException | IllegalArgumentException ex) {
//            throw new GeneralSecurityException(ex);
//        }
//    }


//    public static void main(String args[]) throws GeneralSecurityException, IOException {
//        String cassandraPassword = "JEFBR1FBQUFBQVFBQyQyZmNiQlRWVmpteUJIYUNhV3JVOE1nPT0kSFJ3eWh0U2svM2RSTDhPUXM0\nbzBzbFRTWU5sRm41S2dRSHdhQnZXd2YzYz0=";
//        char[] password = "changeme".toCharArray();
//        UserDetailsService userDetailsService = new UserDetailsService();
//        System.out.println(userDetailsService.verifySaltedPassword( "changeme".toCharArray(), new String(Base64.getMimeDecoder().decode(cassandraPassword)).toCharArray()));
//    }



    public static void main(String args[])  {
        String cassandraPassword = "JEFBR1FBQUFBQVFBQyQyZmNiQlRWVmpteUJIYUNhV3JVOE1nPT0kSFJ3eWh0U2svM2RSTDhPUXM0\nbzBzbFRTWU5sRm41S2dRSHdhQnZXd2YzYz0=";
        String password2=          "JEFBR1FBQUFBQVFBQyQyZmNiQlRWVmpteUJIYUNhV3JVOE1nPT0kSFJ3eWh0U2svM2RSTDhPUXM0\\nbzBzbFRTWU5sRm41S2dRSHdhQnZXd2YzYz0=".replaceFirst("\\\\n", "");
        System.out.println(cassandraPassword);
        System.out.println(password2);

        System.out.println(new String(Base64.getMimeDecoder().decode(cassandraPassword)));
        System.out.println(new String(Base64.getMimeDecoder().decode(password2.getBytes(StandardCharsets.UTF_8))));

    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("Authenticating user : {}", username);
        String quotedUsername = "\"" + username + "\"";
        UserEntity userEntity = userRepository.findByLoginName(quotedUsername);

        logger.info("Found user {} from Auth repository : {}", username, userEntity.getUserDn());

        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        ;
        String password = userEntity.getUserPassword().substring(1, userEntity.getUserPassword().length()-1 ).replaceFirst("\\\\n", "");

        return new org.springframework.security.core.userdetails.User(username, password, buildUserAuthority(userEntity));
    }

    private List<GrantedAuthority> buildUserAuthority(UserEntity userEntity) {
        logger.info("Build Authority");
        String userDn = userEntity.getUserDn();
        UserRole userRole = userRoleRepository.findByUserDn(userDn);
        logger.info("Role {} associated with user :{}", userRole.getRole(), userEntity.getLoginName());
        Set<GrantedAuthority> setAuths = new HashSet<>();
        setAuths.add(new SimpleGrantedAuthority(CqlIdentifier.fromCql(userRole.getRole()).asInternal()));
        List<GrantedAuthority> Result = new ArrayList<>(setAuths);
        return Result;
    }
}
