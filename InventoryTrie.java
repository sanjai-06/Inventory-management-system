import java.util.HashMap;

class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfProduct;
}

public class InventoryTrie {
    TrieNode root = new TrieNode();

    public void insert(String productCode) {
        TrieNode current = root;
        for (char c : productCode.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.isEndOfProduct = true;
    }

    public boolean search(String productCode) {
        TrieNode current = root;
        for (char c : productCode.toCharArray()) {
            current = current.children.get(c);
            if (current == null) return false;
        }
        return current.isEndOfProduct;
    }
}
