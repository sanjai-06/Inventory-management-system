import java.util.TreeMap;

public class InventoryRedBlackTree {
    TreeMap<Integer, Product> stockTree = new TreeMap<>();

    public void addProduct(Product product) {
        stockTree.put(product.quantity, product);
    }

    public Product getLowestStockProduct() {
        return stockTree.firstEntry().getValue();
    }
}
