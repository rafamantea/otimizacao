
public class Grasp {
    private static final int MAX_IT = 10;
    private static final int ALPHA = 5;
    
    private final Instance instance;
    
    private Knapsack solution;
    private Item currentItem;
    
    public Grasp(Instance instance) {
        this.instance = instance;
    }
    
    private Knapsack hillClimbing() {
        return null;
    }
    
    private Knapsack constructiveSolution() {
        return null;
    }
    
    private Knapsack buildInitialSolution() {
        Knapsack solution = new Knapsack(10);
        return null;
    }
    
    public void compute() {
        int iterationsInRow = 0;
        
        this.solution = this.buildInitialSolution();
        Knapsack newSolution;
        
        while(iterationsInRow < MAX_IT) {
            newSolution = this.constructiveSolution();
            newSolution = this.hillClimbing();
            
            if(newSolution.getMinGroupValue() > this.solution.getMinGroupValue()) {
                this.solution = newSolution;
            }
        }
    }
}
