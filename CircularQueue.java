public class CircularQueue {
    private Product[] queue;
    private int front, rear, size, capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        queue = new Product[capacity];
        front = size = 0;
        rear = capacity - 1;
    }

    public void enqueue(Product product) {
        if (size == capacity) return;
        rear = (rear + 1) % capacity;
        queue[rear] = product;
        size++;
    }

    public Product dequeue() {
        if (size == 0) return null;
        Product product = queue[front];
        front = (front + 1) % capacity;
        size--;
        return product;
    }
}
