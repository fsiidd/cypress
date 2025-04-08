package cypress;

import java.time.LocalDate;

public class Report {
    private final String type;
    private final String description;
    private final LocalDate date;

    public Report(String type, String description, LocalDate date) {
        this.type = type;
        this.description = description;
        this.date = date;
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
}

