package cypress;

import java.util.List;

import cypress.Report;

public class ReportService {
    private static final double DUPLICATE_RADIUS_METERS = 100;

    public boolean isDuplicate(Report newReport, List<Report> existingReports) {
        for (Report report : existingReports) {
            if (report.getType().equalsIgnoreCase(newReport.getType())) {
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
    
    public boolean isSpam(Report report) {
    String description = report.getDescription().toLowerCase();
    String[] words = description.trim().split("\\s+");

    // Rule 1: Too short
    if (words.length < 5) {
        return true;
    }

    // Rule 2: Known spammy patterns
    String[] spamWords = {"asdf", "test", "spam", "dummy", "fake"};
    for (String word : words) {
        for (String spam : spamWords) {
            if (word.contains(spam)) {
                return true;
            }
        }
    }

    // Rule 3: Excessive repetition
    int repeatCount = 0;
    for (int i = 1; i < words.length; i++) {
        if (words[i].equals(words[i - 1])) {
            repeatCount++;
        }
    }
    if (repeatCount > 3) {
        return true;
    }

    return false;
  }

}

