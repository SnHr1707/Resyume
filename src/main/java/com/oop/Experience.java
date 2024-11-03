package com.oop;

public class Experience {
    private String role;
    private String organization;
    private String startDate;
    private String endDate;

    public Experience(String role, String organization, String startDate, String endDate) {
        this.role = role;
        this.organization = organization;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getRole() {
        return role;
    }

    public String getOrganization() {
        return organization;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String toString() {
        return role + " at " + organization + " (" + startDate + " - " + endDate + ")\n";
    }
}
