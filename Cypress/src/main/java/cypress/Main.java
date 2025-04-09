package cypress;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        User currentUser = null;

        System.out.println("Welcome to Cypress CLI!");

        while (running) {
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

                        if (nameC.isEmpty() || emailC.isEmpty() || userC.isEmpty() || passC.isEmpty()) {
                            System.out.println("❌ All fields are required. Please try again.");
                            break;
                        }

                        boolean regC = UserManager.registerCitizen(nameC, emailC, userC, passC);
                        System.out.println(regC ? "✅ Account created! Redirecting to login..." : "❌ Username already exists.");
                        break;

                    case 2:
                        System.out.print("Enter full name: ");
                        String nameA = scanner.nextLine().trim();
                        System.out.print("Enter government email: ");
                        String emailA = scanner.nextLine().trim();
                        System.out.print("Choose username: ");
                        String userA = scanner.nextLine().trim();
                        System.out.print("Choose password: ");
                        String passA = scanner.nextLine().trim();

                        if (nameA.isEmpty() || emailA.isEmpty() || userA.isEmpty() || passA.isEmpty()) {
                            System.out.println("❌ All fields are required. Please try again.");
                            break;
                        }

                        boolean regA = UserManager.registerAdmin(nameA, emailA, userA, passA);
                        System.out.println(regA ? "✅ City official account created." : "❌ Invalid or duplicate email.");
                        break;

                    case 3:
                        System.out.print("Username: ");
                        String loginUser = scanner.nextLine();
                        System.out.print("Password: ");
                        String loginPass = scanner.nextLine();

                        currentUser = UserManager.login(loginUser, loginPass);

                        if (currentUser != null) {
                            System.out.println("Login successful! Welcome, " + currentUser.getUsername() + ".");
                        } else {
                            System.out.println("Invalid credentials.");
                        }
                        break;

                    case 4:
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }

            } else {
                boolean isAdmin = currentUser instanceof Admin;
                System.out.println("\nWelcome, " + currentUser.getUsername() +
                        " (" + (isAdmin ? "City Official" : "Citizen") + ")");

                System.out.println("1. Submit a report");
                System.out.println("2. View submitted reports");
                if (isAdmin) {
                    System.out.println("3. Admin: View & Filter Reports");
                    System.out.println("4. Update Report Status");
                    System.out.println("5. Logout");
                } else {
                    System.out.println("3. Logout");
                }

                System.out.print("> ");
                int dashChoice = scanner.nextInt();
                scanner.nextLine();

                switch (dashChoice) {
                    case 1:
                        System.out.print("Enter report description: ");
                        String desc = scanner.nextLine();
                        System.out.print("Enter report type (e.g., Pothole, Graffiti): ");
                        String type = scanner.nextLine();

                        Report report = new Report(desc, type, currentUser.getUsername());
                        ReportSystem.submitReport(report);
                        System.out.println("✅ Report submitted!");
                        break;

                    case 2:
                        if (isAdmin) {
                            ReportSystem.printReports();
                        } else {
                            List<Report> userReports = ReportSystem.getReportsByUser(currentUser.getUsername());
                            if (userReports.isEmpty()) {
                                System.out.println("You have not submitted any reports yet.");
                            } else {
                                System.out.println("Your Submitted Reports:");
                                for (Report r : userReports) {
                                    System.out.println(r);
                                }
                            }
                        }
                        break;

                    case 3:
                        if (isAdmin) {
                            System.out.print("Enter report type to filter by: ");
                            String filterType = scanner.nextLine();
                            List<Report> filtered = ReportSystem.filterReportsByType(filterType);

                            if (filtered.isEmpty()) {
                                System.out.println("No reports found for that type.");
                            } else {
                                for (Report r : filtered) {
                                    System.out.println(r);
                                }
                            }
                        } else {
                            currentUser = null;
                            System.out.println("Logged out.");
                        }
                        break;

                    case 4:
                        if (isAdmin) {
                            ReportSystem.printReportsWithIndexes();
                            System.out.print("Enter the index of the report to update: ");
                            int index = scanner.nextInt();
                            scanner.nextLine(); // consume newline

                            System.out.println("Choose new status:");
                            for (Status s : Status.values()) {
                                System.out.println("- " + s.name());
                            }
                            System.out.print("> ");
                            String statusInput = scanner.nextLine().toUpperCase();

                            try {
                                Status newStatus = Status.valueOf(statusInput);
                                ReportSystem.updateStatus(index, newStatus);
                                System.out.println("✅ Status updated.");
                            } catch (IllegalArgumentException e) {
                                System.out.println("❌ Invalid status value.");
                            }
                        } else {
                            System.out.println("Invalid option.");
                        }
                        break;

                    case 5:
                        if (isAdmin) {
                            currentUser = null;
                            System.out.println("Logged out.");
                        } else {
                            System.out.println("Invalid option.");
                        }
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}

