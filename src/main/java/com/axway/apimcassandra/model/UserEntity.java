package com.axway.apimcassandra.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "api_portal_portaluserstoreldap")
public class UserEntity {

    @PrimaryKey
    private String key;
    private String cn;
    @Column(value="createTimestamp", forceQuote = true)
    private String createTimestamp;
    private String description;
    private String id;
    @Column(value="jpegPhoto", forceQuote = true)
    private String jpegPhoto;
    @Column(value="loginName", forceQuote = true)
    private String loginName;
    private String mail;
    private String mobile;
    private String o;
    private String sn;
    @Column(value="telephoneNumber", forceQuote = true)
    private String telephoneNumber;
    @Column(value="userDn", forceQuote = true)
    private String userDn;
    @Column(value="userPassword", forceQuote = true)
    private String userPassword;
    @Column(value="userPasswordType", forceQuote = true)
    private String userPasswordType;

    public void setKey(String key) {
        this.key = key;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJpegPhoto(String jpegPhoto) {
        this.jpegPhoto = jpegPhoto;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setO(String o) {
        this.o = o;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setUserDn(String userDn) {
        this.userDn = userDn;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserPasswordType(String userPasswordType) {
        this.userPasswordType = userPasswordType;
    }

    public String getKey() {
        return key;
    }

    public String getCn() {
        return cn;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getJpegPhoto() {
        return jpegPhoto;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getMail() {
        return mail;
    }

    public String getMobile() {
        return mobile;
    }

    public String getO() {
        return o;
    }

    public String getSn() {
        return sn;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getUserDn() {
        return userDn;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserPasswordType() {
        return userPasswordType;
    }
}
