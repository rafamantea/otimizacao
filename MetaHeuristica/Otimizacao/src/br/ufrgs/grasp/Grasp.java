package br.ufrgs.grasp;

import java.util.List;
import java.util.Random;

public class Grasp {
    private static final int MAX_IT = 10;
    private static final int ALPHA = 5;
    private final int NEIGHBOURS_IT;
    
    private final Instance instance;
    private final Long seed;
    
    private Knapsack solution;
    
    public Grasp(Instance instance, Long seed) {
        this.instance = instance;
        this.instance.updateMinGroupIndex();
		
        this.seed = seed;
        Double neighbours = (instance.getN() * 0.10);
        this.NEIGHBOURS_IT = neighbours.intValue();
    }
        
    private int generateRandomInt(int maxValue) {
        final Random randomGenerator = (seed == null) ? new Random() : new Random(seed);

        return randomGenerator.nextInt(maxValue);
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
            
            aux = i;
            neighboursIt = 0;
            int maxSize = solution.getItems().size() - 1;
            
            while((neighboursIt < NEIGHBOURS_IT) && (aux < maxSize)) {
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

        this.initConstructiveSolution(constructiveSolution);    //Constroi solução sem elementos do grupo com menor lucro
        int it = 0;
        
        while((constructiveSolution.getTotalWeight() < instance.getCapacity()) && it < 10) {
            int index = generateRandomInt(instance.getMinGroupItems().size());
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
        int it = 0;
        
        while((solution.getTotalWeight() < instance.getCapacity()) && it < 10) {
            int index = generateRandomInt(this.instance.getItems().size());
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
    
    public int compute() {
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
        
        return newSolution.getMinGroupValue();
    }
}