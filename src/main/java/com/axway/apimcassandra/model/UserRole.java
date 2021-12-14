package com.axway.apimcassandra.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "api_portal_portaluserstore")

public class UserRole {

    @PrimaryKey
    private String key;
    @Column(value="orgs2Role", forceQuote = true)
    private String orgs2Role;
    private String role;
    private String id;
    private String tags;
    @Column(value="userDn", forceQuote = true)
    private String userDn;
    private String type;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOrgs2Role() {
        return orgs2Role;
    }

    public void setOrgs2Role(String orgs2Role) {
        this.orgs2Role = orgs2Role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getUserDn() {
        return userDn;
    }

    public void setUserDn(String userDn) {
        this.userDn = userDn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
