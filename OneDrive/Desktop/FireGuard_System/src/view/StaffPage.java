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
import model.Staff;

public class StaffPage {
    private JFrame frame;
    private StaffController controller;

    public StaffPage(StaffController controller) {
        this.controller = controller;
            // Create a JFrame (window).
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
                User User = null;
                AdminDashboardPage dashboardPage = new AdminDashboardPage(User);
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
             
            // Image panel with BorderLayout
            JPanel imagePanel = new JPanel(new BorderLayout());
            imagePanel.setBackground(Color.decode("#FFDEC8"));
            imagePanel.setBorder(BorderFactory.createLineBorder(Color.decode("#FFDEC8"), 1));
            imagePanel.add(stackPanel, BorderLayout.NORTH);
 
            // Control Panel at the top
            JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            controlPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
 
            // Content Panel in the center
            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBackground(Color.WHITE);
            contentPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
 
            JPanel panel = new JPanel();
            panel.setBackground(Color.decode("#FFDEC8"));
            panel.setBounds(300, 70, 1150, 710);
            frame.add(panel);

            JLabel staff = new JLabel("Add Staff");
            panel.setLayout(null); 
            staff.setBounds(80,30,250,30);
            staff.setBackground(Color.decode("#FFDEC8"));
            staff.setFont(new Font("Arial", Font.BOLD, 26)); 
            panel.add(staff);
            
            
            JLabel name = new JLabel("Name:");
            panel.setLayout(null); 
            name.setBounds(100,110,150,30);
            name.setBackground(Color.decode("#FFDEC8"));
            name.setFont(new Font("Arial", Font.BOLD, 18)); 
            panel.add(name);
            JTextField nameField = new JTextField();
            nameField.setLayout(null); 
            nameField.setBackground(Color.decode("#FFDEC8"));
            nameField.setBounds(300,110,300,35);
            nameField.setFont(new Font("Arial",Font.PLAIN, 18)); 
            nameField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  
            panel.add(nameField);

            JLabel staff_id = new JLabel("Staff ID:");
            panel.setLayout(null); 
            staff_id.setBounds(100,170,150,30);
            staff_id.setBackground(Color.decode("#FFDEC8"));
            staff_id.setFont(new Font("Arial", Font.BOLD, 18)); 
            panel.add(staff_id);
            JTextField staff_idField = new JTextField();
            staff_idField.setLayout(null); 
            staff_idField.setBackground(Color.decode("#FFDEC8"));
            staff_idField.setBounds(300,170,300,35);
            staff_idField.setFont(new Font("Arial",Font.PLAIN, 18)); 
            staff_idField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  
            panel.add(staff_idField);

            JLabel rank = new JLabel("Rank:");
            panel.setLayout(null); 
            rank.setBounds(100,230,150,30);
            rank.setBackground(Color.decode("#FFDEC8"));
            rank.setFont(new Font("Arial", Font.BOLD, 18)); 
            panel.add(rank);
            JTextField rankField = new JTextField();
            rankField.setLayout(null); 
            rankField.setBackground(Color.decode("#FFDEC8"));
            rankField.setBounds(300,230,300,35);
            rankField.setFont(new Font("Arial",Font.PLAIN, 18)); 
            rankField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  
            panel.add(rankField);

            JLabel age = new JLabel("Age:");
            panel.setLayout(null); 
            age.setBounds(100,290,150,30);
            age.setBackground(Color.decode("#FFDEC8"));
            age.setFont(new Font("Arial", Font.BOLD, 18)); 
            panel.add(age);
            JTextField ageField = new JTextField();
            ageField.setLayout(null); 
            ageField.setBackground(Color.decode("#FFDEC8"));
            ageField.setBounds(300,290,300,35);
            ageField.setFont(new Font("Arial",Font.PLAIN, 18)); 
            ageField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  
            panel.add(ageField);

            JLabel phone_number = new JLabel("Phone Number:");
            panel.setLayout(null); 
            phone_number.setBounds(100,350,180,30);
            phone_number.setBackground(Color.decode("#FFDEC8"));
            phone_number.setFont(new Font("Arial", Font.BOLD, 18)); 
            panel.add(phone_number);
            JTextField phone_numberField = new JTextField();
            phone_numberField.setLayout(null); 
            phone_numberField.setBackground(Color.decode("#FFDEC8"));
            phone_numberField.setBounds(300,350,300,35);
            phone_numberField.setFont(new Font("Arial",Font.PLAIN, 18)); 
            phone_numberField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  
            panel.add(phone_numberField);

            JLabel email = new JLabel("Email:");
            panel.setLayout(null); 
            email.setBounds(100,410,150,30);
            email.setBackground(Color.decode("#FFDEC8"));
            email.setFont(new Font("Arial", Font.BOLD, 18)); 
            panel.add(email);
            JTextField emailField = new JTextField();
            emailField.setLayout(null); 
            emailField.setBackground(Color.decode("#FFDEC8"));
            emailField.setBounds(300,410,300,35);
            emailField.setFont(new Font("Arial",Font.PLAIN, 18));
            emailField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  
            panel.add(emailField);

            JLabel address = new JLabel("Address:");
            panel.setLayout(null); 
            address.setBounds(100,470,150,30);
            address.setBackground(Color.decode("#FFDEC8"));
            address.setFont(new Font("Arial", Font.BOLD, 18)); 
            panel.add(address);
            JTextField addressField = new JTextField();
            addressField.setLayout(null); 
            addressField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
            addressField.setBackground(Color.decode("#FFDEC8"));
            addressField.setBounds(300,470,300,35);
            addressField.setFont(new Font("Arial",Font.PLAIN, 18));
            panel.add(addressField);
            
            JLabel recruit_date = new JLabel("Recruit Date :");
            panel.setLayout(null); 
            recruit_date.setBounds(100,530,150,30);
            recruit_date.setBackground(Color.decode("#FFDEC8"));
            recruit_date.setFont(new Font("Arial", Font.BOLD, 18)); 
            panel.add(recruit_date);
            JTextField recruit_dateField = new JTextField();
            recruit_dateField.setLayout(null); 
            recruit_dateField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
            recruit_dateField.setBackground(Color.decode("#FFDEC8"));
            recruit_dateField.setBounds(300,530,300,35);
            recruit_dateField.setFont(new Font("Arial",Font.PLAIN, 18)); 
            panel.add(recruit_dateField);

            JButton AddStaff = new JButton("Add Staff");
            AddStaff.setBackground(Color.decode("#01520E"));
            AddStaff.setForeground(Color.WHITE);
            AddStaff.setFocusPainted(false);
            AddStaff.setFont(new Font("Arial",Font.PLAIN, 24)); 
            AddStaff.setBounds(100, 630, 270, 60);
            AddStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String staff_id = staff_idField.getText();
                String rank = rankField.getText();
                String age = ageField.getText();
                String phone_number = phone_numberField.getText();
                String email = emailField.getText();
                String address = addressField.getText();
                String recruit_date = recruit_dateField.getText();

                controller.addStaff(name, staff_id, rank, age, phone_number, email, address, recruit_date);

            }
        });
        panel.add(AddStaff);
        
         JButton DisplayButton = new JButton("Display Staff");
            DisplayButton.setBackground(Color.decode("#01520E"));
            DisplayButton.setForeground(Color.WHITE);
            DisplayButton.setFocusPainted(false);
            DisplayButton.setFont(new Font("Arial", Font.PLAIN, 24));
            DisplayButton.setBounds(500, 630, 270, 60);
            DisplayButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    StaffDAO staffDAO = new StaffDAOImpl();
                    StaffController staffController = new StaffController(staffDAO);
                    StaffListPage staffListPage = new StaffListPage(staffController);
                    staffListPage.setVisible(true);
                    frame.dispose(); 
                }
            });
            panel.add(DisplayButton);
            // Create a panel to add margins around the right panel
            JPanel rightPanelWithMargin = new JPanel(new BorderLayout());
            rightPanelWithMargin.setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80));
 
            // Add right panel with margins to the content panel
            contentPanel.add(rightPanelWithMargin, BorderLayout.CENTER);
 
            // Add panels to the frame
            frame.add(imagePanel, BorderLayout.WEST);
            frame.add(controlPanel, BorderLayout.NORTH);
            frame.add(contentPanel, BorderLayout.CENTER);
 
            // Set frame size and make it visible
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        }
    
    
    // Method to configure button styling
    private static void configureButton(JButton button) {
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setIconTextGap(20);
        button.setSize(new Dimension(180, 60));
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#FFDEC8"));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    private static Component createVerticalSpacing(int height) {
        return Box.createRigidArea(new Dimension(0, height));
    }

   

    // Method to set the visibility of the frame
    public void setVisible(boolean isVisible) {
        frame.setVisible(isVisible);
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Initialize DAO and Controller
        UserDAO userDAO = (UserDAO) new UserDAOImpl();
        
        RegisterController controller = new RegisterController(userDAO);

        // Create and display the Register Page
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RegisterPage(controller).setVisible(true);
            }
        });
    }
}

       

