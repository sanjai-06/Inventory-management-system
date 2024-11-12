class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String category;

    public Product(int id, String name, double price, int quantity, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price; // Added this method
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity + ", Category: " + category;
    }

    public String toFileFormat() {
        return id + "," + name + "," + price + "," + quantity + "," + category;
    }
}
