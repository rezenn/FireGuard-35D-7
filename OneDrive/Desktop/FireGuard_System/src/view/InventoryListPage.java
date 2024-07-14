import Dao.InventoryDAO;
import Dao.InventoryDAOImpl;
import controller.InventoryController;
import model.Inventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InventoryListPage {
    private JFrame frame;
    private InventoryController controller;
    private JTable inventoryTable;

    public InventoryListPage(InventoryController controller) {
        this.controller = controller;

        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Inventory List");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JPanel panel = new JPanel(new BorderLayout());
            frame.add(panel);

            JLabel titleLabel = new JLabel("Inventory List", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
            panel.add(titleLabel, BorderLayout.NORTH);

            // Inventory Table
            String[] columnNames = { "Name", "Category", "Serial Code", "Quantity", "Manufacture Date", "Expiry Date", "Description"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            inventoryTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(inventoryTable);
            panel.add(scrollPane, BorderLayout.CENTER);

            updateInventoryTable();

        

            // Set frame size and make it visible
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null); // Center the window
            frame.setVisible(true);
        });
    }

    public void updateInventoryTable() {
        List<Inventory> inventoryList = controller.getAllInventories();
        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
        model.setRowCount(0); // Clear existing rows
        for (Inventory item : inventoryList) {
            model.addRow(new Object[]{
                    item.getName(),
                    item.getCategory(),
                    item.getSerialCode(),
                    item.getQuantity(),
                    item.getManufactureDate(),
                    item.getExpiryDate(),
                    item.getDescription()
            });
        }
    }

    public void setVisible(boolean isVisible) {
        frame.setVisible(isVisible);
    }

    public void dispose() {
        frame.dispose();
    }

    public static void main(String[] args) {
        InventoryDAO inventoryDAO = new InventoryDAOImpl();
        InventoryController controller = new InventoryController(inventoryDAO);

        SwingUtilities.invokeLater(() -> {
            new InventoryListPage(controller).setVisible(true);
        });
    }
}
