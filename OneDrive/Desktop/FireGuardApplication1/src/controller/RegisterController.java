import Dao.User;
import Dao.UserDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class RegisterController {
    private UserDAO userDAO;

    public RegisterController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void registerUser(String fullName, String email, String password, String confirmPassword) {
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email address", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Create User object
            User user = new User(fullName, email, password);

            try {
                // Use DAO to insert user
                boolean success = userDAO.insertUser(user);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to register. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to register. Please try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean isValidEmail(String email) {
        // Simple email validation logic (you can replace with your own)
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z]+\\.[A-Za-z]+$");
    }
}
