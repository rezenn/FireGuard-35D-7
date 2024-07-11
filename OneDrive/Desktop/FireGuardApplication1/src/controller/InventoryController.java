package controller;

import model.Inventory;
import Dao.InventoryDAO;
import javax.swing.*;
import java.sql.SQLException;

public class InventoryController {
    private InventoryDAO inventoryDAO;
   
    public InventoryController(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public void addInventory(String name, String category, String serialCode, String quantity, String manufactureDate, String expiryDate, String description) {
        if (name.isEmpty() || category.isEmpty() || serialCode.isEmpty() || quantity.isEmpty() || manufactureDate.isEmpty() || expiryDate.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Inventory inventory = new Inventory(name, category, serialCode, quantity, manufactureDate, expiryDate, description);
                boolean success = inventoryDAO.insertInventory(inventory);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Inventory added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add inventory. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error occurred. Failed to add inventory.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

//    public void openDashboard() {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                DashboardPage dashboardPage = new DashboardPage();
//                dashboardPage.setVisible(true);
//            }
//        });
//    }

}
