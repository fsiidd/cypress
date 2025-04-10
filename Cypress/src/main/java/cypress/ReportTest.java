package cypress;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ReportTest {
    public static void main(String[] args) {
        System.out.println("✅ Test starting...");

        ReportService service = new ReportService();

        List<Report> reports = new ArrayList<>();
        reports.add(new Report("Pothole", "Pothole on Main St.", LocalDate.now(), 43.6561, -79.3802));

        Report newReport = new Report("Pothole", "Another pothole", LocalDate.now(), 43.6561005, -79.3802003);

        boolean isDup = service.isDuplicate(newReport, reports);
        if (isDup) {
            System.out.println("❗Duplicate report detected!");
        } else {
            System.out.println("✅ No duplicates found.");
        }

        System.out.println("🧪 Testing spam detection...");

        Report spamReport = new Report("Pothole", "FREE MONEY CLICK HERE", LocalDate.now(), 43.65, -79.38);
        boolean isSpam = service.isSpam(spamReport);
        if (isSpam) {
            System.out.println("🚨 Spam detected!");
        } else {
            System.out.println("✅ Report is clean.");
        }
    }
}

