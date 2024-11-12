import java.util.ArrayList;
import java.util.Comparator;

class InventoryArray {
    ArrayList<Product> productArray = new ArrayList<>();

    public void addProduct(Product product) {
        productArray.add(product);
    }

    public boolean deleteProductById(int id) {
        for (Product product : productArray) {
            if (product.getId() == id) {
                productArray.remove(product);
                return true; // Product was found and deleted
            }
        }
        return false; // Product with given ID was not found
    }

    public Product findProductById(int id) {
        for (Product product : productArray) {
            if (product.getId() == id) {
                return product; // Product was found
            }
        }
        return null; // Product with given ID was not found
    }

    public void sortByPrice() {
        productArray.sort(Comparator.comparingDouble(Product::getPrice));
    }
}
