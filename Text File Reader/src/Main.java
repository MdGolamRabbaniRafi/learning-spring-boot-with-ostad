import File.FileHandler;
import File.Operation;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.json";

        List<Operation> operations = FileHandler.readOperations(inputFile);

        FileHandler.saveToJson(operations, outputFile);

        List<Operation> loadedOperations = FileHandler.readFromJson(outputFile);

        for (Operation op : loadedOperations) {
            System.out.println(op);
        }
    }
}