package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/fireguard";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private Connection conn;

    public UserDAOImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO accounts (full_name, email, password, userType) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUserType());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        // Implement update logic
        return false;
    }

    @Override
    public boolean deleteUser(User user) throws SQLException {
        // Implement delete logic
        return false;
    }

    @Override
    public User validateUser(String email, String userType, String password) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM accounts WHERE email = ? AND password = ? AND userType = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, userType);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve user data from ResultSet
                    String fullName = resultSet.getString("full_name");
                    String userEmail = resultSet.getString("email");
                    String userPassword = resultSet.getString("password");
                    String userTypeResult = resultSet.getString("userType");

                    // Create User object with retrieved data
                    user = new User(fullName, userEmail, userTypeResult, userPassword);
                    // Optionally, set other properties of User if needed
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
}
