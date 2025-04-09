package cypress;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        User currentUser = null;

        System.out.println("Welcome to Cypress CLI");

        while (running) {
            if (currentUser == null) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Sign up as Citizen");
                System.out.println("2. Sign up as Admin");
                System.out.println("3. Log in");
                System.out.println("4. Exit");
                System.out.print("> ");

                int choice = scanner.nextInt();
                scanner.nextLine();

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
                        if (regC) {
                            System.out.println("✅ Account created successfully! Redirecting to login screen...");
                        } else {
                            System.out.println("❌ Username already exists. Please try a different one.");
                        }

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
                        if (regA) {
                            System.out.println("✅ City official account created and verified.");
                        } else {
                            System.out.println("❌ Invalid or already registered email. Must use @toronto.ca.");
                        }

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
                System.out.println("\nWelcome, " + currentUser.getUsername() + " (" + (isAdmin ? "City Official" : "Citizen") + ")");

                if (isAdmin) {
                    System.out.println("1. View All Reports");
                    System.out.println("2. Update Report Status");
                    System.out.println("3. Logout");
                    System.out.print("> ");

                    int dashChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (dashChoice) {
                        case 1:
                            ReportSystem.printReports();
                            break;
                        case 2:
                            ReportSystem.printReportsWithIndexes();
                            System.out.print("Enter the index of the report to update: ");
                            int index = scanner.nextInt();
                            scanner.nextLine();

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
                            break;
                        case 3:
                            currentUser = null;
                            System.out.println("Logged out.");
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                } else {
                    System.out.println("1. Submit a report");
                    System.out.println("2. View submitted reports");
                    System.out.println("3. Logout");
                    System.out.print("> ");

                    int dashChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (dashChoice) {
                        case 1:
                            System.out.print("Enter report type: ");
                            String type = scanner.nextLine();

                            System.out.print("Enter report description: ");
                            String desc = scanner.nextLine();

                            System.out.print("Enter report date (YYYY-MM-DD): ");
                            String dateStr = scanner.nextLine();
                            LocalDate date;
                            try {
                                date = LocalDate.parse(dateStr);
                            } catch (Exception e) {
                                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                                break;
                            }

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

                            // 💥 NEW: Include submittedBy in constructor
                            Report report = new Report(type, desc, date, latitude, longitude, currentUser.getUsername());
                            ReportSystem.submitReport(report);
                            System.out.println("Report submitted!");
                            break;

                        case 2:
                            List<Report> userReports = ReportSystem.getReportsByUser(currentUser.getUsername());
                            if (userReports.isEmpty()) {
                                System.out.println("You have not submitted any reports yet.");
                            } else {
                                System.out.println("Your Submitted Reports:");
                                for (Report r : userReports) {
                                    System.out.println(r);
                                }
                            }
                            break;

                        case 3:
                            currentUser = null;
                            System.out.println("Logged out.");
                            break;

                        default:
                            System.out.println("Invalid option.");
                    }
                }
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}

