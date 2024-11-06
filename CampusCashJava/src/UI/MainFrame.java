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
        ImageIcon largeLogo1 = new ImageIcon("C://Code//CampusCash//CampusCashJava//Images//logoh.png");
        JLabel logo1Label = new JLabel(new ImageIcon(largeLogo1.getImage().getScaledInstance(513, 106, Image.SCALE_SMOOTH)));
        logo1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(logo1Label);

        ImageIcon largeLogo2 = new ImageIcon("C://Code//CampusCash//CampusCashJava//Images//Mits.png");
        JLabel logo2Label = new JLabel(new ImageIcon(largeLogo2.getImage().getScaledInstance(294, 105, Image.SCALE_SMOOTH)));
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
        addInputField(formPanel, "C://Code//CampusCash//CampusCashJava//Images//pass.png", "Enter Your Password", 210);
      
        // Login button with updated size and color
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true)); // Added border
        loginButton.setBounds(250, 280, 100, 50);// Increased button size
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
        formPanel.add(loginButton);

        // Staff login link
        JLabel registerLabel = new JLabel("Staff Login? Click here");
        registerLabel.setForeground(Color.WHITE);
        registerLabel.setFont(new Font("SansSerif", Font.PLAIN, 19));
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLabel.setBounds(250, 0, 160, 20);
        mainPanel.add(registerLabel, BorderLayout.SOUTH);

        // After all components are set up
        mainPanel.requestFocusInWindow(); // Set initial focus to the main panel


        // Add click event for staff login link
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new StaffFrame().setVisible(true);
                MainFrame.this.dispose();
            }
        });
    }

    public static void addInputField(JPanel panel, String iconPath, String placeholder, int yPosition) {
        ImageIcon icon = new ImageIcon(
                new ImageIcon(iconPath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(20, yPosition, 50, 50);
        panel.add(iconLabel);

        JTextField textField = new JTextField(placeholder);
        textField.setBounds(80, yPosition, 500, 40);
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
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
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
        JLabel logo1Label = new JLabel(
                new ImageIcon(largeLogo1.getImage().getScaledInstance(500, 100, Image.SCALE_SMOOTH)));
        logo1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(logo1Label);

        ImageIcon largeLogo2 = new ImageIcon("C://Code//CampusCash//CampusCashJava//Images//Mits.png");
        JLabel logo2Label = new JLabel(
                new ImageIcon(largeLogo2.getImage().getScaledInstance(250, 110, Image.SCALE_SMOOTH)));
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
        formPanel.setPreferredSize(new Dimension(600, 400));

        // Center form container
        JPanel formContainer = new JPanel();
        formContainer.setBackground(new Color(10, 10, 30));
        formContainer.setLayout(new GridBagLayout());
        formContainer.add(formPanel);
        mainPanel.add(formContainer, BorderLayout.CENTER);

        // Input fields with icons
        MainFrame.addInputField(formPanel, "C://Code//CampusCash//CampusCashJava//Images//id.jpg", "Enter Your Staff ID", 50);
        MainFrame.addInputField(formPanel, "C://Code//CampusCash//CampusCashJava//Images//user.png", "Enter Your Name", 130);
        MainFrame.addInputField(formPanel, "C://Code//CampusCash//CampusCashJava//Images//pass.png", "Enter Your Password", 210);

        // Login button with updated style
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        loginButton.setBounds(250, 280, 100, 50);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(loginButton);

        // Store login link
        JLabel storeLoginLabel = new JLabel("Store Login? Click here");
        storeLoginLabel.setForeground(Color.WHITE);
        storeLoginLabel.setFont(new Font("SansSerif", Font.PLAIN, 19));
        storeLoginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        storeLoginLabel.setBounds(250, 0, 160, 20);
        mainPanel.add(storeLoginLabel, BorderLayout.SOUTH);

        // Add click event for store login link
        storeLoginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new MainFrame().setVisible(true);
                StaffFrame.this.dispose();
            }
        });
    }
}
