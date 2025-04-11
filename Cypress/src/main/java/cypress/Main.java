package cypress;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        User currentUser = null;

        System.out.println("Welcome to Cypress CLI");

        while (running) {
            // If no one is logged in, show main menu
            if (currentUser == null) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Sign up as Citizen");
                System.out.println("2. Sign up as Admin");
                System.out.println("3. Log in");
                System.out.println("4. Exit");
                System.out.print("> ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter full name: ");
                        String nameC = scanner.nextLine().trim();
                        System.out.print("Enter email: ");
                        String emailC = scanner.nextLine().trim();
                        System.out.print("Choose username: ");
                        String userC = scanner.nextLine().trim();
                        System.out.print("Choose password: ");
                        String passC = scanner.nextLine().trim();
                        boolean successC = UserManager.registerCitizen(nameC, emailC, userC, passC);
                        if (successC) {
                            System.out.println("✅ Citizen account created. Please log in.");
                        } else {
                            System.out.println("❌ Username already taken. Try again.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter full name: ");
                        String nameA = scanner.nextLine().trim();
                        System.out.print("Enter email: ");
                        String emailA = scanner.nextLine().trim();
                        System.out.print("Choose username: ");
                        String userA = scanner.nextLine().trim();
                        System.out.print("Choose password: ");
                        String passA = scanner.nextLine().trim();
                        boolean successA = UserManager.registerAdmin(nameA, emailA, userA, passA);
                        if (successA) {
                            System.out.println("✅ Admin account created. Please log in.");
                        } else {
                            System.out.println("❌ Registration failed (invalid email or username taken).\n");
                        }
                        break;
                    case 3:
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        currentUser = UserManager.login(username, password);
                        if (currentUser != null) {
                            System.out.println("✅ Login successful!");
                            System.out.println("Welcome back, " + currentUser.getUsername() + "!\n");
                        } else {
                            System.out.println("❌ Login failed. Try again.");
                        }
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } else {
                // There's a logged in user
                if (currentUser instanceof Citizen) {
                    // ===== CITIZEN MENU =====
                    System.out.println("Citizen Menu:");
                    System.out.println("1. Submit a Report");
                    System.out.println("2. View My Reports");
                    System.out.println("3. Log out");
                    System.out.print("> ");

                    int citizenChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    switch (citizenChoice) {
                        case 1:
                            System.out.print("Enter report type: ");
                            String type = scanner.nextLine();

                            System.out.print("Enter report description: ");
                            String desc = scanner.nextLine();

                            System.out.print("Enter report date (any format): ");
                            String dateStr = scanner.nextLine().trim();

                            System.out.print("Enter report location latitude: ");
                            double latitude;
                            try {
                                latitude = Double.parseDouble(scanner.nextLine());
                            } catch (Exception e) {
                                System.out.println("Invalid latitude. Please enter a valid number.");
                                break;
                            }

                            System.out.print("Enter report location longitude: ");
                            double longitude;
                            try {
                                longitude = Double.parseDouble(scanner.nextLine());
                            } catch (Exception e) {
                                System.out.println("Invalid longitude. Please enter a valid number.");
                                break;
                            }

                            // Creates a new report (string-based date)
                            Report report = new Report(type, desc, dateStr, latitude, longitude);
                            report.setSubmittedBy(currentUser.getUsername());

                            // Submits the report (will do spam/duplicate checks)
                            ReportSystem.submitReport(report);
                            System.out.println(); // blank line for spacing
                            break;
                        case 2:
                            System.out.println("📄 Your Submitted Reports:\n");
                            List<Report> userReports = ReportSystem.getReportsByUser(currentUser.getUsername());
                            if (userReports.isEmpty()) {
                                System.out.println("You have no reports.");
                            } else {
                                userReports.forEach(System.out::println);
                            }
                            System.out.println();
                            break;
                        case 3:
                            currentUser = null;
                            System.out.println("👋 Logged out.");
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                } else if (currentUser instanceof Admin) {
                    // ===== ADMIN MENU =====
                    System.out.println("Admin Menu:");
                    System.out.println("1. View All Reports");
                    System.out.println("2. Filter Reports by Type");
                    System.out.println("3. Filter Reports by Status");
                    System.out.println("4. Update Report Status");
                    System.out.println("5. Log out");
                    System.out.print("> ");

                    int adminChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    switch (adminChoice) {
                        case 1:
                            System.out.println("📄 All Submitted Reports:\n");
                            ReportSystem.printReports();
                            System.out.println();
                            break;
                        case 2:
                            System.out.print("Enter type to filter (e.g. Pothole, Graffiti): ");
                            String filterType = scanner.nextLine().trim();
                            List<Report> typeFiltered = ReportSystem.filterReportsByType(filterType);
                            if (typeFiltered.isEmpty()) {
                                System.out.println("No reports of type '" + filterType + "'.\n");
                            } else {
                                typeFiltered.forEach(System.out::println);
                                System.out.println();
                            }
                            break;
                        case 3:
                            System.out.println("Possible statuses: NEW, IN_PROGRESS, CLOSED");
                            System.out.print("Enter status to filter: ");
                            String statusStr = scanner.nextLine().trim().toUpperCase();
                            try {
                                Status st = Status.valueOf(statusStr);
                                List<Report> statusFiltered = ReportSystem.filterReportsByStatus(st);
                                if (statusFiltered.isEmpty()) {
                                    System.out.println("No reports with status " + st + ".\n");
                                } else {
                                    statusFiltered.forEach(System.out::println);
                                    System.out.println();
                                }
                            } catch (IllegalArgumentException iae) {
                                System.out.println("Invalid status. Use NEW, IN_PROGRESS, or CLOSED.\n");
                            }
                            break;
                        case 4:
                            // Update Report Status
                            System.out.println("📄 All Reports with Indexes:\n");
                            ReportSystem.printReportsWithIndexes();

                            System.out.print("\nEnter the index of the report to update: ");
                            int idx;
                            try {
                                idx = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid index.");
                                break;
                            }

                            System.out.println("Possible statuses: NEW, IN_PROGRESS, CLOSED");
                            System.out.print("Enter new status: ");
                            String newStatusStr = scanner.nextLine().trim().toUpperCase();

                            try {
                                Status newStatus = Status.valueOf(newStatusStr);
                                ReportSystem.updateStatus(idx, newStatus);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid status.");
                            }
                            break;
                        case 5:
                            currentUser = null;
                            System.out.println("👋 Logged out.");
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                }
            }
        }

        System.out.println("👋 Goodbye!");
    }
}
