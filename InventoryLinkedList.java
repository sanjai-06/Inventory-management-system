import java.util.LinkedList;

public class InventoryLinkedList {
    LinkedList<Product> productList = new LinkedList<>();

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public void displayProducts() {
        for (Product product : productList) {
            System.out.println(product);
        }
    }
}
