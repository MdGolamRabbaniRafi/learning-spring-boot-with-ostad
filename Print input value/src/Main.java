import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your full name: ");
        String name = input.nextLine();
        String reasonOfLearningJava = "Why I'm learning Java: Java is a powerful, platform-independent language used in many areas including web, mobile, and enterprise applications.";
        String careerGoal = "My career goal: I want to become a skilled software developer who builds impactful applications.";
        System.out.println("My name: "+name);
        System.out.println(reasonOfLearningJava);
        System.out.println(careerGoal);
        input.close();

    }
}