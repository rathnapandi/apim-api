package com.axway.apimcassandra.model;

public class Organization {
    private String organizationDn;
    private String development;
    private String enabled;

    public String getOrganizationDn() {
        return organizationDn;
    }

    public void setOrganizationDn(String organizationDn) {
        this.organizationDn = organizationDn;
    }

    public String getDevelopment() {
        return development;
    }

    public void setDevelopment(String development) {
        this.development = development;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "organizationDn='" + organizationDn + '\'' +
                ", development='" + development + '\'' +
                ", enabled='" + enabled + '\'' +
                '}';
    }
}
