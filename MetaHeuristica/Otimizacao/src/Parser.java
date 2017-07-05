import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    
    public static Instance parse(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        Instance problemInstance;
        
        try {
            String line = br.readLine();
            int n, groups, capacity;
            
            n = Integer.valueOf(line.toString());
            line = br.readLine();
            
            groups = Integer.valueOf(line.toString());
            line = br.readLine();

            capacity = Integer.valueOf(line.toString());
            line = br.readLine();

            problemInstance = new Instance(n, capacity, groups);
            
            String trimmedLine = line.trim().replaceAll(" +", " ");
            String[] groupsLenght = trimmedLine.split("\\s+");
            
            for(int i=0; i<groups; i++) {
                
                int length = Integer.valueOf(groupsLenght[i]);
                
                for(int j=0; j< length; j++) {
                    
                    line = br.readLine();
                    trimmedLine = line.trim().replaceAll(" +", " ");
                    String[] newLenght = trimmedLine.split("\\s+");
                    
                    int weight = Integer.valueOf(newLenght[0]);
                    int value = Integer.valueOf(newLenght[1]);
                    
                    problemInstance.addItem(i, value, weight);
                }
            }
        } finally {
            br.close();
        }
        
        return problemInstance;
    }
}
