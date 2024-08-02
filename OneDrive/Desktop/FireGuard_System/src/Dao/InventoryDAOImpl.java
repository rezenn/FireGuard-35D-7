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
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/fireguard";
    private static final String dbUsername = "root";
    private static final String dbPassword = "root";
    private Connection conn;

    public InventoryDAOImpl() {
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
        try (Statement stmt = conn.createStatement();
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
        return null;
    }

    @Override
    public void updateInventory(Inventory inventory) throws SQLException {
        String sql = "UPDATE inventory SET category = ?, serial_code = ?, quantity = ?, manufacture_date = ?, expiry_date = ?, description = ? WHERE name = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, inventory.getCategory());
            statement.setString(2, inventory.getSerialCode());
            statement.setString(3, inventory.getQuantity());
            statement.setString(4, inventory.getManufactureDate());
            statement.setString(5, inventory.getExpiryDate());
            statement.setString(6, inventory.getDescription());
            statement.setString(7, inventory.getName());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("No inventory item found with the name: " + inventory.getName());
            } else {
                System.out.println("Inventory item with name " + inventory.getName() + " updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to update inventory in the database", e);
        }
    }

    @Override
    public void deleteItem(String name) throws SQLException {
        String sql = "DELETE FROM inventory WHERE name = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, name);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No inventory item found with the name: " + name);
            } else {
                System.out.println("Inventory item with name " + name + " deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to delete inventory from the database", e);
        }
    }
}
