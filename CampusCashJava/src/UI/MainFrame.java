package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import UI.MainFrame.StaffPage;
import java.awt.event.MouseAdapter;

public class MainFrame extends JFrame {
    
    private static final String[][] USER = {
        {"MitsCafe", "Cafe@123", "MITS CAFE"},
        {"MitsStore", "Store@123", "MITS STORE"},
        {"MitsCanteen", "Canteen@123", "MITS CANTEEN"},
        {"CafeRB", "CafeRB@123", "MITS CAFE R-BLOCK"},
        {"Cafe@MGB", "CafeMGB@123", "MITS CAFE MG-BLOCK"}
    };

    public MainFrame() {
        setTitle("CampusCash - Store Login");
        setSize(1420, 800); // Full HD display
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
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
        ImageIcon largeLogo1 = new ImageIcon("CampusCashJava\\Images\\logoh.png");
        JLabel logo1Label = new JLabel(new ImageIcon(largeLogo1.getImage().getScaledInstance(513, 106, Image.SCALE_SMOOTH)));
        logo1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(logo1Label);

        ImageIcon largeLogo2 = new ImageIcon("CampusCashJava\\Images\\Mits.png");
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
        JTextField usernameField = addInputField(formPanel, "CampusCashJava\\Images\\store.png", "Enter Your Store ID", 50, false);
        JTextField nameField = addInputField(formPanel, "CampusCashJava\\Images\\user.png", "Enter Your Name", 130, false);
        JPasswordField passwordField = (JPasswordField) addInputField(formPanel, "CampusCashJava\\Images\\pass.png", "Enter Your Password", 210, true);

        // Login button with updated size and color
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        loginButton.setBounds(250, 280, 100, 50);
        formPanel.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText(); // Retrieve username
            String password = new String(passwordField.getPassword()); // Retrieve password
            String staffname = new String(nameField.getText()); // Retrieve password
        
            // Check if username or password is empty
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(MainFrame.this, "Please fill in all fields", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        
            boolean validCredentials = false;
            String storeName = "";
        
            // Check if username and password are correct
            for (String[] credentials : USER) {
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    validCredentials = true;
                    storeName = credentials[2];
                    break;
                }
            }
        
            if (validCredentials) {
                // Open StorePage with store name and staff name
                new StorePage(storeName, staffname).setVisible(true);
                MainFrame.this.dispose();
            } else {
                // Show incorrect credentials warning
                JOptionPane.showMessageDialog(MainFrame.this, "Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Staff login link
        JLabel registerLabel = new JLabel("Staff Login? Click here");
        registerLabel.setForeground(Color.WHITE);
        registerLabel.setFont(new Font("SansSerif", Font.PLAIN, 19));
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLabel.setBounds(500, 50, 160, 20);
        mainPanel.add(registerLabel, BorderLayout.PAGE_END);

        // Set custom focus traversal policy to skip initial focus
        setFocusTraversalPolicy(new CustomFocusTraversalPolicy());

        // Add click event for staff login link
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new StaffFrame().setVisible(true);
                MainFrame.this.dispose();
            }
        });
    }

    public static JTextField addInputField(JPanel panel, String iconPath, String placeholder, int yPosition, boolean isPassword) {
        ImageIcon icon = new ImageIcon(
                new ImageIcon(iconPath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(20, yPosition, 50, 50);
        panel.add(iconLabel);
    
        JTextField textField;
        JLabel placeholderLabel = new JLabel(placeholder); // Set placeholder label text
        placeholderLabel.setForeground(Color.GRAY);
        placeholderLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        placeholderLabel.setBounds(85, yPosition + 10, 500, 20);
    
        if (isPassword) {
            textField = new JPasswordField("Password");
            ((JPasswordField) textField).setEchoChar((char) 0); // Initially show placeholder text
            placeholderLabel.setText("Enter"); // Set explicit placeholder text
            placeholderLabel.setVisible(true); // Placeholder label is visible initially
        } else {
            textField = new JTextField(placeholder);
            textField.setForeground(Color.GRAY);
        }
    
        textField.setBounds(80, yPosition, 500, 40);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
    
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (isPassword) {
                    ((JPasswordField) textField).setEchoChar('\u2022'); // Set password echo char
                    placeholderLabel.setVisible(false); // Hide placeholder on focus
                } else if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
    
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (isPassword && ((JPasswordField) textField).getPassword().length == 0) {
                    ((JPasswordField) textField).setEchoChar((char) 0); // Reset to show placeholder
                    placeholderLabel.setVisible(true); // Show placeholder if empty
                } else if (!isPassword && textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    
        panel.add(textField);
        if (isPassword) {
            panel.add(placeholderLabel); // Add the placeholder label to the panel
        }
        return textField; // Return textField or passwordField for later retrieval
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
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

// Custom FocusTraversalPolicy that initially skips focusing on any components
class CustomFocusTraversalPolicy extends FocusTraversalPolicy {
    @Override
    public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
        return null;
    }

    @Override
    public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
        return null;
    }

    @Override
    public Component getFirstComponent(Container focusCycleRoot) {
        return null;
    }

    @Override
    public Component getLastComponent(Container focusCycleRoot) {
        return null;
    }

    @Override
    public Component getDefaultComponent(Container focusCycleRoot) {
        return null;
    }
}

public class StaffFrame extends JFrame {

    private static final String[][] Staff = {
        {"Admin", "Admin", "test@123"},
        {"Owner", "Owner", "owner@123"},
    };

    public StaffFrame() {
        setTitle("CampusCash - Staff Login");
        setSize(1420, 800); // Full HD display
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

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
        ImageIcon largeLogo1 = new ImageIcon("CampusCashJava\\Images\\logoh.png");
        JLabel logo1Label = new JLabel(
                new ImageIcon(largeLogo1.getImage().getScaledInstance(500, 100, Image.SCALE_SMOOTH)));
        logo1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(logo1Label);

        ImageIcon largeLogo2 = new ImageIcon("CampusCashJava\\Images\\Mits.png");
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
        JTextField staffIDField = MainFrame.addInputField(formPanel, "CampusCashJava\\Images\\id.jpg", "Enter Your Staff ID", 50, false);
        JTextField staffNameField = MainFrame.addInputField(formPanel, "CampusCashJava\\Images\\user.png", "Enter Your Name", 130, false);
        JTextField staffpassField = MainFrame.addInputField(formPanel, "CampusCashJava\\Images\\pass.png", "Enter Password", 210, true);
        // Create a password field for password input
        

        // Login button with updated size and color
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        loginButton.setBounds(250, 280, 100, 50);
        formPanel.add(loginButton);

        // Back link to store login
        JLabel backLabel = new JLabel("Store Login ? Click Here");
        backLabel.setForeground(Color.WHITE);
        backLabel.setFont(new Font("SansSerif", Font.PLAIN, 19));
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLabel.setBounds(500, 50, 160, 20);
        mainPanel.add(backLabel, BorderLayout.PAGE_END);

        // Set a custom focus traversal policy to skip initial focus
        setFocusTraversalPolicy(new CustomFocusTraversalPolicy());

        // Add click event for back link
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new MainFrame().setVisible(true);
                StaffFrame.this.dispose();
            }
        });

        // Add login action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String staffID = staffIDField.getText();
                String staffName = staffNameField.getText();
                String staffPassword =staffpassField.getText();  // Convert char[] to String

                // Validate credentials
                boolean validLogin = false;
                for (String[] staff : Staff) {
                    if (staff[0].equals(staffID) && staff[2].equals(staffPassword)) {
                        validLogin = true;
                        break;
                    }
                }

                if (validLogin) {
                    // Switch to StaffPage
                    new StaffPage(staffName).setVisible(true);  // Pass the staffName to StaffPage
                    StaffFrame.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(StaffFrame.this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}


public class StorePage extends JFrame {

    private JTextField studentIdField;
    private JTextField studentNameField;
    private JTextField itemNameField;
    private JTextField amountField;

    public StorePage(String storeName, String staffName) {
        setTitle("CampusCash - Store Page");
        setSize(1420, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(10, 10, 30));
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Top logo panel with welcome message
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(10, 10, 30));
        logoPanel.setLayout(new BorderLayout());
        mainPanel.add(logoPanel, BorderLayout.NORTH);

        // Logo and store name in the center of the top panel
        JPanel logoCenterPanel = new JPanel();
        logoCenterPanel.setBackground(new Color(10, 10, 30));
        logoCenterPanel.setLayout(new BoxLayout(logoCenterPanel, BoxLayout.Y_AXIS));
        logoPanel.add(logoCenterPanel, BorderLayout.CENTER);

        JLabel welcomeLabel = new JLabel("Welcome, " + staffName);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.WHITE);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(10, 10, 30));
        rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        rightPanel.add(welcomeLabel);

        // Large logo images
        ImageIcon largeLogo1 = new ImageIcon("CampusCashJava\\Images\\logoh.png");
        JLabel logo1Label = new JLabel(new ImageIcon(largeLogo1.getImage().getScaledInstance(513, 106, Image.SCALE_SMOOTH)));
        logo1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoCenterPanel.add(logo1Label);

        ImageIcon largeLogo2 = new ImageIcon("CampusCashJava\\Images\\Mits.png");
        JLabel logo2Label = new JLabel(new ImageIcon(largeLogo2.getImage().getScaledInstance(294, 105, Image.SCALE_SMOOTH)));
        logo2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoCenterPanel.add(logo2Label);

        // Store name centered below logos
        JLabel storeLabel = new JLabel(storeName.toUpperCase());
        storeLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        storeLabel.setForeground(Color.WHITE);
        storeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoCenterPanel.add(storeLabel);

        logoPanel.add(rightPanel, BorderLayout.EAST);

        // Main content area
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(10, 10, 30));
        contentPanel.setLayout(new GridBagLayout());
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        JPanel formPanel = new RoundedPanel();
        formPanel.setLayout(null);
        formPanel.setPreferredSize(new Dimension(600, 400));
        contentPanel.add(formPanel);

        ImageIcon backIcon = new ImageIcon("CampusCashJava\\Images\\Back.png");
        JLabel backLabel = new JLabel(new ImageIcon(backIcon.getImage().getScaledInstance(26, 43, Image.SCALE_SMOOTH)));
        backLabel.setBounds(5, 20, 26, 42);
        formPanel.add(backLabel);
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new MainFrame().setVisible(true); // Assuming MainFrame is the login page
                StorePage.this.dispose();
            }
        });

        int yPosition = 60;
        int spacing = 65;

        studentIdField = addPlaceholderField(formPanel, "CampusCashJava\\Images\\id.jpg", "Enter Student Id", yPosition);
        yPosition += spacing;

        studentNameField = addPlaceholderField(formPanel, "CampusCashJava\\Images\\user.png", "Enter Student Name", yPosition);
        yPosition += spacing;

        itemNameField = addPlaceholderField(formPanel, "CampusCashJava\\Images\\store.png", "Enter Item Name", yPosition);
        yPosition += spacing;

        amountField = addPlaceholderField(formPanel, "CampusCashJava\\Images\\ammnt.png", "Enter Amount", yPosition);

        JButton addToListButton = new JButton("ADD TO LIST");
        addToListButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        addToListButton.setBackground(Color.BLACK);
        addToListButton.setForeground(Color.WHITE);
        addToListButton.setBounds(200, yPosition + spacing, 200, 50);
        formPanel.add(addToListButton);

        // Add button listener with validation and navigation
        addToListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInputs()) {
                    String studentId = studentIdField.getText();
                    String studentName = studentNameField.getText();
                    String itemName = itemNameField.getText();
                    String amount = amountField.getText();

                    new TransactionSummaryPage(storeName, staffName, studentId, studentName, itemName, amount).setVisible(true);
                    StorePage.this.dispose();
                }
            }
        });
    }

    private boolean validateInputs() {
        String studentId = studentIdField.getText();
        String studentName = studentNameField.getText();
        String itemName = itemNameField.getText();
        String amountText = amountField.getText();

        if (studentId.isEmpty() || studentName.isEmpty() || itemName.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int amount = Integer.parseInt(amountText);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Amount must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private JTextField addPlaceholderField(JPanel panel, String iconPath, String placeholder, int yPosition) {
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(20, yPosition, 40, 40);
        panel.add(iconLabel);

        JTextField textField = new JTextField(placeholder);
        textField.setBounds(80, yPosition, 480, 40);
        textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textField.setForeground(Color.GRAY);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

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
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });

        panel.add(textField);
        return textField;
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
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        }
    }

    // Custom FocusTraversalPolicy to prevent initial focus on any components
    class CustomFocusTraversalPolicy extends FocusTraversalPolicy {
        @Override
        public Component getComponentAfter(Container focusCycleRoot, Component aComponent) { return null; }
        @Override
        public Component getComponentBefore(Container focusCycleRoot, Component aComponent) { return null; }
        @Override
        public Component getFirstComponent(Container focusCycleRoot) { return null; }
        @Override
        public Component getLastComponent(Container focusCycleRoot) { return null; }
        @Override
        public Component getDefaultComponent(Container focusCycleRoot) { return null; }
    }
}



