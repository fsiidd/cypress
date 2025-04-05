package cypress;

public class Admin extends User {

    public Admin(String name, String email, String username, String password) {
        super(name, email, username, password);
    }

    @Override
    public String toString() {
        return "Admin{" + "username='" + getUsername() + '\'' + ", email='" + getEmail() + '\'' + '}';
    }
}
