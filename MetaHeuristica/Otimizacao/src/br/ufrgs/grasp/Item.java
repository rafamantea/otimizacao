package br.ufrgs.grasp;

public class Item {

    private final int value;
    private final int weight;
    private final int group;
    
    public Item(int value, int weight, int group) {
        this.value = value;
        this.weight = weight;
        this.group = group;
    }
    
    public int getValue() { return this.value; }
    public int getWeight() { return this.weight; }
    public int getGroup() { return this.group; }
    public double getStrenght() { return ( (double) this.value / (double) this.weight) ; }
}
