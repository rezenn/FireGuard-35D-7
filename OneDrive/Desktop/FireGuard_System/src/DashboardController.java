import Dao.UserDAO;

public class DashboardController {
    private UserDAO userDAO;

    public DashboardController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

}
