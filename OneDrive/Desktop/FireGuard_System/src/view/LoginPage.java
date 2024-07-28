import Dao.UserDAO;
import Dao.UserDAOImpl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    private JFrame frame;
    private LoginController controller;

    public LoginPage(LoginController controller) {
        this.controller = controller;

        // Create JFrame (window)
        frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Set background color for the content pane
        Color bgColor = Color.decode("#FFDEC8");
        frame.getContentPane().setBackground(bgColor);

        // Load and resize image
        String imagePath = "C:\\Users\\Asus\\OneDrive\\Desktop\\FireGuard_System\\src\\images\\SytemLogo.png";
        ImageIcon originalIcon = new ImageIcon(imagePath);
        JLabel imagLabel = new JLabel(originalIcon);

        // Create JPanel for the left side to hold the image
        JPanel imagePanel = new JPanel();
        imagePanel.add(imagLabel);
        imagePanel.setBackground(bgColor);

        // Center panel to hold the right panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(bgColor);

        // Right panel for login
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(550, 775));
        rightPanel.setBackground(bgColor);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 70));

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setOpaque(true);
        Color formPanelColor = Color.decode("#EFB481");
        formPanel.setBackground(formPanelColor);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Creating headline
        JLabel headline = new JLabel("Welcome Back", SwingConstants.CENTER);
        headline.setFont(new Font("Serif", Font.BOLD, 34));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(headline, gbc);
        gbc.gridwidth = 1;

        // Email field
        JTextField emailField = createTextField(350, 50);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel emailLabel = new JLabel("Email");
        formPanel.add(emailLabel, gbc);
        gbc.gridy = 2;
        formPanel.add(emailField, gbc);

        // Password field
        JPasswordField passwordField = createPasswordField(350, 50);
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel passwordLabel = new JLabel("Password");
        formPanel.add(passwordLabel, gbc);
        gbc.gridy = 4;
        formPanel.add(passwordField, gbc);
        
         JLabel userTypeLabel = new JLabel("Select User Type");
        gbc.gridy = 5;
        formPanel.add(userTypeLabel, gbc);
        String[] choices = {"Admin", "User"};
        final JComboBox<String> dropDown = new JComboBox<>(choices);
        dropDown.setBackground(Color.decode("#EFB481"));
        dropDown.setFont(dropDown.getFont().deriveFont(14f));  // Set font size to 16
        gbc.gridy = 6;
        gbc.ipady = 10;
        formPanel.add(dropDown, gbc);


        // Login button
        JButton loginButton = createButton("Login", 350, 50);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String userType = (String) dropDown.getSelectedItem();

                controller.loginUser(email, password, userType);
            }
        });
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(loginButton, gbc);

        // Sign-up prompt
        JLabel signUpPrompt = new JLabel("If you don’t have an account");
        signUpPrompt.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(signUpPrompt, gbc);

        // Sign up button
        JButton signup = new JButton("Sign Up");
        signup.setFont(new Font("Arial", Font.PLAIN, 16));
        signup.setPreferredSize(new Dimension(100, 30));
        signup.setBackground(Color.decode("#EFB481"));
        signup.setForeground(Color.decode("#1D48DF"));
        signup.setBorderPainted(false);
        signup.setFocusPainted(false);
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDAO userDAO = new UserDAOImpl();
                RegisterController registerController = new RegisterController(userDAO); 
                RegisterPage registerPage = new RegisterPage(registerController);
                registerPage.setVisible(true);
                frame.dispose();
            }
        });
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(signup, gbc);

        rightPanel.add(formPanel, BorderLayout.CENTER);

        // Add right panel to center panel
        GridBagConstraints centerGbc = new GridBagConstraints();
        centerGbc.gridx = 0;
        centerGbc.gridy = 0;
        centerPanel.add(rightPanel, centerGbc);

        // Add panels to frame
        frame.add(imagePanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Maximize frame and set visible
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    // Helper method to create text field
    private static JTextField createTextField(int width, int height) {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(width, height));
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
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
    private static JPasswordField createPasswordField(int width, int height) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(width, height));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
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
    private static JButton createButton(String text, int width, int height) {
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

    public void setVisible(boolean isVisible) {
        frame.setVisible(isVisible);
    }
    
    public static void main(String[] args) {
        // Create and display the login page
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginPage(new LoginController()).setVisible(true);
            }
        });
    }
}