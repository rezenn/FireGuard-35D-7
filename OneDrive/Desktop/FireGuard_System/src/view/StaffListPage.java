import controller.StaffController;
import dao.StaffDAO;
import dao.StaffDAOImpl;
import model.Staff;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StaffListPage {
    private JFrame frame;
    private StaffController controller;
    private JTable staffTable;

    public StaffListPage(StaffController controller) {
        this.controller = controller;
                
        SwingUtilities.invokeLater(() -> {
        frame = new JFrame("Staff");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
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
    

    public static void main(String[] args) {
        StaffDAO staffDAO = new StaffDAOImpl();
        StaffController controller = new StaffController(staffDAO);

        SwingUtilities.invokeLater(() -> {
            new StaffListPage(controller).setVisible(true);
        });
    }
}
