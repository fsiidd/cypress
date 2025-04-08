package cypress;

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
                // ===== LOGGED-IN DASHBOARD =====
                boolean isAdmin = currentUser instanceof Admin;
                System.out.println("\nWelcome, " + currentUser.getUsername() + " (" + (isAdmin ? "City Official" : "Citizen") + ")");

                if (isAdmin) {
                    System.out.println("1. View All Reports");
                    System.out.println("2. Logout");
                    System.out.print("> ");

                    int dashChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (dashChoice) {
                        case 1:
                            ReportSystem.printReports();
                            break;
                        case 2:
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
                            System.out.print("Enter report description: ");
                            String desc = scanner.nextLine();
                            Report report = new Report(desc);
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
                    }
                }
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}
