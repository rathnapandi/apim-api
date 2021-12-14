package com.axway.apimcassandra.model;

import java.util.List;

public class AccessStore  {
    private String apiId;
    private String createdBy;
    private String createdOn;
    private String entityId;
    private String organizationId;

    @Override
    public String toString() {
        return "AccessStore{" +
                "apiId='" + apiId + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", entityId='" + entityId + '\'' +
                ", organizationId='" + organizationId + '\'' +
                '}';
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getCreatedBy() {
        return createdBy;
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

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}
