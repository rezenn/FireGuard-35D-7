import Dao.User;
import Dao.UserDAO;
import Dao.UserDAOImpl;
import javax.swing.*;
import java.sql.SQLException;

public class LoginController {
    private UserDAO userDao;

    public LoginController() {
        this.userDao = new UserDAOImpl();
    }

    public void loginuser(String email, String password) {
        try {
            User user = userDao.validateUser(email, password);
            if (user != null) {
                // Successfully logged in
                JOptionPane.showMessageDialog(null, "Login successful! Welcome " + user.getFullName());
                // Proceed to the next screen or functionality
                openDashboard(user);

            } else {
                // Invalid credentials
                JOptionPane.showMessageDialog(null, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while trying to log in. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void openDashboard(User user) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DashboardPage dashboardPage = new DashboardPage(user);
                dashboardPage.setVisible(true);
            }
        });
    }
}
