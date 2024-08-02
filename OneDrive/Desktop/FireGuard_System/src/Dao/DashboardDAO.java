
package Dao;

import java.sql.SQLException;

public interface DashboardDAO {
    int countStaffs() throws SQLException;
    int countInventories() throws SQLException;
    int countReports() throws SQLException;
    int countSchedules() throws SQLException;
}
