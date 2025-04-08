package com.cypress.models;

public class Report {
    private double latitude;
    private double longitude;
    private String issueType;
    private String description;

    public Report(double latitude, double longitude, String issueType, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.issueType = issueType;
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getDescription() {
        return description;
    }
}

