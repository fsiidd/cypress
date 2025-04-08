package cypress;

import java.time.LocalDate;

public class Report {
    private final String type;
    private final String description;
    private final LocalDate date;
    private final double latitude;
    private final double longitude;


    public Report(String type, String description, LocalDate date, double latitude, double longitude) {
        this.type = type;
        this.description = description;
        this.date = date;
	this.latitude = latitude;
        this.longitude = longitude;
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
}

