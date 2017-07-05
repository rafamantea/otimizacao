import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Instance {

    private int capacity;
    private int n;
    private int groups;
    private Map<Integer, List<Item>> items;
    private List<Item> itemsList;
    
    
    public Instance(int n, int capacity, int groups) {
        this.n = n;
        this.capacity = capacity;
        this.groups = groups;
        this.items = new HashMap();
        this.itemsList = new ArrayList();
        
        for(int i=0; i<groups; i++) {
            List<Item> items = new ArrayList();
            this.items.put(i, items);
        }
    }
    
    public int getN() { return this.n; }
    public int getCapacity() { return this.capacity; }
    public int getGroups() { return this.groups; }
    
    public void addItem(int group, int value, int weight) {
        Item item = new Item(value, weight, group);
        List<Item> items = this.items.get(group);
        items.add(item);
        this.itemsList.add(item);
    }
    
    public List<Item> getItems() {
        return this.itemsList;
    }
}
