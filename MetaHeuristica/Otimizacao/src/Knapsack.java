import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Knapsack {

    private int capacity;
    private int n;
    
    private final Map<Integer, List<Item>> items;
    private int minGroupIndex;
    
    public Knapsack(int capacity) {
        this.items = new HashMap();
        this.capacity = capacity;
    }
    
    public Knapsack() {
        this(0);
    }
    
    public List<Item> getItems() {
        final List<Item> allItems = new ArrayList();
        
        for(List<Item> items: this.items.values()) {
            allItems.addAll(items);
        }
        
        return allItems; 
    }
    
    public int getTotalValue() {
        int sum = 0;
        for(List<Item> items: this.items.values()) {
            for(Item item: items) {
                sum += item.getValue();
            }
        }
        
        return sum;
    }
    
    public int getMinGroupValue() {
        final List<Item> minGroupItems = this.items.get(this.minGroupIndex);
        int sum = 0;
        
        for(Item item: minGroupItems) {
            sum += item.getValue();
        }
        
        return sum;
    }
    
    public int getTotalWeight() {
        int sum = 0;
        for(List<Item> items: this.items.values()) {
            for(Item item: items) {
                sum += item.getWeight();
            }
        }
        
        return sum;
    }
}
