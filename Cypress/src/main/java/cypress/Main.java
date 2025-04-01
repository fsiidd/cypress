package cypress;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to Cypress CLI");

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Submit a report");
            System.out.println("2. View all reports");
            System.out.println("3. Exit");
            System.out.print("> ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter report description: ");
                    String desc = scanner.nextLine();
                    Report report = new Report(desc);
                    ReportSystem.submitReport(report);
                    System.out.println("Report submitted!");
                    break;
                case 2:
                    ReportSystem.printReports();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}

