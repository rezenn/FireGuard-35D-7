package dao;

import model.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class StaffDAOImpl implements StaffDAO {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/fireGuard";
    private static final String dbUsername = "root";
    private static final String dbPassword = "root";
    private Connection conn;

    public StaffDAOImpl() {
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
    public boolean insertStaff(Staff staff) throws SQLException {
        String sql = "INSERT INTO staff (name, staff_id, `rank`, age, phone_number, email, address, recruit_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, staff.getName());
            statement.setString(2, staff.getStaff_id());
            statement.setString(3, staff.getRank());
            statement.setInt(4, staff.getAge());
            statement.setString(5, staff.getPhone_number());
            statement.setString(6, staff.getEmail());
            statement.setString(7, staff.getAddress());
            statement.setString(8, staff.getRecruit_date());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace for detailed debug information
            throw e; // Re-throw the exception to be handled in the controller
        }
    }

    @Override
    public List<Staff> getAllStaffs() throws SQLException {
        List<Staff> staffs = new ArrayList<>();
        String sql = "SELECT * FROM staff";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Staff staff = new Staff(
                        rs.getString("staff_id"),
                        rs.getString("name"),
                        rs.getString("rank"),
                        rs.getInt("age"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("recruit_date")
                );
                staffs.add(staff);
            }
        }

        return staffs;
    }

    @Override
    public Staff getStaffById(int id) throws SQLException {
        String sql = "SELECT * FROM staff WHERE staff_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Staff(
                        rs.getString("staff_id"),
                        rs.getString("name"),
                        rs.getString("rank"),
                        rs.getInt("age"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("recruit_date")
                );
            }
        }
        return null;
    }

    @Override
    public void updateStaff(Staff staff) throws SQLException {
        String sql = "UPDATE staff SET name = ?, `rank` = ?, age = ?, phone_number = ?, email = ?, address = ?, recruit_date = ? WHERE staff_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, staff.getName());
            statement.setString(2, staff.getRank());
            statement.setInt(3, staff.getAge());
            statement.setString(4, staff.getPhone_number());
            statement.setString(5, staff.getEmail());
            statement.setString(6, staff.getAddress());
            statement.setString(7, staff.getRecruit_date());
            statement.setString(8, staff.getStaff_id());

            statement.executeUpdate();
        }
    }
}
