package com.axway.apimcassandra.model;

import java.util.List;

public class APIDependency {
    private String name;
    private String id;
    private List<Organization> providerOrganizations;
    private List<Organization> consumerOrganizations;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Organization> getProviderOrganizations() {
        return providerOrganizations;
    }

    public void setProviderOrganizations(List<Organization> providerOrganizations) {
        this.providerOrganizations = providerOrganizations;
    }

    public List<Organization> getConsumerOrganizations() {
        return consumerOrganizations;
    }

    public void setConsumerOrganizations(List<Organization> consumerOrganizations) {
        this.consumerOrganizations = consumerOrganizations;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    private List<Application> applications;
}
