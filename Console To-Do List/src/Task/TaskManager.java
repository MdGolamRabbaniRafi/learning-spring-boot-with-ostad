package Task;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private HashSet<Task> tasks = new HashSet<>();

    public void addTask(Task task)
    {
        task.setId(randomIdGenerator());
        tasks.add(task);

        viewTask();

    }

    public Task findTaskById(long id)
    {
        return tasks.stream().filter(task -> task.getId()==id).findFirst().orElse(null);
    }

    public List<Task> findTasksByDate(String date) {
        return tasks.stream()
                .filter(task -> task.getDate().equals(date))
                .collect(Collectors.toList());
    }


    public void deleteTask(long id) {
        Task task = findTaskById(id);

        if (task == null) {
            System.out.println("Task with id " + id + " not found!");
            return;
        }

        tasks.remove(task);
        System.out.println("Task with id " + id + " deleted.");
        viewTask();
    }

    public void viewTask() {
        System.out.println();

        System.out.println("--------All Task--------");
        System.out.println();

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            tasks.stream()
                    .map(task -> "Id: " + task.getId() + "\n"
                            + "Message: " + task.getMessage() + "\n"
                            + "Date: " + task.getDate() + "\n")
                    .forEach(System.out::println);
        }

        System.out.println("--------End--------");
        System.out.println();
    }



    public void viewSingleTask(Task task) {

        System.out.println();

        System.out.println("Id:"+task.getId());
        System.out.println("Message:"+task.getMessage());
        System.out.println("Date:"+task.getDate());
        System.out.println();


    }

    public void viewSingleTaskByDate(String date) {

        List<Task> tasksOnDate = findTasksByDate(date);

        if (tasksOnDate.isEmpty()) {
            System.out.println("No tasks found for date " + date + "!");
            return;
        }

        System.out.println("\n--------Tasks on " + date + "--------");
        tasksOnDate.forEach(this::viewSingleTask);
        System.out.println("------------End------------\n");

    }


    public void viewSingleTaskById(long id) {

        Task task = findTaskById(id);

        if (task == null) {
            System.out.println("Task with id " + id + " not found!");
            return;
        }
        System.out.println();
        System.out.println("--------Print Task Details--------");

        viewSingleTask(task);

        System.out.println("------------End------------");
        System.out.println();
    }

    public long randomIdGenerator()
    {

        Date currentDate = new Date();
        return currentDate.getTime();
    }

}
