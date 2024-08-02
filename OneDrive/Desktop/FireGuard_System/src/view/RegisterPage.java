import Dao.UserDAO;
import Dao.UserDAOImpl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegisterPage {
    private JFrame frame;
    private RegisterController controller;

    public RegisterPage(RegisterController controller) {
        this.controller = controller;

        // Create a JFrame (window)..
        frame = new JFrame("FireGuard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        ImageIcon logo = new ImageIcon(getClass().getResource("/images/Logo.png"));
        frame.setIconImage(logo.getImage());

        Color bgColor = Color.decode("#FFDEC8");
        frame.getContentPane().setBackground(bgColor);

        String imagePath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\SytemLogo.png"; 
        ImageIcon originalIcon = new ImageIcon(imagePath);
        JLabel imagLabel = new JLabel(originalIcon);

        // Create a JPanel for the left side to hold the image
        JPanel imagePanel = new JPanel();
        imagePanel.add(imagLabel);
        imagePanel.setBackground(bgColor);

        // Center panel to hold the right panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(bgColor);

        // Right panel for registration
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(550, 775));
        rightPanel.setBackground(bgColor);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 70));

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(true);
        formPanel.setBackground(Color.decode("#EFB481"));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Creating headline
        JLabel headline = new JLabel("Register Your Account", SwingConstants.CENTER);
        headline.setFont(new Font("Serif", Font.BOLD, 34));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(headline, gbc);
        gbc.gridwidth = 1;

        // Adding components to form panel with default styles
        JTextField fullNameField = createTextField(350, 50);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(new JLabel("Full Name"), gbc);
        gbc.gridy = 2;
        formPanel.add(fullNameField, gbc);

        JTextField emailField = createTextField(350, 50);
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Email"), gbc);
        gbc.gridy = 4;
        formPanel.add(emailField, gbc);
        
         JLabel userTypeLabel = new JLabel("Select User Type");
        gbc.gridy = 5;
        formPanel.add(userTypeLabel, gbc);
        String[] choices = {"Admin", "User"};
        final JComboBox<String> dropDown = new JComboBox<>(choices);
        dropDown.setBackground(Color.decode("#EFB481"));
        dropDown.setFont(dropDown.getFont().deriveFont(14f));  
        gbc.gridy = 6;
        formPanel.add(dropDown, gbc);


        JPasswordField passwordField = createPasswordField(300, 50);
        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel passwordLabel = new JLabel("Password");
        formPanel.add(passwordLabel, gbc);
        gbc.gridy = 8;
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setOpaque(false);
        passwordPanel.add(passwordField, BorderLayout.CENTER);
        
        ImageIcon showIcon = resizeImage(new ImageIcon(getClass().getResource("/images/show.png")), 20, 20);
        ImageIcon hideIcon = resizeImage(new ImageIcon(getClass().getResource("/images/hide.png")), 20, 20);
        JButton toggleButton = new JButton(showIcon);
        toggleButton.setPreferredSize(new Dimension(50, 50));
        toggleButton.setBackground(Color.decode("#EFB481"));
        toggleButton.setBorderPainted(false);
        toggleButton.setFocusPainted(false);

        toggleButton.addActionListener(new ActionListener() {
            private boolean isPasswordVisible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPasswordVisible) {
                    passwordField.setEchoChar('*');
                    toggleButton.setIcon(showIcon);
                } else {
                    passwordField.setEchoChar((char) 0); // Show password
                    toggleButton.setIcon(hideIcon);
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });
        
        passwordPanel.add(toggleButton, BorderLayout.EAST);
        formPanel.add(passwordPanel, gbc);

        JPasswordField ConfirmPasswordField = createPasswordField(300, 50);
        gbc.gridx = 0;
        gbc.gridy = 9;
        JLabel ConfirmPasswordLabel = new JLabel("Confirm Password");
        formPanel.add(ConfirmPasswordLabel, gbc);
        gbc.gridy = 10;
        JPanel ConfirmPasswordPanel = new JPanel(new BorderLayout());
        ConfirmPasswordPanel.setOpaque(false);
        ConfirmPasswordPanel.add(ConfirmPasswordField, BorderLayout.CENTER);
        
        ImageIcon showIcon1 = resizeImage(new ImageIcon(getClass().getResource("/images/show.png")), 20, 20);
        ImageIcon hideIcon1 = resizeImage(new ImageIcon(getClass().getResource("/images/hide.png")), 20, 20);
        JButton toggleButton1 = new JButton(showIcon1);
        toggleButton1.setPreferredSize(new Dimension(50, 50));
        toggleButton1.setBackground(Color.decode("#EFB481"));
        toggleButton1.setBorderPainted(false);
        toggleButton1.setFocusPainted(false);

        toggleButton1.addActionListener(new ActionListener() {
            private boolean isPasswordVisible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPasswordVisible) {
                    ConfirmPasswordField.setEchoChar('*');
                    toggleButton1.setIcon(showIcon1);
                } else {
                    ConfirmPasswordField.setEchoChar((char) 0); // Show password
                    toggleButton1.setIcon(hideIcon1);
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });
        
        ConfirmPasswordPanel.add(toggleButton1, BorderLayout.EAST);
        formPanel.add(ConfirmPasswordPanel, gbc);

        JButton registerButton = createButton("Register", 350, 50);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fullName = fullNameField.getText();
                String email = emailField.getText();
                String userType = (String) dropDown.getSelectedItem();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(ConfirmPasswordField.getPassword());

                controller.registerUser(fullName, email, userType, password, confirmPassword);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(registerButton, gbc);

        JButton login = new JButton("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 16));
        login.setPreferredSize(new Dimension(100, 30));
        login.setBackground(Color.decode("#EFB481"));
        login.setForeground(Color.decode("#1D48DF"));
        login.setBorderPainted(false);
        login.setFocusPainted(false);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController loginController = new LoginController(); // Instantiate LoginController
                LoginPage loginPage = new LoginPage(loginController); // Pass LoginController to LoginPage
                loginPage.setVisible(true);
                frame.dispose();
            }
        });
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(login, gbc);

        rightPanel.add(formPanel, BorderLayout.CENTER);

        // Add right panel to center panel with positioning
        GridBagConstraints centerGbc = new GridBagConstraints();
        centerGbc.gridx = 0;
        centerGbc.gridy = 0;
        centerPanel.add(rightPanel, centerGbc);

        // Add panels to frame
        frame.add(imagePanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Maximize the frame and make it visible
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }


    // Helper method to create text field
    private JTextField createTextField(int width, int height) {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(width, height));
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#282828")),
                BorderFactory.createEmptyBorder(13, 26, 13, 26)
        ));
        textField.setOpaque(true);
        textField.setForeground(Color.BLACK);
        textField.setCaretColor(Color.BLACK);
                textField.setBackground(Color.decode("#EFB481"));
        return textField;
    }

    // Helper method to create password field
    private JPasswordField createPasswordField(int width, int height) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(width, height));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#282828")),
                BorderFactory.createEmptyBorder(13, 26, 13, 26)
        ));
        passwordField.setOpaque(true);
        passwordField.setForeground(Color.BLACK);
        passwordField.setCaretColor(Color.BLACK);
        passwordField.setBackground(Color.decode("#EFB481"));
        return passwordField;
    }

    // Helper method to create button
    private JButton createButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(Color.decode("#134700"));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#282828")),
                BorderFactory.createEmptyBorder(13, 26, 13, 26)
        ));
        button.setFocusPainted(false);
        return button;
    }

    private static ImageIcon resizeImage(ImageIcon originalIcon, int width, int height) {
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
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

       
