import controller.ScheduleController;
import dao.ScheduleDAO;
import dao.ScheduleDAOImpl;
import model.Schedule;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ScheduleListPage {
    private JFrame frame;
    private ScheduleController controller;
    private JTable scheduleTable;

    public ScheduleListPage(ScheduleController controller) {
        this.controller = controller;
                
        SwingUtilities.invokeLater(() -> {
        
        frame = new JFrame("FireGuard");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        ImageIcon logo = new ImageIcon(getClass().getResource("/images/Logo.png"));
        frame.setIconImage(logo.getImage());

        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);
        
        JLabel titleLabel = new JLabel("Schedule", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Table model with column names
        String[] columnNames = {"Name", "Rank", "Phone Number","Email", "Date", "Shift"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        scheduleTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(scheduleTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JButton deleteButton = new JButton("Delete");
            panel.add(deleteButton, BorderLayout.SOUTH);
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteSelectedRow();
                }
            });
            
        updateScheduleTable();
        
        frame.setSize(800, 600);
            frame.setLocationRelativeTo(null); // Center the window
            frame.setVisible(true);
        });
    }
    public void updateScheduleTable() {
        List<Schedule> scheduleList = controller.getAllSchedules();
        DefaultTableModel model = (DefaultTableModel) scheduleTable.getModel();
        model.setRowCount(0); // Clear existing rows
        for (Schedule item : scheduleList) {
            model.addRow(new Object[]{
                    item.getName(),
                    item.getRank(),
                    item.getPhone_number(),
                    item.getEmail(),
                    item.getDate(),
                    item.getShift()
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
        int selectedRow = scheduleTable.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) scheduleTable.getModel();
            String phoneNumber = (String) model.getValueAt(selectedRow, 2); // Assuming phone number is in the 3rd column
            
            // Remove the row from the table
            model.removeRow(selectedRow);
            
            // Remove the entry from the database
            controller.deleteSchedule(phoneNumber); // You need to implement this method in the controller
        } else {
            JOptionPane.showMessageDialog(frame, "No row selected for deletion.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        ScheduleController controller = new ScheduleController(scheduleDAO);

        SwingUtilities.invokeLater(() -> {
            new ScheduleListPage(controller).setVisible(true);
        });
    }
}
