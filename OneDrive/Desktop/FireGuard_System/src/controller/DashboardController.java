package controller;

import Dao.DashboardDAO;
import Dao.UserDAO;
import java.sql.SQLException;


public class DashboardController {
    private DashboardDAO dashboardDAO;

    public DashboardController(DashboardDAO dashboardDAO) {
        this.dashboardDAO = dashboardDAO;
    }

    public int getStaffCount() throws SQLException {
        return dashboardDAO.countStaffs();
    }
    public int getInventoryCount() throws SQLException {
        return dashboardDAO.countInventories();
    }
    public int getReportCount() throws SQLException {
        return dashboardDAO.countReports();
    }
    public int getScheduleCount() throws SQLException {
        return dashboardDAO.countSchedules();
    }
    
}
