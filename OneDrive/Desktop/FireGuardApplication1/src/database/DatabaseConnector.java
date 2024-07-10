import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/fireguard";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DatabaseConnector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
            if (connection != null) {
                System.out.println("Connected");
            } else {
                System.out.println("Connection Error");
            }
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
        Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
    }

    // Create, Update, Delete
    public void manipulate(String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Read // Fetch
    public ResultSet retrieve(String query) {
        try {
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
