package com.cypress.services;

import java.util.List;

import com.cypress.models.Report;

public class ReportService {
    private static final double DUPLICATE_RADIUS_METERS = 100;

    public boolean isDuplicate(Report newReport, List<Report> existingReports) {
        for (Report report : existingReports) {
            if (report.getIssueType().equalsIgnoreCase(newReport.getIssueType())) {
                double distance = haversineDistance(
                    report.getLatitude(), report.getLongitude(),
                    newReport.getLatitude(), newReport.getLongitude()
                );
                if (distance <= DUPLICATE_RADIUS_METERS) {
                    return true; // Duplicate found
                }
            }
        }
        return false;
    }

    private double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371000; // Radius of Earth in meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}

