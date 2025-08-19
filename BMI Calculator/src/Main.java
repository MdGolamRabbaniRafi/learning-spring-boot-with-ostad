import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Weight: ");
        double weight = sc.nextDouble();
        sc.nextLine();
        System.out.print("Height: ");
        String height = sc.nextLine();
        double totalHeightInMeter = stringHeightToMeter(height);
        double BMI = weight / Math.pow(totalHeightInMeter, 2);
        displayOutput(BMI);
    }

    public static String getCategory(double BMI)
    {
        String category = "";
        if(BMI<18.5)
        {
            category = "Underweight";
        } else if (BMI<25 ) {
            category = "Normal Weight";
        } else if (BMI<30 ) {
            category = "Overweight";
        } else if (BMI>30 ) {
            category = "Obese";
        }

        return category;
    }

    public static double stringHeightToMeter(String height){
        int feet = 0;
        int inches = 0;

        String[] splittedHeight = height.split("ft|in");
        if(height.contains("ft") && height.contains("in"))
        {
            feet = Integer.parseInt(splittedHeight[0].trim());
            inches = Integer.parseInt(splittedHeight[1].trim());
        }
        else if(height.contains("ft"))
        {
            feet = Integer.parseInt(splittedHeight[0].trim());
        }
        else if(height.contains("in"))
        {
            inches = Integer.parseInt(splittedHeight[0].trim());
        }

        return (feet * 12 + inches) * 0.0254;
    }

    public static void recursive(int BMI)
    {
        if(BMI==0)
        {
            return;
        }
        System.out.print("*");
        recursive(BMI-1);
    }

    public static void displayOutput(double BMI)
    {
        String category = getCategory(BMI);
        recursive((int)BMI);
        System.out.println();
        System.out.printf("Your BMI is: %.3f\n", BMI);
        System.out.println("Category: " + category);
        recursive((int)BMI);

    }
}