import Dao.InventoryDAO;
import Dao.InventoryDAOImpl;
import controller.InventoryController;
import model.Inventory;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;


public class InventoryListPage {
    private JFrame frame;
    private InventoryController controller;
    private JTable inventoryTable;

    public InventoryListPage(InventoryController controller) {
        this.controller = controller;

        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("FireGuard");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            ImageIcon logo = new ImageIcon(getClass().getResource("/images/Logo.png"));
            frame.setIconImage(logo.getImage());

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

           JButton deleteButton = new JButton("Delete");
            deleteButton.setBackground(Color.decode("#01520E"));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setFont(new Font("Arial",Font.PLAIN, 24));            
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteSelectedRow();
                }
            });
            
            panel.add(deleteButton, BorderLayout.SOUTH);

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

   private void deleteSelectedRow() {
    int selectedRow = inventoryTable.getSelectedRow();
    if (selectedRow != -1) {
        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
        String name = (String) model.getValueAt(selectedRow, 0); // Assuming name is in the 1st column
        
        // Remove the row from the table
        model.removeRow(selectedRow);
        
        // Delete the entry from the database
        try {
            controller.deleteItem(name);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to delete inventory item from database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(frame, "No row selected for deletion.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    public static void main(String[] args) {
        InventoryDAO inventoryDAO = new InventoryDAOImpl();
        InventoryController controller = new InventoryController(inventoryDAO);

        SwingUtilities.invokeLater(() -> {
            new InventoryListPage(controller).setVisible(true);
        });
    }

    
}
