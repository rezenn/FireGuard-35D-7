import Dao.User;
import controller.ScheduleController;
import controller.StaffController;
import dao.ScheduleDAO;
import dao.ScheduleDAOImpl;
import dao.StaffDAO;
import dao.StaffDAOImpl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboardPage {
    private JFrame frame;

    public AdminDashboardPage(User user) {
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
                    SchedulePage schedulePage = new SchedulePage(scheduleController);
                    schedulePage.setVisible(true);
                    frame.dispose();
                }
            });

            // Staff button with text and image
            String staffIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download.png";
            ImageIcon staffIcon = new ImageIcon(new ImageIcon(staffIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            JButton staffButton = new JButton("Staff", staffIcon);
            configureButton(staffButton);
            staffButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    StaffDAO staffDAO = new StaffDAOImpl();
                    StaffController staffController = new StaffController(staffDAO);
                    StaffPage staffPage = new StaffPage(staffController);
                    staffPage.setVisible(true);
                    frame.dispose();
                }
            });

            // Operation button with text and image
//            String operationIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download (6).png";
//            ImageIcon operationIcon = new ImageIcon(new ImageIcon(operationIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
//            JButton operationButton = new JButton("Operation", operationIcon);
//            configureButton(operationButton);
//            operationButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    OperationDAO operationDAO = new OperationDAOImpl();
//                    OperationController operationController = new OperationController(operationDAO);
//                    OperationPage operationPage = new OperationPage(operationController);
//                    operationPage.setVisible(true);
//                    frame.dispose();
//                }
//            });

            // Inventory button with text and image
//            String inventoryIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download (2).png";
//            ImageIcon inventoryIcon = new ImageIcon(new ImageIcon(inventoryIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
//            JButton inventoryButton = new JButton("Inventory", inventoryIcon);
//            configureButton(inventoryButton);
//            inventoryButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    InventoryDAO inventoryDAO = new InventoryDAOImpl();
//                    InventoryController inventoryController = new InventoryController(inventoryDAO);
//                    InventoryPage inventoryPage = new InventoryPage(inventoryController);
//                    inventoryPage.setVisible(true);
//                    frame.dispose();
//                }
//            });
//
//            // Report button with text and image
//            String reportIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download (7).png";
//            ImageIcon reportIcon = new ImageIcon(new ImageIcon(reportIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
//            JButton reportButton = new JButton("Reports", reportIcon);
//            configureButton(reportButton);

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
//            stackPanel.add(operationButton);
//            stackPanel.add(createVerticalSpacing(20));
//            stackPanel.add(inventoryButton);
//            stackPanel.add(createVerticalSpacing(20));
//            stackPanel.add(reportButton);

            // Image panel with BorderLayout
            JPanel imagePanel = new JPanel(new BorderLayout());
            imagePanel.setBackground(Color.decode("#FFDEC8"));
            imagePanel.setBorder(BorderFactory.createLineBorder(Color.decode("#FFDEC8"), 1));
            imagePanel.add(stackPanel, BorderLayout.NORTH);

            // Control Panel at the top
            JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            controlPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

            // Content Panel in the center with null layout for absolute positioning
            JPanel contentPanel = new JPanel(null);
            contentPanel.setBackground(Color.WHITE);
            contentPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#FFDEC8"), 1));

            // Add panels to the frame
            frame.add(imagePanel, BorderLayout.WEST);
            frame.add(controlPanel, BorderLayout.NORTH);
            frame.add(contentPanel, BorderLayout.CENTER);

            // Set frame size and make it visible
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        });
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
        // Assuming User is already defined and available
        User user = new User();
        new AdminDashboardPage(user).setVisible(true);
    }
}
