
import Dao.UserDAO;
import Dao.UserDAOImpl;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Create an instance of UserDAO (implement it first)
        UserDAO userDAO = new UserDAOImpl(); // Example implementation, implement this class

        // Create an instance of RegisterController with UserDAO
        RegisterController registerController = new RegisterController(userDAO);

        // Run GUI-related code on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RegisterPage registerPage = new RegisterPage(registerController);
                registerPage.setVisible(true);
            }
        });
    }
}
