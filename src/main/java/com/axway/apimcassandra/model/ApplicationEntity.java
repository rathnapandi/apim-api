package com.axway.apimcassandra.model;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "api_server_portalapplicationstore")
public class ApplicationEntity {
    @PrimaryKey
    private String key;

    @Column(value="createdBy", forceQuote = true)
    private String createdBy;
    @Column(value="createdOn", forceQuote = true)
    private String createdOn;
    private String description;
    private String email;
    private String enabled;
    private String id;
    private String image;
    private String name;
    @Column(value="organizationId", forceQuote = true)
    private String organizationId;
    private String phone;
    @Column(value="quotaId", forceQuote = true)
    private String quotaId;
    private String tags;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCreatedBy() {
        return CqlIdentifier.fromCql(createdBy).asInternal();
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getDescription() {
        if(description != null)
            return CqlIdentifier.fromCql(description).asInternal();
        return null;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getId() {
        return CqlIdentifier.fromCql(id).asInternal();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return CqlIdentifier.fromCql(name).asInternal();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizationId() {
        return CqlIdentifier.fromCql(organizationId).asInternal();
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuotaId() {
        return CqlIdentifier.fromCql(quotaId).asInternal();
    }

    public void setQuotaId(String quotaId) {
        this.quotaId = quotaId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
