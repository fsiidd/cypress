package cypress;

import java.util.ArrayList;
import java.util.List;

import cypress.Report;
import cypress.ReportService;

public class ReportTest {
    public static void main(String[] args) {
        System.out.println("✅ Test starting...");

        ReportService service = new ReportService();

        List<Report> reports = new ArrayList<>();
        reports.add(new Report(43.6561, -79.3802, "Pothole", "Pothole on Main St."));

        Report newReport = new Report(43.6561005, -79.3802003, "Pothole", "Another pothole");

        boolean isDup = service.isDuplicate(newReport, reports);
        if (isDup) {
            System.out.println("❗Duplicate report detected!");
        } else {
            System.out.println("✅ No duplicates found.");
        }
	
	System.out.println("🧪 Testing spam detection...");

	Report spamReport = new Report(43.65, -79.38, "Pothole", "asdf asdf asdf asdf asdf");
	boolean isSpam = service.isSpam(spamReport);
	if (isSpam) {
    		System.out.println("🚨 Spam detected!");
	} else {
    		System.out.println("✅ Report is clean.");
	}

    }
}

