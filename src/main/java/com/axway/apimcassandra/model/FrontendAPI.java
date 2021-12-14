package com.axway.apimcassandra.model;

import com.axway.apimcassandra.Util;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(value = "api_portal_portalvirtualizedapi")
public class FrontendAPI {
    @PrimaryKey
    private String key;
    private String id;
    @Column(value="organizationId", forceQuote = true)
    private String organizationId;
    @Column(value="apiId", forceQuote = true)
    private String apiId;
    private String name;
    private String version;
    @Column(value="apiRoutingKey", forceQuote = true)
    private String apiRoutingKey;
    private String  vhost;
    private String path;
    private String summary;
    private String retired;
    private String  expired;
    private String  image;
    @Column(value="retirementDate", forceQuote = true)
    private String retirementDate;
    private String deprecated;
    private String state;
    @Column(value="createdOn", forceQuote = true)
    private String createdOn;
    @Column(value="createdBy", forceQuote = true)
    private String createdBy;

    public String getId() {
       return Util.removeQuote(id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizationId() {
        return Util.removeQuote(organizationId);
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getApiId() {
        return Util.removeQuote(apiId);
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getName() {
        return Util.removeQuote(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return Util.removeQuote(version);
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApiRoutingKey() {
        return apiRoutingKey;
    }

    public void setApiRoutingKey(String apiRoutingKey) {
        this.apiRoutingKey = apiRoutingKey;
    }

    public String getVhost() {
        return vhost;
    }

    public void setVhost(String vhost) {
        this.vhost = vhost;
    }

    public String getPath() {
        return Util.removeQuote(path);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String isRetired() {
        return retired;
    }

    public void setRetired(String retired) {
        this.retired = retired;
    }

    public String isExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRetirementDate() {
        return retirementDate;
    }

    public void setRetirementDate(String retirementDate) {
        this.retirementDate = retirementDate;
    }

    public String isDeprecated() {
        return deprecated;
    }

    public void setDeprecated(String deprecated) {
        this.deprecated = deprecated;
    }

    public String getState() {
        return Util.removeQuote(state);
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return Util.removeQuote(createdBy);
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "FrontendAPI{" +
                "id='" + id + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", apiId='" + apiId + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", apiRoutingKey='" + apiRoutingKey + '\'' +
                ", vhost='" + vhost + '\'' +
                ", path='" + path + '\'' +
                ", summary='" + summary + '\'' +
                ", retired=" + retired +
                ", expired=" + expired +
                ", image='" + image + '\'' +
                ", retirementDate=" + retirementDate +
                ", deprecated=" + deprecated +
                ", state='" + state + '\'' +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }




}
