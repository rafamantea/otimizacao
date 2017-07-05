package br.ufrgs.grasp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Instance {

    private int capacity;
    private int n;
    private int groups;
    private int minGroupIndex;
    private Map<Integer, List<Item>> items;
    private List<Item> itemsList;
    
    
    public Instance(int n, int capacity, int groups) {
        this.n = n;
        this.capacity = capacity;
        this.groups = groups;
        this.minGroupIndex = -1;
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
    
    public List<Item> getMinGroupItems() {
        return this.items.get(this.minGroupIndex);
    }
    
    public void updateMinGroupIndex() {
        int minSum = Integer.MAX_VALUE;
        int index = -1;
        
        for(int i =0; i <items.keySet().size(); i++) {
            List<Item> items = this.items.get(i);
            int sum = 0;
            
            for(Item item: items){
                sum += item.getValue();
            }
            
            if(sum < minSum) {
                index = i;
                minSum = sum;
            }
        }
        
        this.minGroupIndex = index;
    }
    
    public int getMinGroupIndex() {
        return this.minGroupIndex;
    }
}
