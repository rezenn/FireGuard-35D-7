package Dao;

import model.Inventory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAOImpl implements InventoryDAO {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/fireGuard";
    private static final String dbUsername = "root";
    private static final String dbPassword = "root";
    private Connection conn;

    public InventoryDAOImpl() {
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

    @Override
    public List<Inventory> getAllInventories() throws SQLException {
        List<Inventory> inventories = new ArrayList<>();
        String sql = "SELECT * FROM inventory";
        try (Connection connection = conn;
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Inventory inventory = new Inventory(
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("serial_code"),
                        rs.getString("quantity"),
                        rs.getString("manufacture_date"),
                        rs.getString("expiry_date"),
                        rs.getString("description")
                );
                inventories.add(inventory);
            }
        }
        return inventories;
    }

    @Override
    public Inventory getInventoryById(int id) {
        // Implementation for fetching an inventory item by its ID
        // This method is not implemented in your example but should retrieve a single Inventory object
        return null;
    }

    @Override
    public void updateInventory(Inventory inventory) {
        // Implementation for updating an existing inventory item
        // This method is not implemented in your example
    }
}
