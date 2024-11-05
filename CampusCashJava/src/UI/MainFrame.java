package UI;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("CampusCash - Store Login");
        setSize(1920, 1080); // Full HD display
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel setup
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(10, 10, 30));
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Top logo panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(10, 10, 30));
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        mainPanel.add(logoPanel, BorderLayout.NORTH);

        // Large logo images
        ImageIcon largeLogo1 = new ImageIcon("C://Code//CampusCash//CampusCashJava//Images//logo.png");
        JLabel logo1Label = new JLabel(new ImageIcon(largeLogo1.getImage().getScaledInstance(500, 100, Image.SCALE_SMOOTH)));
        logo1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(logo1Label);

        ImageIcon largeLogo2 = new ImageIcon("C://Code//CampusCash//CampusCashJava//Images//Mits.png");
        JLabel logo2Label = new JLabel(new ImageIcon(largeLogo2.getImage().getScaledInstance(250, 110, Image.SCALE_SMOOTH)));
        logo2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(logo2Label);

        // Login title
        JLabel loginTitle = new JLabel("<html><center><br>STORE LOGIN</center></html>", SwingConstants.CENTER);
        loginTitle.setFont(new Font("Monospaced", Font.BOLD, 34));
        loginTitle.setForeground(Color.WHITE);
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(loginTitle);

        // Form panel with rounded edges
        JPanel formPanel = new RoundedPanel();
        formPanel.setLayout(null);
        formPanel.setPreferredSize(new Dimension(600, 400));

        // Center form container
        JPanel formContainer = new JPanel();
        formContainer.setBackground(new Color(10, 10, 30));
        formContainer.setLayout(new GridBagLayout());
        formContainer.add(formPanel);
        mainPanel.add(formContainer, BorderLayout.CENTER);

        // Input fields with icons
        addInputField(formPanel, "C://Code//CampusCash//CampusCashJava//Images//store.png", "Enter Your Store ID", 50);
        addInputField(formPanel, "C://Code//CampusCash//CampusCashJava//Images//user.png", "Enter Your Name", 130);
        addPasswordField(formPanel, "C://Code//CampusCash//CampusCashJava//Images//pass.png", "Enter Your Password", 210);

        // Login button with updated size and color
        JButton loginButton = new JButton("LOGIN") {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(Color.BLACK);
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true)); // Added border
        loginButton.setBounds(250, 280, 100, 50);// Increased button size
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
        formPanel.add(loginButton);

        // Staff login link, repositioned further down
        JLabel registerLabel = new JLabel("Staff Login? Click here");
        registerLabel.setForeground(Color.WHITE);
        registerLabel.setFont(new Font("SansSerif", Font.PLAIN, 19));
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLabel.setBounds(250, 0, 160, 20); // Repositioned further down
        mainPanel.add(registerLabel, BorderLayout.SOUTH);

        // Add click event for staff login link
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new StaffFrame().setVisible(true); // Open Staff Frame
                MainFrame.this.dispose(); // Close Main Frame
            }
        });
    }

    public static void addInputField(JPanel panel, String iconPath, String placeholder, int yPosition) {
        // Icon for input field
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)); // Increased size
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(20, yPosition, 40, 40); // Adjusted bounds
        panel.add(iconLabel);

        // Text field with placeholder text
        JTextField textField = new JTextField(placeholder);
        textField.setBounds(60, yPosition, 500, 40);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        textField.setForeground(Color.GRAY);
        textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
        panel.add(textField);
    }

    public static void addPasswordField(JPanel panel, String iconPath, String placeholder, int yPosition) {
        // Icon for password field
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)); // Increased size
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(30, yPosition, 50, 50); // Adjusted bounds
        panel.add(iconLabel);

        // Password field with placeholder behavior
        JPasswordField passwordField = new JPasswordField(placeholder);
        passwordField.setBounds(60, yPosition, 500, 40);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        passwordField.setForeground(Color.GRAY);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordField.setEchoChar((char) 0); // Show placeholder text initially
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('\u2022'); // Hide text with bullet char
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setText(placeholder);
                    passwordField.setEchoChar((char) 0); // Show text for placeholder
                }
            }
        });
        panel.add(passwordField);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

// Custom panel class for rounded corners
class RoundedPanel extends JPanel {
    public RoundedPanel() {
        setOpaque(false); // Make sure the panel is transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE); // Set the background color
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Draw rounded rectangle
    }
}

class StaffFrame extends JFrame {
    public StaffFrame() {
        setTitle("CampusCash - Staff Login");
        setSize(1920, 1080); // Full HD display
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(10, 10, 30));
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Top logo panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(10, 10, 30));
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        mainPanel.add(logoPanel, BorderLayout.NORTH);

        // Large logo images
        ImageIcon largeLogo1 = new ImageIcon("C://Code//CampusCash//CampusCashJava//Images//logo.png");
        JLabel logo1Label = new JLabel(new ImageIcon(largeLogo1.getImage().getScaledInstance(500, 100, Image.SCALE_SMOOTH)));
        logo1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(logo1Label);

        ImageIcon largeLogo2 = new ImageIcon("C://Code//CampusCash//CampusCashJava//Images//Mits.png");
        JLabel logo2Label = new JLabel(new ImageIcon(largeLogo2.getImage().getScaledInstance(250, 110, Image.SCALE_SMOOTH)));
        logo2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(logo2Label);

        // Login title
        JLabel loginTitle = new JLabel("<html><center><br>STAFF LOGIN</center></html>", SwingConstants.CENTER);
        loginTitle.setFont(new Font("Monospaced", Font.BOLD, 34));
        loginTitle.setForeground(Color.WHITE);
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(loginTitle);

        // Form panel
        JPanel formPanel = new RoundedPanel();
        formPanel.setLayout(null);
        formPanel.setPreferredSize(new Dimension(600, 300));
        
        // Center form container
        JPanel formContainer = new JPanel();
        formContainer.setBackground(new Color(10, 10, 30));
        formContainer.setLayout(new GridBagLayout());
        formContainer.add(formPanel);
        mainPanel.add(formContainer, BorderLayout.CENTER);

        // Input fields with icons
        MainFrame.addInputField(formPanel, "C://Code//CampusCash//CampusCashJava//Images//id.jpg", "Enter Your Staff ID", 50);
        MainFrame.addPasswordField(formPanel, "C://Code//CampusCash//CampusCashJava//Images//pass.png", "Enter Your Password", 130);

        // Login button with updated size and color
        JButton loginButton = new JButton("LOGIN") {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(Color.BLACK);
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true)); // Added border
        loginButton.setBounds(150, 210, 300, 50); // Increased button size
        formPanel.add(loginButton);

        // Repositioned staff login link
        JLabel registerLabel = new JLabel("Staff Login? Click here");
        registerLabel.setForeground(Color.WHITE);
        registerLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLabel.setBounds(220, 270, 200, 20); // Repositioned further down
        formPanel.add(registerLabel);

        // Add click event for staff login link
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Implement action for staff login link
            }
        });
    }
}