public class StaffPage extends JFrame {
    public StaffPage(String staffName) {
        setTitle("CampusCash - Staff Page");
        setSize(1420, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(10, 10, 30));
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Top logo panel with welcome message
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(10, 10, 30));
        logoPanel.setLayout(new BorderLayout());
        mainPanel.add(logoPanel, BorderLayout.NORTH);

        // Logo and staff name in the center of the top panel
        JPanel logoCenterPanel = new JPanel();
        logoCenterPanel.setBackground(new Color(10, 10, 30));
        logoCenterPanel.setLayout(new BoxLayout(logoCenterPanel, BoxLayout.Y_AXIS));
        logoPanel.add(logoCenterPanel, BorderLayout.CENTER);
        JLabel welcomeLabel = new JLabel("Welcome, " + staffName + " ");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.WHITE);
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(10, 10, 30));
        rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        rightPanel.add(welcomeLabel);

        // Large logo images
        ImageIcon largeLogo1 = new ImageIcon("CampusCashJava\\Images\\logoh.png");
        JLabel logo1Label = new JLabel(new ImageIcon(largeLogo1.getImage().getScaledInstance(513, 106, Image.SCALE_SMOOTH)));
        logo1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoCenterPanel.add(logo1Label);

        ImageIcon largeLogo2 = new ImageIcon("CampusCashJava\\Images\\Mits.png");
        JLabel logo2Label = new JLabel(new ImageIcon(largeLogo2.getImage().getScaledInstance(294, 105, Image.SCALE_SMOOTH)));
        logo2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoCenterPanel.add(logo2Label);

        // Staff name centered below logos
        JLabel staffLabel = new JLabel("PAY DUE");
        staffLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        staffLabel.setForeground(Color.WHITE);
        staffLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoCenterPanel.add(staffLabel);

        // Right side panel for welcome text
        logoPanel.add(rightPanel, BorderLayout.EAST);

        // Main content area (you can customize this further)
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(10, 10, 30));
        contentPanel.setLayout(new GridBagLayout());
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Form panel with rounded corners (you can add forms or additional UI elements here)
        JPanel formPanel = new RoundedPanel();
        formPanel.setLayout(null);
        formPanel.setPreferredSize(new Dimension(600, 400));
        contentPanel.add(formPanel);

        ImageIcon backIcon = new ImageIcon("CampusCashJava\\Images\\Back.png");
        JLabel backLabel = new JLabel(new ImageIcon(backIcon.getImage().getScaledInstance(26, 43, Image.SCALE_SMOOTH)));
        backLabel.setBounds(5, 20, 26, 42);
        formPanel.add(backLabel);
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new StaffFrame().setVisible(true); // Assuming MainFrame is the login page
                StaffPage.this.dispose();
            }
        });

        // Placeholder input fields
        int yPosition = 80; // Initial Y position for the first field
        int spacing = 79; // Space between fields

        JTextField idField = addPlaceholderField(formPanel, "CampusCashJava\\Images\\id.jpg", "Enter Student Id", yPosition);
        yPosition += spacing;

        JTextField nameField = addPlaceholderField(formPanel, "CampusCashJava\\Images\\user.png", "Enter Student Name", yPosition);
        yPosition += spacing;

        JTextField amountField = addPlaceholderField(formPanel, "CampusCashJava\\Images\\ammnt.png", "Enter Amount", yPosition);

        // Add to List button
        JButton addToListButton = new JButton("UPDATE");
        addToListButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        addToListButton.setBackground(Color.BLACK);
        addToListButton.setForeground(Color.WHITE);
        addToListButton.setBounds(200, yPosition + spacing, 200, 50);
        formPanel.add(addToListButton);

        addToListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if all fields are filled
                String studentId = idField.getText();
                String studentName = nameField.getText();
                String amountText = amountField.getText();

                if (studentId.isEmpty() || studentName.isEmpty() || amountText.isEmpty()) {
                    // Show popup message if any field is empty
                    JOptionPane.showMessageDialog(StaffPage.this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!isNumeric(amountText)) {
                    // Show popup message if the amount is not a valid number
                    JOptionPane.showMessageDialog(StaffPage.this, "Please enter a valid amount (only digits).", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // All fields are valid, navigate to DueDetails page
                    new DueDetails(staffName,studentName, studentId, amountText).setVisible(true);
                    StaffPage.this.dispose();
                }
            }
        });

        // Set custom focus traversal policy
        setFocusTraversalPolicy(new CustomFocusTraversalPolicy());
    }

    // Helper method to check if a string contains only digits
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Helper method to add placeholder fields with focus listener
    private JTextField addPlaceholderField(JPanel panel, String iconPath, String placeholder, int yPosition) {
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(20, yPosition, 40, 40);
        panel.add(iconLabel);

        JTextField textField = new JTextField(placeholder);
        textField.setBounds(80, yPosition, 480, 40);
        textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textField.setForeground(Color.GRAY); // Placeholder color
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        // Focus listener for placeholder text
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
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });

        panel.add(textField);
        return textField;
    }
}
    



    public class TransactionSummaryPage extends JFrame {
        public TransactionSummaryPage(String storeName, String staffName, String studentId, String studentName, String itemName, String amount) {
            setTitle("Transaction Summary");
            setSize(1420, 800);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
    
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(new Color(10, 10, 30));
            mainPanel.setLayout(new BorderLayout());
            add(mainPanel);
    
            JPanel logoPanel = new JPanel();
            logoPanel.setBackground(new Color(10, 10, 30));
            logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
            mainPanel.add(logoPanel, BorderLayout.NORTH);

            ImageIcon largeLogo1 = new ImageIcon("CampusCashJava\\Images\\logoh.png");
        JLabel logo1Label = new JLabel(new ImageIcon(largeLogo1.getImage().getScaledInstance(513, 106, Image.SCALE_SMOOTH)));
        logo1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(logo1Label);

        ImageIcon largeLogo2 = new ImageIcon("CampusCashJava\\Images\\Mits.png");
        JLabel logo2Label = new JLabel(new ImageIcon(largeLogo2.getImage().getScaledInstance(294, 105, Image.SCALE_SMOOTH)));
        logo2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(logo2Label);
    
           
    
            JLabel storeLabel = new JLabel(storeName.toUpperCase());
            storeLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
            storeLabel.setForeground(Color.WHITE);
            storeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            logoPanel.add(storeLabel);
    
            JPanel contentPanel = new JPanel();
            contentPanel.setBackground(new Color(10, 10, 30));
            contentPanel.setLayout(new GridBagLayout());
            mainPanel.add(contentPanel, BorderLayout.CENTER);
    
            JPanel summaryPanel = new RoundedPanel();
            summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
            summaryPanel.setPreferredSize(new Dimension(600, 400));
            summaryPanel.setBackground(Color.WHITE);
            contentPanel.add(summaryPanel);

            // Back Button Panel aligned to the left
JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
backButtonPanel.setBackground(Color.WHITE);
summaryPanel.add(backButtonPanel);

// Set the back button's image
ImageIcon backIcon = new ImageIcon("CampusCashJava\\Images\\Back.png");
JLabel backLabel = new JLabel(new ImageIcon(backIcon.getImage().getScaledInstance(26, 43, Image.SCALE_SMOOTH)));
backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
backButtonPanel.add(backLabel);

// Set action on back button
backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseClicked(java.awt.event.MouseEvent e) {
        new StorePage(storeName, staffName).setVisible(true);
        TransactionSummaryPage.this.dispose();
    }
});

