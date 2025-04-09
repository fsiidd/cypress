package cypress;

import java.time.LocalDate;

public class Report {
    private final String type;
    private final String description;
    private final LocalDate date;
    private final double latitude;
    private final double longitude;

    private Status status;         // from your additions
    private String submittedBy;    // from your additions

    // Constructor with full parameters, including submittedBy
    public Report(String type, String description, LocalDate date, double latitude, double longitude, String submittedBy) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.submittedBy = submittedBy;
        this.status = Status.NEW; // default to NEW
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }

    @Override
    public String toString() {
        return "Submitted By: " + submittedBy +
               " | Type: " + type +
               " | Status: " + status +
               " | Description: " + description +
               " | Date: " + date +
               " | Location: (" + latitude + ", " + longitude + ")";
    }
}

