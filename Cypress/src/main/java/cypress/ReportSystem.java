package cypress;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportSystem {
    private static final List<Report> reports = new ArrayList<>();

    public static void submitReport(Report report) {
        reports.add(report);
    }

    public static void printReports() {
        if (reports.isEmpty()) {
            System.out.println("No reports submitted.");
        } else {
            for (Report r : reports) {
                System.out.println(r);
            }
        }
    }

    public static List<Report> getReportsByUser(String username) {
        return reports.stream()
                      .filter(r -> username.equals(r.getSubmittedBy()))
                      .collect(Collectors.toList());
    }

    public static List<Report> filterReportsByType(String type) {
        return reports.stream()
                      .filter(r -> r.getType().equalsIgnoreCase(type))
                      .collect(Collectors.toList());
    }

    public static void printReportsWithIndexes() {
        if (reports.isEmpty()) {
            System.out.println("No reports available.");
        } else {
            for (int i = 0; i < reports.size(); i++) {
                System.out.println("[" + i + "] " + reports.get(i));
            }
        }
    }

    public static void updateStatus(int index, Status newStatus) {
        if (index >= 0 && index < reports.size()) {
            reports.get(index).setStatus(newStatus);
        } else {
            System.out.println("Invalid report index.");
        }
    }
}

