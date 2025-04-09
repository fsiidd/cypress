package cypress;

public class Report {
    private String description;
    private String type;
    private Status status;
    private String submittedBy;

    public Report(String description, String type, String submittedBy) {
        this.description = description;
        this.type = type;
        this.submittedBy = submittedBy;
        this.status = Status.NEW; // default
    }

    public String getType() {
        return type;
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

    public String toString() {
        return "Submitted By: " + submittedBy +
               " | Type: " + type +
               " | Status: " + status +
               " | Description: " + description;
    }
}



