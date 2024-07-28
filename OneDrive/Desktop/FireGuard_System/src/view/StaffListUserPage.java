import Dao.InventoryDAO;
import Dao.InventoryDAOImpl;
import Dao.User;
import Dao.UserDAO;
import Dao.UserDAOImpl;
import controller.DashboardController;
import dao.OperationDAO;
import dao.OperationDAOImpl;
import dao.StaffDAO;
import dao.StaffDAOImpl;
import controller.InventoryController;
import controller.OperationController;
import controller.ScheduleController;
import controller.StaffController;
import dao.ScheduleDAO;
import dao.ScheduleDAOImpl;
import model.Staff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StaffListUserPage {
    private JFrame frame;
    private StaffController controller;
    private JTable staffTable;

    public StaffListUserPage(StaffController controller) {
        this.controller = controller;

        SwingUtilities.invokeLater(() -> {
            // Create a JFrame (window)
        frame = new JFrame("FireGuard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        
        ImageIcon logo = new ImageIcon(getClass().getResource("/images/Logo.png"));
        frame.setIconImage(logo.getImage());

            // Image Panel on the left
            String imagePath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\SystemLogo.png";
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image originalImage = originalIcon.getImage();
            int newWidth = 210;
            int newHeight = 170;
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(resizedIcon);

            // Dashboard button with text and image
            String dashboardIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download (5).png";
            ImageIcon dashboardIcon = new ImageIcon(new ImageIcon(dashboardIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            JButton dashboardButton = new JButton("Dashboard", dashboardIcon);
            configureButton(dashboardButton);
            dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDAO userDAO = new UserDAOImpl();
                DashboardController dashboardController = new DashboardController(userDAO);
                User user = null; // Initialize user if needed
                DashboardPage dashboardPage = new DashboardPage(user);
                dashboardPage.setVisible(true);
                frame.dispose();
            }
        });

            // Schedule button with text and a different image
            String scheduleIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download (1).png";
            ImageIcon scheduleIcon = new ImageIcon(new ImageIcon(scheduleIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            JButton scheduleButton = new JButton("Schedule", scheduleIcon);
            configureButton(scheduleButton);
            scheduleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
                    ScheduleController scheduleController = new ScheduleController(scheduleDAO);
                    ScheduleListUserPage scheduleListUserPage = new ScheduleListUserPage(scheduleController);
                    scheduleListUserPage.setVisible(true);
                    frame.dispose(); 
                }
            });

            // Staff button with text and image
            String staffIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download.png";
            ImageIcon staffIcon = new ImageIcon(new ImageIcon(staffIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            JButton staffButton = new JButton("Staff", staffIcon);
            configureButton(staffButton);

            // Operation button with text and image
            String operationIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download (6).png";
            ImageIcon operationIcon = new ImageIcon(new ImageIcon(operationIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            JButton operationButton = new JButton("Operation", operationIcon);
            configureButton(operationButton);
            operationButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    OperationDAO operationDAO = new OperationDAOImpl();  // Replace with your implementation
                    OperationController operationController = new OperationController(operationDAO);  // Replace with your implementation
                    OperationPage operationPage = new OperationPage(operationController);
                    operationPage.setVisible(true);
                    frame.dispose();
                }
            });

            // Inventory button with text and image
            String inventoryIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download (2).png";
            ImageIcon inventoryIcon = new ImageIcon(new ImageIcon(inventoryIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            JButton inventoryButton = new JButton("Inventory", inventoryIcon);
            configureButton(inventoryButton);
            inventoryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    InventoryDAO inventoryDAO = new InventoryDAOImpl();  // Replace with your implementation
                    InventoryController inventoryController = new InventoryController(inventoryDAO);  // Replace with your implementation
                    InventoryPage inventoryPage = new InventoryPage(inventoryController);
                    inventoryPage.setVisible(true);
                    frame.dispose();
                }
            });

            // Report button with text and image
            String reportIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download (7).png";
            ImageIcon reportIcon = new ImageIcon(new ImageIcon(reportIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            JButton reportButton = new JButton("Reports", reportIcon);
            configureButton(reportButton);
            reportButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    OperationDAO operationDAO = new OperationDAOImpl();  // Replace with your implementation
                    OperationController operationController = new OperationController(operationDAO);  // Replace with your implementation
                    ReportPage reportPage = new ReportPage(operationController);
                    reportPage.setVisible(true);
                    frame.dispose();
                }
            });

            // Create a panel with BoxLayout to stack image and buttons vertically
            JPanel stackPanel = new JPanel();
            stackPanel.setLayout(new BoxLayout(stackPanel, BoxLayout.Y_AXIS));
            stackPanel.setBackground(Color.decode("#FFDEC8"));
            stackPanel.add(imageLabel);
            stackPanel.add(createVerticalSpacing(20));
            stackPanel.add(dashboardButton);
            stackPanel.add(createVerticalSpacing(20));
            stackPanel.add(scheduleButton);
            stackPanel.add(createVerticalSpacing(20));
            stackPanel.add(staffButton);
            stackPanel.add(createVerticalSpacing(20));
            stackPanel.add(operationButton);
            stackPanel.add(createVerticalSpacing(20));
            stackPanel.add(inventoryButton);
            stackPanel.add(createVerticalSpacing(20));
            stackPanel.add(reportButton);

            // Image panel with BorderLayout
            JPanel imagePanel = new JPanel(new BorderLayout());
            imagePanel.setBackground(Color.decode("#FFDEC8"));
            imagePanel.setBorder(BorderFactory.createLineBorder(Color.decode("#FFDEC8"), 1));
            imagePanel.add(stackPanel, BorderLayout.NORTH);

            // Control Panel at the top
            JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            controlPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

            // Content Panel in the center with BorderLayout for proper component placement
            JPanel contentPanel = new JPanel(new GridBagLayout());
            contentPanel.setBackground(Color.WHITE);
//            contentPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#FFDEC8"), 1));

            // Create the panel with the specified size
            JPanel tablePanel = new JPanel(new BorderLayout());
            tablePanel.setPreferredSize(new Dimension(1070, 670));
            tablePanel.setBorder(BorderFactory.createLineBorder(Color.decode("#FFDEC8"), 15));
            tablePanel.setBackground(Color.decode("#FFDEC8"));
                
            JLabel titleLabel = new JLabel("Staff", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
            tablePanel.add(titleLabel, BorderLayout.NORTH);
            
            // Table model with column names
            String[] columnNames = {"Staff ID", "Name", "Rank", "Age", "Phone Number", "Email", "Address", "Recruit Date"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            staffTable = new JTable(tableModel);
            staffTable.setBackground(Color.decode("#FFD3AD"));
            JScrollPane scrollPane = new JScrollPane(staffTable);
            tablePanel.add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the table panel

            updateStaffTable();

            // Add table panel to the content panel with GridBagLayout constraints
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            contentPanel.add(tablePanel, gbc);

            // Add panels to the frame
            frame.add(imagePanel, BorderLayout.WEST);
            frame.add(controlPanel, BorderLayout.NORTH);
            frame.add(contentPanel, BorderLayout.CENTER); // Add content panel to the frame

            // Set frame size and make it visible
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        });
    }

    public void updateStaffTable() {
        List<Staff> staffList = controller.getAllStaffs();
        DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
        model.setRowCount(0); // Clear existing rows
        for (Staff item : staffList) {
            model.addRow(new Object[]{
                    item.getStaff_id(),
                    item.getName(),
                    item.getRank(),
                    item.getAge(),
                    item.getPhone_number(),
                    item.getEmail(),
                    item.getAddress(),
                    item.getRecruit_date()
            });
        }
    }

    // Method configure button styling
    private static void configureButton(JButton button) {
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setIconTextGap(20);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#FFDEC8"));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    // Method to create vertical spacing
    private static Component createVerticalSpacing(int height) {
        return Box.createRigidArea(new Dimension(0, height));
    }

    public void setVisible(boolean isVisible) {
        frame.setVisible(isVisible);
    }

    public static void main(String[] args) {
        StaffDAO staffDAO = new StaffDAOImpl();
        StaffController controller = new StaffController(staffDAO);
        new StaffListUserPage(controller);
    }
}