// Add a vertical strut to move the labels below the back button
summaryPanel.add(Box.createRigidArea(new Dimension(0, 0))); // Increase vertical space below back button

// Add student details with reduced spacing
JLabel nameLabel = new JLabel("Name: " + studentName);
nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
summaryPanel.add(nameLabel);

summaryPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Reduced space between labels

JLabel idLabel = new JLabel("Id: " + studentId);
idLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
summaryPanel.add(idLabel);

summaryPanel.add(Box.createRigidArea(new Dimension(0, 35))); // Reduced space between labels

JLabel itemLabel = new JLabel("Item: " + itemName);
itemLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
itemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
summaryPanel.add(itemLabel);

summaryPanel.add(Box.createRigidArea(new Dimension(0, 35))); // Reduced space between labels

JLabel amountLabel = new JLabel("Amount: ₹" + amount);
amountLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
summaryPanel.add(amountLabel);

summaryPanel.add(Box.createRigidArea(new Dimension(0, 35))); // Reduced space between labels

JLabel oldDueLabel = new JLabel("Old Due: ₹0");
oldDueLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
oldDueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
summaryPanel.add(oldDueLabel);

summaryPanel.add(Box.createRigidArea(new Dimension(0, 35))); // Reduced space between labels

JLabel currentDueLabel = new JLabel("Current Due: ₹" + (Integer.parseInt(amount) + 0));
currentDueLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
currentDueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
summaryPanel.add(currentDueLabel);

