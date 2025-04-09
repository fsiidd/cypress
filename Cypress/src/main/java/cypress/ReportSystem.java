package cypress;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportSystem {
    private static List<Report> reports = new ArrayList<>();

    public static void submitReport(Report report) {
        reports.add(report);
    }

    public static void printReports() {
        for (Report r : reports) {
            System.out.println(r);
        }
    }

    public static List<Report> filterReportsByType(String type) {
        return reports.stream()
                .filter(r -> r.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public static List<Report> getReportsByUser(String username) {
        return reports.stream()
                .filter(r -> r.getSubmittedBy().equalsIgnoreCase(username))
                .collect(Collectors.toList());
    }

    public static void printReportsWithIndexes() {
        for (int i = 0; i < reports.size(); i++) {
            System.out.println("[" + i + "] " + reports.get(i));
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

