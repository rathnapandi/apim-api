package com.axway.apimcassandra.model;

import com.axway.apimcassandra.StringQuoteDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = StringQuoteDeserializer.class)
public class Summary {
    private int frontendAPICount;
    private int backendAPICount;
    private int applicationCount;
    private int organizationCount;
    private int usersCount;
    private int quotasCount;

    public int getFrontendAPICount() {
        return frontendAPICount;
    }

    public void setFrontendAPICount(int frontendAPICount) {
        this.frontendAPICount = frontendAPICount;
    }

    public int getBackendAPICount() {
        return backendAPICount;
    }

    public void setBackendAPICount(int backendAPICount) {
        this.backendAPICount = backendAPICount;
    }

    public int getApplicationCount() {
        return applicationCount;
    }

    public void setApplicationCount(int applicationCount) {
        this.applicationCount = applicationCount;
    }

    public int getOrganizationCount() {
        return organizationCount;
    }

    public void setOrganizationCount(int organizationCount) {
        this.organizationCount = organizationCount;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public int getQuotasCount() {
        return quotasCount;
    }

    public void setQuotasCount(int quotasCount) {
        this.quotasCount = quotasCount;
    }
}
