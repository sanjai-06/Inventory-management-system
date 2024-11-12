import java.util.HashMap;

public class InventoryHashTable {
    HashMap<Integer, Product> productMap = new HashMap<>();

    public void addProduct(Product product) {
        productMap.put(product.id, product);
    }

    public Product getProduct(int productId) {
        return productMap.get(productId);
    }
}
