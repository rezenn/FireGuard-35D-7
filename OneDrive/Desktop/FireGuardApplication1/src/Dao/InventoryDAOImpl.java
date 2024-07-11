package Dao;

import model.Inventory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDAOImpl implements InventoryDAO {
    private Connection conn;

    public InventoryDAOImpl() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/fireGuard";
        String dbUsername = "root";
        String dbPassword = "root";

        try {
            conn = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle connection failure
        }
    }

    @Override
    public boolean insertInventory(Inventory inventory) throws SQLException {
        String sql = "INSERT INTO inventory (name, category, serial_code, quantity, manufacture_date, expiry_date, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, inventory.getName());
            statement.setString(2, inventory.getCategory());
            statement.setString(3, inventory.getSerialCode());
            statement.setString(4, inventory.getQuantity());
            statement.setString(5, inventory.getManufactureDate());
            statement.setString(6, inventory.getExpiryDate());
            statement.setString(7, inventory.getDescription());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    }
}
