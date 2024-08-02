package dao;

import model.Schedule;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class ScheduleDAOImpl implements ScheduleDAO {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/fireguard";
    private static final String dbUsername = "root";
    private static final String dbPassword = "root";
    private Connection conn;

    public ScheduleDAOImpl() {
        try {
            conn = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            if (conn != null) {
                System.out.println("Connected to the database successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean insertSchedule(Schedule schedule) throws SQLException {
        String sql = "INSERT INTO schedule (name, `rank`, phone_number, email, date, shift) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, schedule.getName());
            statement.setString(2, schedule.getRank());
            statement.setString(3, schedule.getPhone_number());
            statement.setString(4, schedule.getEmail());
            statement.setString(5, schedule.getDate());
            statement.setString(6, schedule.getShift());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace for detailed debug information
            throw e; // Re-throw the exception to be handled in the controller
        }
    }

    @Override
    public List<Schedule> getAllSchedules() throws SQLException {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM schedule";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Schedule schedule = new Schedule(
                        rs.getString("name"),
                        rs.getString("rank"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("date"),
                        rs.getString("shift")
                );
                schedules.add(schedule);
            }
        }

        return schedules;
    }

    @Override
    public Schedule getScheduleById(int id) throws SQLException {
        String sql = "SELECT * FROM schedule WHERE schedule_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Schedule(
                        rs.getString("name"),
                        rs.getString("rank"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("date"),
                        rs.getString("shift")
                );
            }
        }
        return null;
    }

    @Override
    public void updateSchedule(Schedule schedule) throws SQLException {
        String sql = "UPDATE schedule SET `rank` = ?, phone_number = ?, email = ?, date = ?, shift = ? WHERE name = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, schedule.getRank());
            statement.setString(2, schedule.getPhone_number());
            statement.setString(3, schedule.getEmail());
            statement.setString(4, schedule.getDate());
            statement.setString(5, schedule.getShift());
            statement.setString(6, schedule.getName());

           int rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to update schedule in the database", e);
        }
    }

    @Override
    public void deleteScheduleByPhoneNumber(String phoneNumber) throws SQLException {
        String sql = "DELETE FROM schedule WHERE phone_number = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, phoneNumber);
            int rowsAffected = statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to delete schedule from the database", e);
        }
    }
}