summaryPanel.add(Box.createRigidArea(new Dimension(0, 20)));

JLabel emptyforspace = new JLabel(" ");
emptyforspace.setFont(new Font("SansSerif", Font.BOLD, 18));
emptyforspace.setAlignmentX(Component.CENTER_ALIGNMENT);
summaryPanel.add(emptyforspace);

        }
}
    


public class DueDetails extends JFrame {

    // Constructor receives the student name, id, and amount from the StaffPage
    public DueDetails(String staffName, String studentName, String studentId, String amount) {
        setTitle("CampusCash - Due Details");
        setSize(1420, 800);  // Increased size to match TransactionSummaryPage
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(10, 10, 30));
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Logo Panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(10, 10, 30));
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));  // Correct usage of BoxLayout
        mainPanel.add(logoPanel, BorderLayout.NORTH);

        // Logo for the application
        ImageIcon largeLogo1 = new ImageIcon("CampusCashJava\\Images\\logoh.png");
        JLabel logo1Label = new JLabel(new ImageIcon(largeLogo1.getImage().getScaledInstance(513, 106, Image.SCALE_SMOOTH)));
        logo1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(logo1Label);

        ImageIcon largeLogo2 = new ImageIcon("CampusCashJava\\Images\\Mits.png");
        JLabel logo2Label = new JLabel(new ImageIcon(largeLogo2.getImage().getScaledInstance(294, 105, Image.SCALE_SMOOTH)));
        logo2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(logo2Label);

        // Header Label
        JLabel headerLabel = new JLabel("Due Details");
        headerLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(headerLabel);

        // Content Panel to display student details
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(10, 10, 30));
        contentPanel.setLayout(new GridBagLayout());
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Rounded panel to display the details
        JPanel detailsPanel = new RoundedPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));  // Correct usage of BoxLayout
        detailsPanel.setPreferredSize(new Dimension(600, 400));
        detailsPanel.setBackground(Color.WHITE);
        contentPanel.add(detailsPanel);

        // Back Button Panel aligned to the left
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        backButtonPanel.setBackground(Color.WHITE);
        detailsPanel.add(backButtonPanel);

        // Set the back button's image

        
        ImageIcon backIcon = new ImageIcon("CampusCashJava\\Images\\Back.png");
        JLabel backLabel = new JLabel(new ImageIcon(backIcon.getImage().getScaledInstance(26, 43, Image.SCALE_SMOOTH)));
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButtonPanel.add(backLabel);

        // Action on back button
        backLabel.addMouseListener((MouseListener) new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new StaffPage(staffName).setVisible(true);
                DueDetails.this.dispose();
            }
        });

        detailsPanel.add(Box.createRigidArea(new Dimension(0, 0)));  // Increased spacing

        // Student details
        JLabel nameLabel = new JLabel("Name: " + studentName);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(nameLabel);

        detailsPanel.add(Box.createRigidArea(new Dimension(0, 35)));  // Space between labels

        JLabel idLabel = new JLabel("ID: " + studentId);
        idLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(idLabel);

        detailsPanel.add(Box.createRigidArea(new Dimension(0, 35)));  // Space between labels

        JLabel amountLabel = new JLabel("Amount Due: ₹" + amount);
        amountLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(amountLabel);

        detailsPanel.add(Box.createRigidArea(new Dimension(0, 35)));  // Space below the amount



        JLabel oldDueLabel = new JLabel("Old Due: ₹500");
        oldDueLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        oldDueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(oldDueLabel);

        detailsPanel.add(Box.createRigidArea(new Dimension(0, 35))); 
         // Space before current due
         int a = 500 - Integer.parseInt(amount);

        // Current due calculated
        JLabel currentDueLabel = new JLabel("Current Due: ₹" + a);

        currentDueLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        currentDueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(currentDueLabel);

        detailsPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        JLabel emptyforspace = new JLabel(" ");
       emptyforspace.setFont(new Font("SansSerif", Font.BOLD, 18));
       emptyforspace.setAlignmentX(Component.CENTER_ALIGNMENT);
       detailsPanel.add(emptyforspace);  // Extra space at the bottom
    }
}


}

