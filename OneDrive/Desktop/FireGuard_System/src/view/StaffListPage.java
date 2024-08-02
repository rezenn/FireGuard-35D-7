import controller.StaffController;
import dao.StaffDAO;
import dao.StaffDAOImpl;
import model.Staff;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaffListPage {
    private JFrame frame;
    private StaffController controller;
    private JTable staffTable;

    public StaffListPage(StaffController controller) {
        this.controller = controller;
                
        SwingUtilities.invokeLater(() -> {
        frame = new JFrame("FireGuard");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        ImageIcon logo = new ImageIcon(getClass().getResource("/images/Logo.png"));
        frame.setIconImage(logo.getImage());
       
        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);
        
        JLabel titleLabel = new JLabel("Staff", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Table model with column names
        String[] columnNames = {"Staff ID", "Name", "Rank", "Age", "Phone Number", "Email", "Address", "Recruit Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        staffTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(staffTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.decode("#01520E"));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.setFont(new Font("Arial",Font.PLAIN, 24));   
           
            panel.add(deleteButton, BorderLayout.SOUTH);
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        deleteSelectedRow();
                    } catch (SQLException ex) {
                        Logger.getLogger(StaffListPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            panel.add(deleteButton, BorderLayout.SOUTH);
        updateStaffTable();
        
        frame.setSize(800, 600);
            frame.setLocationRelativeTo(null); // Center the window
            frame.setVisible(true);
        });
    }
    public void updateStaffTable() {
        List<Staff> staffList = controller.getAllStaffs();
        DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
        model.setRowCount(0); // Clear existing rows
        for (Staff item : staffList) {
            model.addRow(new Object[]{
                    item.getName(),
                    item.getStaff_id(),
                    item.getRank(),
                    item.getAge(),
                    item.getPhone_number(),
                    item.getEmail(),
                    item.getAddress(),
                    item.getRecruit_date()
            });
        }
    }
    public void setVisible(boolean isVisible) {
        frame.setVisible(isVisible);
    }
    public void dispose() {
        frame.dispose();
    }
    private void deleteSelectedRow() throws SQLException {
        int selectedRow = staffTable.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
            String name = (String) model.getValueAt(selectedRow, 1); 
            // Remove the row from the table
            model.removeRow(selectedRow);
            
        try {
            controller.deleteStaff(name);
} catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to delete staff item from database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(frame, "No row selected for deletion.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    public static void main(String[] args) {
        StaffDAO staffDAO = new StaffDAOImpl();
        StaffController controller = new StaffController(staffDAO);

        SwingUtilities.invokeLater(() -> {
            new StaffListPage(controller).setVisible(true);
        });
    }
}
