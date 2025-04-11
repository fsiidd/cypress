package cypress;

import java.util.ArrayList;

public class UserManager {
    private static ArrayList<User> users = new ArrayList<>();

    public static boolean registerCitizen(String name, String email, String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username)) return false;
        }
        users.add(new Citizen(name, email, username, password));
        return true;
    }

    public static boolean registerAdmin(String name, String email, String username, String password) {
        if (!email.endsWith("@toronto.ca")) return false; // assuming city of toronto employees email uses '@toronto.ca' domain

        for (User u : users) {
            if (u.getUsername().equals(username)) return false;
        }
        users.add(new Admin(name, email, username, password));
        return true;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) return u;
        }
        return null;
    }
}