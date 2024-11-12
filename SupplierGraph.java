import java.util.ArrayList;
import java.util.HashMap;

public class SupplierGraph {
    HashMap<String, ArrayList<String>> adjacencyList = new HashMap<>();

    public void addConnection(String supplier, String warehouse) {
        adjacencyList.putIfAbsent(supplier, new ArrayList<>());
        adjacencyList.get(supplier).add(warehouse);
    }

    public void displayConnections() {
        for (String supplier : adjacencyList.keySet()) {
            System.out.println(supplier + " -> " + adjacencyList.get(supplier));
        }
    }
}
