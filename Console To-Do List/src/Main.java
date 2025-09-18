import Task.Task;
import Task.TaskManager;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String command;
        String message;
        String date;

        Scanner scanner = new Scanner(System.in);


        TaskManager taskManager = new TaskManager();

        while(true)
        {
            System.out.print("Enter Command:");
            command = scanner.nextLine();

            if(command.startsWith("a") && command.length()==1)
            {
                Task task = new Task();

                System.out.print("Message:");
                message = scanner.nextLine();

                System.out.print("Date:");
                date = scanner.nextLine();

                task.setMessage(message);
                task.setDate(date);

                taskManager.addTask(task);
            }
            else if(command.startsWith("v") && command.contains("/"))
            {
                String stringDate = command.split("v")[1];

                taskManager.viewSingleTaskByDate(stringDate);
            }
            else if(command.startsWith("v"))
            {
                long id = Long.parseLong(command.split("v")[1]);

                taskManager.viewSingleTaskById(id);
            }
            else if(command.startsWith("d"))
            {
                long id = Long.parseLong(command.split("d")[1]);

                taskManager.deleteTask(id);
            }
            else{
                System.out.println("Invalid Command Format");
            }
        }

    }
}