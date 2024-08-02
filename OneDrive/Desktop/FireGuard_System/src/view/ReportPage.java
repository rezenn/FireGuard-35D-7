import Dao.DashboardDAO;
import Dao.InventoryDAO;
import Dao.InventoryDAOImpl;
import Dao.User;
import Dao.UserDAO;
import Dao.UserDAOImpl;
import controller.DashboardController;
import controller.InventoryController;
import controller.OperationController;
import controller.ScheduleController;
import controller.StaffController;
import dao.OperationDAO;
import dao.OperationDAOImpl;
import dao.ScheduleDAO;
import dao.ScheduleDAOImpl;
import dao.StaffDAO;
import dao.StaffDAOImpl;
import model.Operation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportPage {
    private JFrame frame;
    private JList<String> operationList;
    private JTextArea detailTextArea;
    private OperationController operationController;

    public ReportPage(OperationController controller) {
        this.operationController = controller;
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
         

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
                 DashboardDAO dashboardDAO = new DashboardDAOImpl();
                DashboardController dashboardController = new DashboardController(dashboardDAO);
                User User = null;
                DashboardPage dashboardPage = new DashboardPage(User);
                dashboardPage.setVisible(true);
                frame.dispose();
            }
        });

        // Schedule button with text and a different image
        String scheduleIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download (1).png";
        ImageIcon scheduleIcon = new ImageIcon(new ImageIcon(scheduleIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        JButton scheduleButton = new JButton("Schedule", scheduleIcon);
        configureButton(scheduleButton);
        scheduleButton.addActionListener(e -> {
            ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
            ScheduleController scheduleController = new ScheduleController(scheduleDAO);
            ScheduleListUserPage scheduleListUserPage = new ScheduleListUserPage(scheduleController);
            scheduleListUserPage.setVisible(true);
            frame.dispose();
        });

        // Staff button with text and image
        String staffIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download.png";
        ImageIcon staffIcon = new ImageIcon(new ImageIcon(staffIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        JButton staffButton = new JButton("Staff", staffIcon);
        configureButton(staffButton);
        staffButton.addActionListener(e -> {
            StaffDAO staffDAO = new StaffDAOImpl();
            StaffController staffController = new StaffController(staffDAO);
            StaffListUserPage staffListUserPage = new StaffListUserPage(staffController);
            staffListUserPage.setVisible(true);
            frame.dispose();
        });

        // Operation button with text and image
        String operationIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download (6).png";
        ImageIcon operationIcon = new ImageIcon(new ImageIcon(operationIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        JButton operationButton = new JButton("Operation", operationIcon);
        configureButton(operationButton);
        operationButton.addActionListener(e -> {
            OperationDAO operationDAO = new OperationDAOImpl();
            OperationController operationController = new OperationController(operationDAO);
            OperationPage operationPage = new OperationPage(operationController);
            operationPage.setVisible(true);
            frame.dispose();
        });

        // Inventory button with text and image
        String inventoryIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download (2).png";
        ImageIcon inventoryIcon = new ImageIcon(new ImageIcon(inventoryIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        JButton inventoryButton = new JButton("Inventory", inventoryIcon);
        configureButton(inventoryButton);
        inventoryButton.addActionListener(e -> {
            InventoryDAO inventoryDAO = new InventoryDAOImpl();
            InventoryController inventoryController = new InventoryController(inventoryDAO);
            InventoryPage inventoryPage = new InventoryPage(inventoryController);
            inventoryPage.setVisible(true);
            frame.dispose();
        });

        // Report button with text and image
        String reportIconPath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\download (7).png";
        ImageIcon reportIcon = new ImageIcon(new ImageIcon(reportIconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        JButton reportButton = new JButton("Reports", reportIcon);
        configureButton(reportButton);
        reportButton.addActionListener(e -> {
            // Avoid reloading the ReportPage from the button click
            // Commented out to prevent recursive loading
            // ReportPage reportPage = new ReportPage(operationController);
            // reportPage.setVisible(true);
            // frame.dispose();
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

        // Create a panel to hold the list and details
        JPanel databaseContentPanel = createDatabaseContentPanel();

        // Center the databaseContentPanel in the frame
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        centerPanel.add(databaseContentPanel, gbc);

        // Add panels to the frame
        frame.add(imagePanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Load operations and populate the JList
        loadOperations();

        // Set frame size and make it visible
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

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

    private static Component createVerticalSpacing(int height) {
        return Box.createRigidArea(new Dimension(0, height));
    }

  private JPanel createDatabaseContentPanel() {
    JPanel panel = new JPanel(new BorderLayout());

    // Create and configure the JList for displaying operation titles
    operationList = new JList<>();
    operationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    operationList.addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            String selectedValue = operationList.getSelectedValue();
            if (selectedValue != null) {
                try {
                    displayOperationDetails(selectedValue);
                } catch (SQLException ex) {
                    Logger.getLogger(ReportPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
    operationList.setBackground(Color.decode("#FFDEC8"));
    operationList.setFont(new Font("Arial", Font.BOLD, 18));


    JScrollPane listScrollPane = new JScrollPane(operationList);
    listScrollPane.setPreferredSize(new Dimension(150, 400));

    // Create and configure the JTextArea for displaying operation details
    detailTextArea = new JTextArea();
    detailTextArea.setEditable(false);
    detailTextArea.setLineWrap(true);
    detailTextArea.setWrapStyleWord(true);
    detailTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
    detailTextArea.setBackground(Color.decode("#FFDEC8"));

    JScrollPane detailScrollPane = new JScrollPane(detailTextArea);

    // Create a panel to hold the list and details
    JPanel contentPanel = new JPanel(new BorderLayout());
    contentPanel.setBackground(Color.WHITE);
    contentPanel.add(listScrollPane, BorderLayout.WEST);
    contentPanel.add(detailScrollPane, BorderLayout.CENTER);

    // Add padding to the contentPanel
    contentPanel.setPreferredSize(new Dimension(100, 100)); 
    contentPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

    // Add the contentPanel to the databaseContentPanel
    panel.add(contentPanel, BorderLayout.CENTER);

    return panel;
}


    private void loadOperations() {
        try {
            List<Operation> operations = operationController.getAllOperations();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Operation op : operations) {
                // Assuming incidentId is used as title
                listModel.addElement(String.valueOf(op.getIncidentId()));
            }
            operationList.setModel(listModel);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to load operations", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayOperationDetails(String incidentId) throws SQLException {
        try {
            int id = Integer.parseInt(incidentId);
            Operation operation = operationController.getAllOperations().stream()
                .filter(op -> op.getIncidentId() == id)
                .findFirst()
                .orElse(null);

            if (operation != null) {
                StringBuilder details = new StringBuilder();
                details.append("Incident ID: ").append(operation.getIncidentId()).append("\n")
                        .append("Name of Caller: ").append(operation.getNameOfCaller()).append("\n")
                        .append("Type of Incident: ").append(operation.getTypeOfIncident()).append("\n")
                        .append("Cause: ").append(operation.getCause()).append("\n")
                        .append("Number of Injured: ").append(operation.getNoOfInjured()).append("\n")
                        .append("Number of Casualties: ").append(operation.getNoOfCasualties()).append("\n")
                        .append("Time of Incident: ").append(operation.getTimeOfIncident()).append("\n")
                        .append("Date of Incident: ").append(operation.getDateOfIncident()).append("\n")
                        .append("Place of Incident: ").append(operation.getPlaceOfIncident()).append("\n")
                        .append("Damage of Property: ").append(operation.getDamageOfProperty());

                detailTextArea.setText(details.toString());
            } else {
                detailTextArea.setText("No details available.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Invalid incident ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Initialize the DAO and controller
        OperationDAO operationDAO = new OperationDAOImpl();
        OperationController operationController = new OperationController(operationDAO);
        new ReportPage(operationController);
    }

    public void setVisible(boolean isVisible) {
        frame.setVisible(isVisible);
    }
}
