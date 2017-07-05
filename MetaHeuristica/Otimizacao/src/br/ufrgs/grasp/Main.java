package br.ufrgs.grasp;

public class Main {

    public static void main(String[] args) throws Exception {
        
        String path = "";
        Long seed = null;
        
        if(args != null && args.length == 2) {
            path = args[0];
            seed = Long.valueOf(args[1]);
            
        } else if(args != null && args.length == 1) {
            path = args[0];
            
        } else {
            System.out.println("REQUIRED: parameter: 'file path' ");
            System.out.println("OPTIONAL: parameter: 'seed' ");
            System.out.println("ERROR: missing 'file path' " );
            throw new Exception("Missing parameter");
        }
        
        Instance problemInstance = Parser.parse(path);
        Grasp grasp = new Grasp(problemInstance, seed);
        grasp.compute();
    }
}
