package cypress;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportSystem {
    private static final List<Report> reports = new ArrayList<>();

    // For spam/duplicate checks
    private static final ReportService service = new ReportService();

    public static void submitReport(Report report) {
        // 1. Check spam
        if (service.isSpam(report)) {
            System.out.println("❌ This report looks like spam. It was NOT submitted.");
            return;
        }
        // 2. Check duplicate
        if (service.isDuplicate(report, reports)) {
            System.out.println("❌ This report is a duplicate (same type & near location). It was NOT submitted.");
            return;
        }
        // 3. Passed checks
        reports.add(report);
        System.out.println("✅ Report submitted!");
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

    // NEW: Filter by Status
    public static List<Report> filterReportsByStatus(Status status) {
        return reports.stream()
                .filter(r -> r.getStatus() == status)
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

    // Admin can update the status of a chosen report
    public static void updateStatus(int index, Status newStatus) {
        if (index >= 0 && index < reports.size()) {
            reports.get(index).setStatus(newStatus);
            System.out.println("✅ Report status updated to " + newStatus + "!");
        } else {
            System.out.println("Invalid report index.");
        }
    }
}
