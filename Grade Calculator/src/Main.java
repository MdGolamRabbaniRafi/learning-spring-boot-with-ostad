import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Your Mark:");
        int mark = scanner.nextInt();
        char grade;
        String message = "";

        if(mark>=90)
        {
            message = "Well done";
            grade = 'A';
        } else if (mark>=80) {
            message = "Well done";
            grade = 'B';
        }
        else if (mark>=70) {
            message = "Needs improvement";
            grade = 'C';
        }
        else if (mark>=60) {
            message = "Needs improvement";
            grade = 'D';
        }
        else{
            message = "Failed";
            grade = 'F';
        }

        System.out.println(grade+" : "+message);


    }
}