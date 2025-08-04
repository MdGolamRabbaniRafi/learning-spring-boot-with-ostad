import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float TotalInterest;
        System.out.print("Enter Amount:");
        int amount=sc.nextInt();
        System.out.print("Enter Interest Rate:");

        float InterestRate=sc.nextFloat();
        sc.nextLine();
        System.out.print("Enter time(y/m):");
        String time = sc.nextLine();
        char LastIndexValue=time.charAt(time.length()-1);
        float OrginalTime = Float.parseFloat(time.substring(0,time.length()-1));
        if(LastIndexValue=='m')
        {
            OrginalTime=OrginalTime/12;

        }
        TotalInterest=OrginalTime*InterestRate*amount/100;
        System.out.printf("Total Interest: %.2f\n", TotalInterest);

    }
}