import Dao.InventoryDAO;
import Dao.InventoryDAOImpl;
import dao.OperationDAO;
import dao.OperationDAOImpl;
import Dao.User;
import Dao.UserDAO;
import Dao.UserDAOImpl;
import controller.DashboardController;
import controller.InventoryController;
import controller.OperationController;
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

public class OperationPage {
    private JFrame frame;
    private OperationController controller;

    public OperationPage(OperationController controller) {
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
        staffButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    StaffDAO staffDAO = new StaffDAOImpl();
                    StaffController staffController = new StaffController(staffDAO);
                    StaffListUserPage staffListUserPage = new StaffListUserPage(staffController);
                    staffListUserPage.setVisible(true);
                    frame.dispose(); 
                }
            });

        // Operation button with text and image
        String operationIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download (6).png";
        ImageIcon operationIcon = new ImageIcon(new ImageIcon(operationIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        JButton operationButton = new JButton("Operation", operationIcon);
        configureButton(operationButton);

        // Inventory button with text and image
        String inventoryIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download (2).png";
        ImageIcon inventoryIcon = new ImageIcon(new ImageIcon(inventoryIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        JButton inventoryButton = new JButton("Inventory", inventoryIcon);
        configureButton(inventoryButton);
        inventoryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    InventoryDAO inventoryDAO = new InventoryDAOImpl();  
                    InventoryController inventoryController = new InventoryController(inventoryDAO); 
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

        // Content Panel in the center
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#FFDEC8"));
        panel.setBounds(300, 70, 1150, 710);
        frame.add(panel);

        JLabel inventory = new JLabel("Operation");
        panel.setLayout(null);
        inventory.setBounds(80, 30, 250, 30);
        inventory.setBackground(Color.decode("#FFDEC8"));
        inventory.setFont(new Font("Arial", Font.BOLD, 26));
        panel.add(inventory);

        JLabel Incident = new JLabel("Incident ID:");
        panel.setLayout(null);
        Incident.setBounds(50, 110, 150, 30);
        Incident.setBackground(Color.decode("#FFDEC8"));
        Incident.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(Incident);
        JTextField IncidentField = new JTextField();
        IncidentField.setLayout(null);
        IncidentField.setBackground(Color.decode("#FFDEC8"));
        IncidentField.setBounds(250, 110, 250, 35);
        IncidentField.setFont(new Font("Arial", Font.PLAIN, 18));
        IncidentField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.add(IncidentField);

        JLabel caller = new JLabel("Name Of Caller:");
        panel.setLayout(null);
        caller.setBounds(50, 170, 150, 30);
        caller.setBackground(Color.decode("#FFDEC8"));
        caller.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(caller);
        JTextField callerField = new JTextField();
        callerField.setLayout(null);
        callerField.setBackground(Color.decode("#FFDEC8"));
        callerField.setBounds(250, 170, 250, 35);
        callerField.setFont(new Font("Arial", Font.PLAIN, 18));
        callerField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.add(callerField);

        JLabel typeOfIncident = new JLabel("Type Of Incident:");
        panel.setLayout(null);
        typeOfIncident.setBounds(50, 230, 150, 30);
        typeOfIncident.setBackground(Color.decode("#FFDEC8"));
        typeOfIncident.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(typeOfIncident);
        JTextField typeOfIncidentField = new JTextField();
        typeOfIncidentField.setLayout(null);
        typeOfIncidentField.setBackground(Color.decode("#FFDEC8"));
        typeOfIncidentField.setBounds(250, 230, 250, 35);
        typeOfIncidentField.setFont(new Font("Arial", Font.PLAIN, 18));
        typeOfIncidentField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.add(typeOfIncidentField);

        JLabel Cause = new JLabel("Cause:");
        panel.setLayout(null);
        Cause.setBounds(50, 290, 150, 30);
        Cause.setBackground(Color.decode("#FFDEC8"));
        Cause.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(Cause);
        JTextField CauseField = new JTextField();
        CauseField.setLayout(null);
        CauseField.setBackground(Color.decode("#FFDEC8"));
        CauseField.setBounds(250, 290, 250, 35);
        CauseField.setFont(new Font("Arial", Font.PLAIN, 18));
        CauseField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.add(CauseField);

        JLabel Injured = new JLabel("No Of Injured:");
        panel.setLayout(null); 
            Injured.setBounds(50,350,150,30);
            Injured.setBackground(Color.decode("#FFDEC8"));
            Injured.setFont(new Font("Arial", Font.BOLD, 18)); 
            panel.add(Injured);
            JTextField InjuredField = new JTextField();
            InjuredField.setLayout(null); 
            InjuredField.setBackground(Color.decode("#FFDEC8"));
            InjuredField.setBounds(250,350,250,35);
            InjuredField.setFont(new Font("Arial",Font.PLAIN, 18));
            InjuredField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  
            panel.add(InjuredField);

            JLabel NoOfCasualties = new JLabel("No Of Casualties:");
            panel.setLayout(null); 
            NoOfCasualties.setBounds(50,410,170,30);
            NoOfCasualties.setBackground(Color.decode("#FFDEC8"));
            NoOfCasualties.setFont(new Font("Arial", Font.BOLD, 18)); 
            panel.add(NoOfCasualties);
            JTextField NoOfCasualtiesField = new JTextField();
            NoOfCasualtiesField.setLayout(null); 
            NoOfCasualtiesField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
            NoOfCasualtiesField.setBackground(Color.decode("#FFDEC8"));
            NoOfCasualtiesField.setBounds(250,410,250,35);
            NoOfCasualtiesField.setFont(new Font("Arial",Font.PLAIN, 18)); 
            panel.add(NoOfCasualtiesField);

            JLabel TimeOfIncident = new JLabel("Time of Incident:");
            panel.setLayout(null); 
            TimeOfIncident.setBounds(550,110,150,30);
            TimeOfIncident.setBackground(Color.decode("#FFDEC8"));
            TimeOfIncident.setFont(new Font("Arial", Font.BOLD, 18)); 
            panel.add(TimeOfIncident);
            JTextField TimeOfIncidentField = new JTextField();
            TimeOfIncidentField.setLayout(null); 
            TimeOfIncidentField.setBackground(Color.decode("#FFDEC8"));
            TimeOfIncidentField.setBounds(750,110,250,35);
            TimeOfIncidentField.setFont(new Font("Arial",Font.PLAIN, 18)); 
            TimeOfIncidentField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  
            panel.add(TimeOfIncidentField);

            JLabel DateOfIncident = new JLabel("Date Of Incident:");
            panel.setLayout(null); 
            DateOfIncident.setBounds(550,170,150,30);
            DateOfIncident.setBackground(Color.decode("#FFDEC8"));
            DateOfIncident.setFont(new Font("Arial", Font.BOLD, 18)); 
            panel.add(DateOfIncident);
            JTextField DateOfIncidentField = new JTextField();
            DateOfIncidentField.setLayout(null); 
            DateOfIncidentField.setBackground(Color.decode("#FFDEC8"));
            DateOfIncidentField.setBounds(750,170,250,35);
            DateOfIncidentField.setFont(new Font("Arial",Font.PLAIN, 18)); 
            DateOfIncidentField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  
            panel.add(DateOfIncidentField);

            JLabel PlaceOfIncident = new JLabel("Place Of Incident:");
            panel.setLayout(null); 
            PlaceOfIncident.setBounds(550,230,180,30);
            PlaceOfIncident.setBackground(Color.decode("#FFDEC8"));
            PlaceOfIncident.setFont(new Font("Arial", Font.BOLD, 18)); 
            panel.add(PlaceOfIncident);
            JTextField PlaceOfIncidentField = new JTextField();
            PlaceOfIncidentField.setLayout(null); 
            PlaceOfIncidentField.setBackground(Color.decode("#FFDEC8"));
            PlaceOfIncidentField.setBounds(750,230,250,35);
            PlaceOfIncidentField.setFont(new Font("Arial",Font.PLAIN, 18)); 
            PlaceOfIncidentField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  
            panel.add(PlaceOfIncidentField);


            JLabel DamageOfProperty = new JLabel("Damage Of Property:");
            panel.setLayout(null); 
            DamageOfProperty.setBounds(550,290,250,30);
            DamageOfProperty.setBackground(Color.decode("#FFDEC8"));
            DamageOfProperty.setFont(new Font("Arial", Font.BOLD, 18)); 
            panel.add(DamageOfProperty);
            JTextField DamageOfPropertyField = new JTextField();
            DamageOfPropertyField.setLayout(null); 
            DamageOfPropertyField.setBackground(Color.decode("#FFDEC8"));
            DamageOfPropertyField.setBounds(750,290,250,35);
            DamageOfPropertyField.setFont(new Font("Arial",Font.PLAIN, 18)); 
            DamageOfPropertyField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  
            panel.add(DamageOfPropertyField);

          

            JButton SaveReport = new JButton("Save Operation");
            SaveReport.setLayout(null);
            SaveReport.setBackground(Color.decode("#01520E"));
            SaveReport.setForeground(Color.WHITE);
            SaveReport.setFocusPainted(false);
            SaveReport.setFont(new Font("Arial",Font.PLAIN, 24)); 
            SaveReport.setBounds(400, 640, 370, 60);
            SaveReport.addActionListener(new ActionListener() {
             @Override
                public void actionPerformed(ActionEvent e) {
                    int incidentId = Integer.parseInt(IncidentField.getText());
                    String nameOfCaller = callerField.getText();
                    String typeOfIncident = typeOfIncidentField.getText();
                    String cause = CauseField.getText();
                    int noOfInjured = Integer.parseInt(InjuredField.getText());
                    int noOfCasualties = Integer.parseInt(NoOfCasualtiesField.getText());
                    String timeOfIncident = TimeOfIncidentField.getText();
                    String dateOfIncident = DateOfIncidentField.getText();
                    String placeOfIncident = PlaceOfIncidentField.getText();
                    String damageOfProperty = DamageOfPropertyField.getText();
                    controller.insertOperation(incidentId, nameOfCaller, typeOfIncident, cause, noOfInjured,noOfCasualties, timeOfIncident, dateOfIncident, placeOfIncident, damageOfProperty);
            }
            });
        panel.add(SaveReport);
        
         JButton DisplayButton = new JButton("Display Report");
            DisplayButton.setBackground(Color.decode("#01520E"));
            DisplayButton.setForeground(Color.WHITE);
            DisplayButton.setFocusPainted(false);
            DisplayButton.setFont(new Font("Arial", Font.PLAIN, 24));
            DisplayButton.setBounds(500, 640, 270, 60);
            DisplayButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    OperationDAO operationDAO = new OperationDAOImpl();
                    OperationController operationController = new OperationController(operationDAO);
                 //   OperationListPage operationListPage = new OperationListPage(operationController);
                 //   operationListPage.setVisible(true);
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
        OperationDAO operationDAO = new OperationDAOImpl();
        OperationController controller = new OperationController(operationDAO);

        // Create and display the Register Page
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OperationPage(controller).setVisible(true);
            }
        });
    }
}

       

