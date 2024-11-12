import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryManagementApp extends JFrame {

    public InventoryManagementApp() {
        setTitle("Inventory Management App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title Page
        showTitlePage();
    }

    private void showTitlePage() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(new Color(255, 239, 204)); // Light peach color

        // Title Label
        JLabel titleLabel = new JLabel("Welcome to the Inventory Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102)); // Dark blue color
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Start Button
        JButton startButton = new JButton("Start Inventory Management");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setBackground(new Color(102, 204, 255)); // Light blue
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the Inventory Home Page
                showInventoryHomePage();
            }
        });

        // Add button to the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        titlePanel.add(buttonPanel, BorderLayout.SOUTH);

        add(titlePanel);
        revalidate();
        repaint();
    }

    private void showInventoryHomePage() {
        getContentPane().removeAll(); // Remove the title page

        // Set background color for the frame
        getContentPane().setBackground(new Color(255, 239, 204)); // Light peach color

        // Title Label
        JLabel titleLabel = new JLabel("Welcome to the Inventory Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102)); // Dark blue color
        add(titleLabel, BorderLayout.NORTH);

        // Button to go to EnhancedInventoryManagementSystem
        JButton goToInventoryButton = new JButton("Go to Inventory Management");
        goToInventoryButton.setFont(new Font("Arial", Font.BOLD, 18));
        goToInventoryButton.setBackground(new Color(102, 204, 255)); // Light blue
        goToInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Enhanced Inventory Management System page
                SwingUtilities.invokeLater(() -> {
                    EnhancedInventoryManagementSystem app = new EnhancedInventoryManagementSystem();
                    app.setVisible(true);
                    dispose(); // Close the homepage
                });
            }
        });

        // Add button to the center
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(goToInventoryButton);
        add(buttonPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InventoryManagementApp app = new InventoryManagementApp();
            app.setVisible(true);
        });
    }
}
