package File;

public class Operation {
    private double numOne;
    private double numTwo;
    private String operator;
    private double result;

    public Operation(double numOne, double numTwo, String operator, double result) {
        this.numOne = numOne;
        this.numTwo = numTwo;
        this.operator = operator;
        this.result = result;
    }

    public double getNumOne() {
        return numOne;
    }

    public double getNumTwo() {
        return numTwo;
    }

    public String getOperator() {
        return operator;
    }

    public double getResult() {
        return result;
    }

    public String toJson() {
        return String.format(
                "{ \"numOne\": %.1f, \"numTwo\": %.1f, \"operator\": \"%s\", \"result\": %.1f }",
                numOne, numTwo, operator, result
        );
    }

    public static Operation fromJson(String jsonLine) {
        jsonLine = jsonLine.replace("{", "")
                .replace("}", "")
                .replace("\"", "")
                .trim();

        // Split by comma (not remove!)
        String[] parts = jsonLine.split(",");

        double numOne = 0, numTwo = 0, result = 0;
        String operator = "";

        for (String part : parts) {
            String[] kv = part.split(":");
            if (kv.length < 2) continue;

            String key = kv[0].trim();
            String value = kv[1].trim();

            switch (key) {
                case "numOne":
                    numOne = Double.parseDouble(value);
                    break;
                case "numTwo":
                    numTwo = Double.parseDouble(value);
                    break;
                case "operator":
                    operator = value;
                    break;
                case "result":
                    result = Double.parseDouble(value);
                    break;
            }
        }

        return new Operation(numOne, numTwo, operator, result);
    }

    @Override
    public String toString() {
        return numOne + " " + operator + " " + numTwo + " = " + result;
    }
}
