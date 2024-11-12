public class InventoryBST {
    class Node {
        Product product;
        Node left, right;

        Node(Product product) {
            this.product = product;
            left = right = null;
        }
    }

    private Node root;

    public void addProduct(Product product) {
        root = addRecursive(root, product);
    }

    private Node addRecursive(Node current, Product product) {
        if (current == null) {
            return new Node(product);
        }
        if (product.id < current.product.id) {
            current.left = addRecursive(current.left, product);
        } else if (product.id > current.product.id) {
            current.right = addRecursive(current.right, product);
        }
        return current;
    }
}
