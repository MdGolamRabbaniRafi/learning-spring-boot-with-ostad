package File;
import java.io.*;
        import java.nio.file.*;
        import java.util.*;
public class FileHandler {

    public static List<Operation> readOperations(String filename) {
        List<Operation> operations = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));

            for (String line : lines) {
                try {
                    String[] parts = line.trim().split(" ");
                    if (parts.length != 3) continue;

                    double numOne = Double.parseDouble(parts[0]);
                    String operator = parts[1];
                    double numTwo = Double.parseDouble(parts[2]);
                    double result = calculate(numOne, numTwo, operator);

                    operations.add(new Operation(numOne, numTwo, operator, result));
                } catch (Exception e) {
                    System.err.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
        }
        return operations;
    }

    public static void saveToJson(List<Operation> operations, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("[\n");
            for (int i = 0; i < operations.size(); i++) {
                writer.write(operations.get(i).toJson());
                if (i < operations.size() - 1) writer.write(",");
                writer.write("\n");
            }
            writer.write("]");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filename);
        }
    }

    public static List<Operation> readFromJson(String filename) {
        List<Operation> operations = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("{") && line.endsWith("}")) {
                    operations.add(Operation.fromJson(line));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + filename);
        }
        return operations;
    }

    private static double calculate(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return b != 0 ? a / b : Double.NaN;
            default: return Double.NaN;
        }
    }
}
