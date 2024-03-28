import java.util.Scanner;

public class TaskView {
    private Scanner scanner;

    public TaskView() {
        this.scanner = new Scanner(System.in);
    }

    public void showTasks(Task[] tasks) {
        if (tasks.length == 0) {
            return;
        }
        System.out.println("\nTASKS:");
        for (int i = 0; i < tasks.length; i++) {
            Task task = tasks[i];
            String status = task.isCompleted() ? "[x]" : "[ ]";
            System.out.printf("%d. %s %s\n", i + 1, status, task.getName());
        }
        System.out.println("\n");
    }

    public String getTaskName() {
        scanner.nextLine();
        System.out.print("Enter task name: ");
        return scanner.nextLine();
    }

    public String getTaskDescription() {
        System.out.print("Enter task description: ");
        return scanner.nextLine();
    }

    public int getTaskIndex(String Type) {
        System.out.print("Enter " + Type + " number: ");
        return scanner.nextInt() - 1;
    }
}
