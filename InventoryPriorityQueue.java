import java.util.PriorityQueue;

public class InventoryPriorityQueue {
    PriorityQueue<Product> reorderQueue = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.quantity, p2.quantity));

    public void addProduct(Product product) {
        reorderQueue.add(product);
    }

    public Product getCriticalProduct() {
        return reorderQueue.peek();
    }
}
