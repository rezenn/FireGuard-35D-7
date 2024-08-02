import Dao.DashboardDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardDAOImpl implements DashboardDAO {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/fireguard";
    private static final String dbUsername = "root";
    private static final String dbPassword = "root";
    private Connection conn;

    public DashboardDAOImpl() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/fireGuard";
        String dbUsername = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int countStaffs() throws SQLException {
        String sql = "SELECT COUNT(*) FROM staff";
        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Error counting staffs.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int countInventories() throws SQLException {
        String sql = "SELECT COUNT(*) FROM inventory";
        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Error counting inventory.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int countReports() throws SQLException {
        String sql = "SELECT COUNT(*) FROM operation";
        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Error counting report.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int countSchedules() throws SQLException {
        String sql = "SELECT COUNT(*) FROM schedule";
        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Error counting report.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
