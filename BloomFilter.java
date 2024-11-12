import java.util.BitSet;
import java.util.function.Function;

public class BloomFilter {
    private BitSet bitset;
    private int bitArraySize;
    private Function<String, Integer>[] hashFunctions;

    public BloomFilter(int size, Function<String, Integer>[] hashFunctions) {
        this.bitArraySize = size;
        this.bitset = new BitSet(size);
        this.hashFunctions = hashFunctions;
    }

    public void add(String item) {
        for (Function<String, Integer> hashFunction : hashFunctions) {
            int hash = hashFunction.apply(item) % bitArraySize;
            bitset.set(Math.abs(hash));
        }
    }

    public boolean mightContain(String item) {
        for (Function<String, Integer> hashFunction : hashFunctions) {
            int hash = hashFunction.apply(item) % bitArraySize;
            if (!bitset.get(Math.abs(hash))) return false;
        }
        return true;
    }
}
