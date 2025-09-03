import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static Student findLowestCGPA(List<Student> students) {
        if (students.isEmpty()) return null;
        Student lowest = students.getFirst();
        for (Student s : students) {
            if (s.getCgpa() < lowest.getCgpa()) {
                lowest = s;
            }
        }
        return lowest;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter info for student " + (i + 1) + ":");
            System.out.print("ID: ");
            long id = sc.nextLong();
            sc.nextLine();

            System.out.print("Full Name: ");
            String fullName = sc.nextLine();

            System.out.print("Blood Group: ");
            String bloodGroup = sc.nextLine();

            System.out.print("CGPA: ");
            float cgpa = sc.nextFloat();
            sc.nextLine();

            students.add(new Student(id, fullName, bloodGroup, cgpa));
        }

        System.out.print("Enter query: ");
        String query = sc.nextLine();

        System.out.println("\nMatching Students:");
        boolean found = false;
        for (Student s : students) {
            if (s.matches(query)) {
                s.print();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students matched the query.");
        }

        Student lowest = findLowestCGPA(students);
        if (lowest != null) {
            System.out.println("\nStudent with Lowest CGPA:");
            lowest.print();
        }

        sc.close();
    }
}