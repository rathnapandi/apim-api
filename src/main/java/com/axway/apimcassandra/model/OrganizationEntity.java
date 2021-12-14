package com.axway.apimcassandra.model;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "api_portal_portalorganizationstore")

public class OrganizationEntity {
    @PrimaryKey
    private String key;
    private String development;
    private String email;
    private String enabled;
    @Column(value="endTrialDate", forceQuote = true)
    private String endTrialDate;
    private String id;
    private String image;
    @Column(value="isTrial", forceQuote = true)
    private String isTrial;
    @Column(value="organizationDn", forceQuote = true)
    private String organizationDn;
    private String phone;
    private String restricted;
    @Column(value="startTrialDate", forceQuote = true)
    private String startTrialDate;
    private String tags;
    @Column(value="trialDuration", forceQuote = true)
    private String trialDuration;
    @Column(value="virtualHost", forceQuote = true)
    private String virtualHost;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDevelopment() {
        return development;
    }

    public void setDevelopment(String development) {
        this.development = development;
    }

    public String getEmail() {
        if(email != null)
            return CqlIdentifier.fromCql(email).asInternal();
        return null;
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

    public String getEndTrialDate() {
        if(endTrialDate != null)
            return CqlIdentifier.fromCql(endTrialDate).asInternal();
        return null;
    }

    public void setEndTrialDate(String endTrialDate) {
        this.endTrialDate = endTrialDate;
    }

    public String getId() {
        if(id != null)
            return CqlIdentifier.fromCql(id).asInternal();
        return null;
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

    public String getIsTrial() {
        if(isTrial != null)
            return CqlIdentifier.fromCql(isTrial).asInternal();
        return null;
    }

    public void setIsTrial(String isTrial) {
        this.isTrial = isTrial;
    }

    public String getOrganizationDn() {
        if(organizationDn != null)
            return CqlIdentifier.fromCql(organizationDn).asInternal();
        return null;
    }

    public void setOrganizationDn(String organizationDn) {
        this.organizationDn = organizationDn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRestricted() {
        return restricted;
    }

    public void setRestricted(String restricted) {
        this.restricted = restricted;
    }

    public String getStartTrialDate() {
        if(startTrialDate != null)
            return CqlIdentifier.fromCql(startTrialDate).asInternal();
        return null;
    }

    public void setStartTrialDate(String startTrialDate) {
        this.startTrialDate = startTrialDate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTrialDuration() {
        if(trialDuration != null)
            return CqlIdentifier.fromCql(trialDuration).asInternal();
        return null;
    }

    public void setTrialDuration(String trialDuration) {
        this.trialDuration = trialDuration;
    }

    public String getVirtualHost() {
        if(virtualHost != null)
            return CqlIdentifier.fromCql(virtualHost).asInternal();
        return null;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }
}
