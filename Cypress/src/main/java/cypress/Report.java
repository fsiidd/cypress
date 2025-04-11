package cypress;

public class Report {
    private final String type;
    private final String description;
    private final String date; // store as string
    private final double latitude;
    private final double longitude;

    private String submittedBy;
    private Status status;

    public Report(String type, String description, String date, double latitude, double longitude) {
        this.type = type;
        this.description = description;
        this.date = date; // just store raw string
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = Status.NEW;
        this.submittedBy = "";
    }

    // getters
    public String getType() { return type; }
    public String getDescription() { return description; }
    public String getDate() { return date; }   // note this returns a String now
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }

    public String getSubmittedBy() { return submittedBy; }
    public void setSubmittedBy(String submittedBy) { this.submittedBy = submittedBy; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return "Report{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", location=(" + latitude + ", " + longitude + ")" +
                ", submittedBy='" + submittedBy + '\'' +
                ", status=" + status +
                '}';
    }
}
