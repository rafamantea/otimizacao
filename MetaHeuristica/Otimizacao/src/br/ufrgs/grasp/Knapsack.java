package br.ufrgs.grasp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Knapsack {

    private final Map<Integer, List<Item>> items;
    private final List<Item> itemsList;
    
    private int minGroupIndex;
    private int totalWeight;
    private int totalValue;
    
    public Knapsack(Instance instance) {
        this.items = new HashMap();
        this.itemsList = new ArrayList();
        this.totalWeight = 0;
        this.totalValue = 0;
        this.minGroupIndex = instance.getMinGroupIndex();
        
        this.initMap(instance.getGroups());
    }
    
    private void initMap(int groups) {
        for(int i=0; i< groups; i++) {
            List<Item> items = new ArrayList();
            this.items.put(i, items);
        }
    }
    
    public void addItem(Item item) {
        List<Item> itemList = this.items.get(item.getGroup());
        
        if(itemList == null) {
            itemList = new ArrayList<Item>();
            this.items.put(item.getGroup(), itemList);
        }
        
        this.totalWeight += item.getWeight();
        this.totalValue += item.getValue();
        
        this.itemsList.add(item);
        itemList.add(item);
    }
    
    public void removeItem(Item item) {
        List<Item> items = this.items.get(item.getGroup());
        items.remove(item);
        itemsList.remove(item);
        
        this.totalValue -= item.getValue();
        this.totalWeight -= item.getWeight();
    }
    
    public List<Item> getItems() {
        return this.itemsList;
    }
    
    public List<Item> getMinGroupItems() {
        return this.items.get(this.minGroupIndex);
    }
    
    public int getTotalValue() {
        return this.totalValue;
    }
    
    public int getTotalWeight() {
        return this.totalWeight;
    }
    
    public int getMinGroupValue() {
        final List<Item> minGroupItems = this.items.get(this.minGroupIndex);
        int sum = 0;
        
        for(Item item: minGroupItems) {
            sum += item.getValue();
        }
        
        return sum;
    }
    
    public boolean hasItem(Item item) {
        for(List<Item> items: this.items.values()) {
            if(items.contains(item)) {
                return true;
            }
        }
        return false;
    }
}
