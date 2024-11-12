import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EnhancedInventoryManagementSystem extends JFrame {
    InventoryArray inventoryArray = new InventoryArray();

    JLabel titleLabel;
    JTextField idField, nameField, priceField, quantityField;
    JComboBox<String> categoryDropdown;
    JTextArea displayArea;
    JTabbedPane tabbedPane;
    JPanel inventoryPanel;

    public EnhancedInventoryManagementSystem() {
        setTitle("Enhanced Inventory Management System");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set background color for the frame
        getContentPane().setBackground(new Color(255, 239, 204)); // Light peach color

        // Title Label
        titleLabel = new JLabel("Inventory Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102)); // Dark blue color
        add(titleLabel, BorderLayout.NORTH);

        // Create JTabbedPane for the Inventory Management
        tabbedPane = new JTabbedPane();

        // Create inventory panel
        inventoryPanel = createInventoryPanel();
        tabbedPane.addTab("Inventory", inventoryPanel);

        // Add tabbed pane to the center of the frame
        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createInventoryPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Labels and Text Fields
        JLabel idLabel = new JLabel("Product ID:");
        idField = new JTextField(10);

        JLabel nameLabel = new JLabel("Product Name:");
        nameField = new JTextField(15);

        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField(10);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(5);

        JLabel categoryLabel = new JLabel("Category:");
        String[] categories = {"Select Category", "Electronics", "Groceries", "Clothing", "Stationery"};
        categoryDropdown = new JComboBox<>(categories);

        // Buttons
        JButton addButton = createButton("Add Product", e -> addProduct());
        JButton displayButton = createButton("Display Products", e -> displayProducts());
        JButton resetButton = createButton("Reset Fields", e -> resetFields());
        JButton deleteButton = createButton("Delete Product", e -> deleteProduct());
        JButton searchButton = createButton("Search Product", e -> searchProduct());
        JButton sortButton = createButton("Sort by Price", e -> sortProductsByPrice());
        JButton clearDisplayButton = createButton("Clear Display", e -> displayArea.setText(""));
        JButton saveButton = createButton("Save to File", e -> saveToFile());
        JButton loadButton = createButton("Load from File", e -> loadFromFile());

        // Display Area
        displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);
        displayArea.setBackground(new Color(240, 240, 240)); // Light gray for text area
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Adding Components to Inventory Panel Layout
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(idLabel, gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(priceLabel, gbc);
        gbc.gridx = 1;
        panel.add(priceField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(quantityLabel, gbc);
        gbc.gridx = 1;
        panel.add(quantityField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(categoryLabel, gbc);
        gbc.gridx = 1;
        panel.add(categoryDropdown, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(addButton, gbc);
        gbc.gridx = 1;
        panel.add(displayButton, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(deleteButton, gbc);
        gbc.gridx = 1;
        panel.add(searchButton, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(sortButton, gbc);
        gbc.gridx = 1;
        panel.add(resetButton, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        panel.add(clearDisplayButton, gbc);
        gbc.gridx = 1;
        panel.add(saveButton, gbc);

        gbc.gridx = 0; gbc.gridy = 9;
        panel.add(loadButton, gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        return panel;
    }

    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setBackground(new Color(173, 216, 230)); // Light blue
        button.addActionListener(action);
        return button;
    }

    private void addProduct() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String category = (String) categoryDropdown.getSelectedItem();

            if (category.equals("Select Category")) {
                displayArea.append("Please select a valid category!\n");
                return;
            }

            Product product = new Product(id, name, price, quantity, category);
            inventoryArray.addProduct(product);
            displayArea.append("Product Added: " + name + " (ID: " + id + ")\n");
        } catch (Exception e) {
            displayArea.append("Error: " + e.getMessage() + "\n");
        }
    }

    private void displayProducts() {
        if (inventoryArray.getProducts().isEmpty()) {
            displayArea.setText("No products available.\n");
        } else {
            displayArea.setText("Inventory:\n");
            for (Product product : inventoryArray.getProducts()) {
                displayArea.append(product + "\n");
            }
        }
    }

    private void resetFields() {
        idField.setText("");
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
        categoryDropdown.setSelectedIndex(0);
    }

    private void deleteProduct() {
        String id = idField.getText();
        if (inventoryArray.deleteProductById(id)) {
            displayArea.append("Deleted Product with ID: " + id + "\n");
        } else {
            displayArea.append("Product not found with ID: " + id + "\n");
        }
    }

    private void searchProduct() {
        String id = idField.getText();
        Product product = inventoryArray.searchProductById(id);
        if (product != null) {
            displayArea.append("Found Product: " + product + "\n");
        } else {
            displayArea.append("Product not found with ID: " + id + "\n");
        }
    }

    private void sortProductsByPrice() {
        inventoryArray.sortProductsByPrice();
        displayArea.append("Sorted products by price.\n");
        displayProducts();
    }

    private void saveToFile() {
        try (FileWriter writer = new FileWriter("inventory.txt")) {
            for (Product product : inventoryArray.getProducts()) {
                writer.write(product + "\n");
            }
            displayArea.append("Saved inventory to file.\n");
        } catch (IOException e) {
            displayArea.append("Error saving inventory: " + e.getMessage() + "\n");
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                Product product = new Product(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]), parts[4]);
                inventoryArray.addProduct(product);
            }
            displayArea.append("Loaded inventory from file.\n");
        } catch (IOException e) {
            displayArea.append("Error loading inventory: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EnhancedInventoryManagementSystem frame = new EnhancedInventoryManagementSystem();
            frame.setVisible(true);
        });
    }

    // InventoryArray and Product classes
    class InventoryArray {
        private ArrayList<Product> products = new ArrayList<>();

        public void addProduct(Product product) {
            products.add(product);
        }

        public ArrayList<Product> getProducts() {
            return products;
        }

        public boolean deleteProductById(String id) {
            return products.removeIf(p -> p.getId().equals(id));
        }

        public Product searchProductById(String id) {
            for (Product p : products) {
                if (p.getId().equals(id)) {
                    return p;
                }
            }
            return null;
        }

        public void sortProductsByPrice() {
            products.sort(Comparator.comparingDouble(Product::getPrice));
        }
    }

    class Product {
        private String id;
        private String name;
        private double price;
        private int quantity;
        private String category;

        public Product(String id, String name, double price, int quantity, String category) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.category = category;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getCategory() {
            return category;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Price: $" + price + ", Quantity: " + quantity + ", Category: " + category;
        }
    }
}
