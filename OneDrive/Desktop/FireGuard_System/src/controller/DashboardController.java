package controller;

import Dao.UserDAO;

public class DashboardController {
    private UserDAO userDAO;

    public DashboardController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Add any methods to interact with UserDAO here
}
