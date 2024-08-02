import Dao.DashboardDAO;
import Dao.User;
import Dao.UserDAO;
import Dao.UserDAOImpl;
import controller.DashboardController;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

            String logoutIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\log-out.png";
            ImageIcon logoutIcon = new ImageIcon(new ImageIcon(logoutIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            JButton logoutButton = new JButton("Logout", logoutIcon);
            configureButton(logoutButton);
            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to log out?", "Logout Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        UserDAO userDAO = new UserDAOImpl();  
                        LoginController loginController = new LoginController();  
                        LoginPage loginPage = new LoginPage(loginController);
                        loginPage.setVisible(true);
                        frame.dispose();
                    }
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
            stackPanel.add(logoutButton);
            stackPanel.add(createVerticalSpacing(20));
          
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

            
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedDate = currentDateTime.format(dateFormatter);
            String formattedTime = currentDateTime.format(timeFormatter);

            JLabel dateLabel = new JLabel("Date: " + formattedDate);
            dateLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
            JLabel timeLabel = new JLabel("Time: " + formattedTime);
            timeLabel.setFont(new Font("Helvetica", Font.BOLD, 16));

            controlPanel.add(dateLabel);
            controlPanel.add(timeLabel);
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LocalDateTime now = LocalDateTime.now();
                    String updatedTime = now.format(timeFormatter);
                    timeLabel.setText("Time: " + updatedTime);
                }
            });
            timer.start();

           
            int staffCount = 0;
            try {
                DashboardDAO dashboardDAO = new DashboardDAOImpl();
                DashboardController dashboardController = new DashboardController(dashboardDAO);
                staffCount = dashboardController.getStaffCount();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            
            int inventoryCount = 0;
            try {
                DashboardDAO dashboardDAO = new DashboardDAOImpl();
                DashboardController dashboardController = new DashboardController(dashboardDAO);
                inventoryCount = dashboardController.getInventoryCount();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            int reportCount = 0;
            try {
                DashboardDAO dashboardDAO = new DashboardDAOImpl();
                DashboardController dashboardController = new DashboardController(dashboardDAO);
                reportCount = dashboardController.getReportCount();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            int scheduleCount = 0;
            try {
                DashboardDAO dashboardDAO = new DashboardDAOImpl();
                DashboardController dashboardController = new DashboardController(dashboardDAO);
                scheduleCount = dashboardController.getScheduleCount();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            
            JPanel staffShow = new JPanel();
            staffShow.setBackground(Color.decode("#FFDEC8"));
            staffShow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            staffShow.setBounds(300, 150, 250, 130);
            frame.add(staffShow);
            
           JTextArea staffCountArea = new JTextArea("Total Staffs\n\n" +"         " + staffCount);
           staffCountArea.setBounds(50, 70, 150, 150);
           staffCountArea.setFont(new Font("Verdana", Font.BOLD, 24));
           staffCountArea.setOpaque(false); 
           staffCountArea.setEditable(false); 
           staffCountArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT); 
           staffShow.add(staffCountArea);
           
            JPanel inventoryShow = new JPanel();
            inventoryShow.setBackground(Color.decode("#FFDEC8"));
            inventoryShow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            inventoryShow.setBounds(700, 150, 250, 130);
            frame.add(inventoryShow);
            
           JTextArea inventoryCountShow = new JTextArea("Total Inventories\n\n" +"            " +inventoryCount);
           inventoryCountShow.setBounds(50, 70, 150, 150);
           inventoryCountShow.setFont(new Font("Verdana", Font.BOLD, 24));
           inventoryCountShow.setOpaque(false); 
           inventoryCountShow.setEditable(false); 
           inventoryCountShow.setAlignmentX(JTextArea.CENTER_ALIGNMENT); 
           inventoryShow.add(inventoryCountShow);
           
           JPanel reportShow = new JPanel();
            reportShow.setBackground(Color.decode("#FFDEC8"));
            reportShow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            reportShow.setBounds(1100, 150, 250, 130);
            frame.add(reportShow);
            
           JTextArea reportCountShow = new JTextArea("Total Reports\n\n" +"           " +reportCount);
           reportCountShow.setBounds(50, 70, 150, 150);
           reportCountShow.setFont(new Font("Verdana", Font.BOLD, 24));
           reportCountShow.setOpaque(false); 
           reportCountShow.setEditable(false); 
           reportCountShow.setAlignmentX(JTextArea.CENTER_ALIGNMENT); 
           reportShow.add(reportCountShow);
           
           JPanel scheduleShow = new JPanel();
            scheduleShow.setBackground(Color.decode("#FFDEC8"));
            scheduleShow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            scheduleShow.setBounds(300, 350, 250, 130);
            frame.add(scheduleShow);
            
           JTextArea scheduleCountShow = new JTextArea("Total Schedules\n\n" +"           " +scheduleCount);
           scheduleCountShow.setBounds(50, 70, 150, 150);
           scheduleCountShow.setFont(new Font("Verdana", Font.BOLD, 24));
           scheduleCountShow.setOpaque(false); 
           scheduleCountShow.setEditable(false); 
           scheduleCountShow.setAlignmentX(JTextArea.CENTER_ALIGNMENT); 
           scheduleShow.add(scheduleCountShow);
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
