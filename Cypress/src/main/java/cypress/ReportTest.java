package cypress;

import java.util.ArrayList;
import java.util.List;

public class ReportTest {
    public static void main(String[] args) {
        System.out.println("✅ Test starting...");

        // Create the ReportService instance for spam/duplicate checks
        ReportService service = new ReportService();

        // Use an ArrayList to hold existing Reports
        List<Report> reports = new ArrayList<>();

        // Add an existing “Pothole” report
        reports.add(new Report("Pothole",
                "Pothole on Main St.",
                "2025-05-01",      // just a string, no date logic
                43.6561,
                -79.3802));

        // Create a new report that’s near the existing pothole
        Report newReport = new Report("Pothole",
                "Another pothole",
                "2025-05-01",
                43.6561005,
                -79.3802003);

        // Test duplicate detection
        boolean isDup = service.isDuplicate(newReport, reports);
        if (isDup) {
            System.out.println("❗Duplicate report detected!");
        } else {
            System.out.println("✅ No duplicates found.");
        }

        System.out.println("🧪 Testing spam detection...");

        // Create a “spammy” report (few words or known spammy patterns)
        Report spamReport = new Report("Pothole",
                "FREE MONEY CLICK HERE",
                "2025-05-01",
                43.65,
                -79.38);

        // Check if the spam detection flags it
        boolean isSpam = service.isSpam(spamReport);
        if (isSpam) {
            System.out.println("🚨 Spam detected!");
        } else {
            System.out.println("✅ Report is clean.");
        }
    }
}
