import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Instance problemInstance = Parser.parse("C:\\Users\\I852842\\workspace\\Otimizacao\\bin\\test.txt");
        Grasp grasp = new Grasp(problemInstance);
        grasp.compute();
    }

}
