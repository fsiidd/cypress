package cypress;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        User currentUser = null;

        System.out.println("Welcome to Cypress CLI");

        while (running) {
            if (currentUser == null) {
                // ===== MAIN MENU =====
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
                        currentUser = new Citizen(nameC, emailC, userC, passC);
                        System.out.println("Signed up and logged in as Citizen.");
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
                        currentUser = new Admin(nameA, emailA, userA, passA);
                        System.out.println("Signed up and logged in as Admin.");
                        break;

                    case 3:
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        currentUser = UserManager.login(username, password);
                        if (currentUser != null) {
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Login failed. Try again.");
                        }
                        break;

                    case 4:
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                        break;
                }
            } else {
                if (currentUser instanceof Citizen) {
                    // ===== CITIZEN MENU =====
                    System.out.println("\nCitizen Menu:");
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

                            Report report = new Report(type, desc, date, latitude, longitude);
                            ReportSystem.submitReport(report);
                            System.out.println("Report submitted!");
                            break;

                        case 2:
                            ReportSystem.printReports(); // or filter by user if needed
                            break;

                        case 3:
                            currentUser = null;
                            System.out.println("Logged out.");
                            break;

                        default:
                            System.out.println("Invalid option.");
                            break;
                    }
                } else if (currentUser instanceof Admin) {
                    // ===== ADMIN MENU =====
                    System.out.println("\nAdmin Menu:");
                    System.out.println("1. View All Reports");
                    System.out.println("2. Log out");
                    System.out.print("> ");

                    int adminChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    switch (adminChoice) {
                        case 1:
                            ReportSystem.printReports();
                            break;
                        case 2:
                            currentUser = null;
                            System.out.println("Logged out.");
                            break;
                        default:
                            System.out.println("Invalid option.");
                            break;
                    }
                }
            }
        }

        System.out.println("Goodbye!");
    }
}

