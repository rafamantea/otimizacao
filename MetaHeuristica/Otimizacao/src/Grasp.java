import java.util.List;
import java.util.Random;

public class Grasp {
    private static final int MAX_IT = 10;
    private static final int ALPHA = 5;
    private static final int NEIGHBOURS_IT = 10;
    
    private final Instance instance;
    
    private Knapsack solution;
    
    public Grasp(Instance instance) {
        this.instance = instance;
        this.instance.updateMinGroupIndex();
    }
    
    private Knapsack hillClimbing(Knapsack solution) {
        Knapsack newSolution = new Knapsack(instance);
        List<Item> items = solution.getItems();
        
        for(int i=0; i< items.size() ; i++) {
            Item bestItem = items.get(i);
            int aux = i;
            int neighboursIt = 0;
            
            while(neighboursIt < NEIGHBOURS_IT && aux > 0) {
                aux--;
                Item previousItem = items.get(aux);
                int newWeight = newSolution.getTotalWeight() + previousItem.getWeight();
                if(previousItem.getStrenght() > bestItem.getStrenght() && !solution.hasItem(previousItem) && newWeight <= instance.getCapacity()) {
                    bestItem = previousItem;
                }
                neighboursIt++;
            }
            
            while(neighboursIt < NEIGHBOURS_IT && aux <= solution.getItems().size() - 1) {
                aux++;
                Item nextItem = items.get(aux);
                int newWeight = newSolution.getTotalWeight() + nextItem.getWeight();
                if(nextItem.getStrenght() > bestItem.getStrenght() && !solution.hasItem(nextItem) && newWeight <= instance.getCapacity()) {
                    bestItem = nextItem;
                }
                neighboursIt++;
            }
            
            newSolution.addItem(bestItem);
        }
        
        return newSolution;
    }
    
    private Knapsack constructiveSolution() {
        final Knapsack constructiveSolution = new Knapsack(instance);
        final Random randomGenerator = new Random();

        this.initConstructiveSolution(constructiveSolution);    //Constroi solução sem elementos do grupo com menor lucro
        int it = 0;
        
        while((constructiveSolution.getTotalWeight() < instance.getCapacity()) && it < 10) {
            int index = randomGenerator.nextInt(instance.getMinGroupItems().size());
            Item randomItem = instance.getMinGroupItems().get(index);

            int newWeight = constructiveSolution.getTotalWeight() + randomItem.getWeight();
            if(newWeight <= instance.getCapacity() && !constructiveSolution.hasItem(randomItem)) {
                constructiveSolution.addItem(randomItem);
                it = 0;
            } else {
                it++;
            }
        }
        
        return constructiveSolution;
    }
    
    private void initConstructiveSolution(Knapsack constructiveSolution) {
        List<Item> minGroupItems = solution.getMinGroupItems();
        for(Item item: solution.getItems()) {
            if(!minGroupItems.contains(item)) {
                constructiveSolution.addItem(item);
            }
        }
    }
    
    private Knapsack buildInitialSolution() {
        final Knapsack solution = new Knapsack(instance);
        final Random randomGenerator = new Random();
        int it = 0;
        
        while((solution.getTotalWeight() < instance.getCapacity()) && it < 10) {
            int index = randomGenerator.nextInt(this.instance.getItems().size());
            Item randomItem = instance.getItems().get(index);

            int newWeight = solution.getTotalWeight() + randomItem.getWeight();
            if(newWeight <= instance.getCapacity() && !solution.hasItem(randomItem)) {
                solution.addItem(randomItem);
                it = 0;
            } else {
                it++;
            }
        }
        
        return solution;
    }
    
    public void compute() {
        int iterationsInRow = 0;
        
        this.solution = this.buildInitialSolution();
        Knapsack newSolution = null;
        
        while(iterationsInRow < MAX_IT) {
            newSolution = this.constructiveSolution();
            newSolution = this.hillClimbing(newSolution);
            
            if(newSolution.getMinGroupValue() > this.solution.getMinGroupValue()) {
                this.solution = newSolution;
                
            } else {
                
                iterationsInRow++;
            }
        }
        
        System.out.println("Z = " + newSolution.getMinGroupValue());
    }
}
