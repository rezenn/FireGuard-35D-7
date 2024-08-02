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

    public void loginUser(String email, String userType, String password) {
        try {
            User user = userDao.validateUser(email, userType, password);
            if (user != null) {
            JOptionPane.showMessageDialog(null, "Login successful! Welcome " + user.getFullName());

                openDashboard(user);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while trying to log in. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openDashboard(User user) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if ("Admin".equalsIgnoreCase(user.getUserType())) {
                    AdminDashboardPage adminDashboardPage = new AdminDashboardPage(user);
                    adminDashboardPage.setVisible(true);
                } else {
                    DashboardPage userDashboardPage = new DashboardPage(user);
                    userDashboardPage.setVisible(true);
                }
            }
        });
    }
}
