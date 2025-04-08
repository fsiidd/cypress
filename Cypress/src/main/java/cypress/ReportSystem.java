package cypress;

import java.util.ArrayList;

public class ReportSystem {
    private static ArrayList<Report> reports = new ArrayList<>();

    public static void submitReport(Report report) {
        reports.add(report);
    }

    public static void printReports() {
        if (reports.isEmpty()) {
            System.out.println("No reports submitted.");
        } else {
            System.out.println("Submitted Reports:");
            for (int i = 0; i < reports.size(); i++) {
		Report r = reports.get(i);
                System.out.println((i + 1) + ". Type: " + r.getType() + ", Description: " + r.getDescription() + ", Date: " + r.getDate());
            }
        }
    }
}
