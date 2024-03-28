public class TaskController {
    private TaskView view;
    private TaskModel model;

    public TaskController(TaskView view, TaskModel model) {
        this.view = view;
        this.model = model;
    }

    public void showTasks() {
        Task[] tasks = model.getTasks();
        view.showTasks(tasks);
    }

    public void addTask() {
        String name = view.getTaskName();
        String description = view.getTaskDescription();
        Task task = new Task(name, description);
        model.addTask(task);
    }

    public void completeTask() {
        int index = view.getTaskIndex("Task");
        model.completeTask(index);
    }

    public void run() {
        boolean running = true;
        while (running) {
            view.showTasks(model.getTasks());
            System.out.println("MENU");
            System.out.println("1. Add task");
            System.out.println("2. Complete task");
            System.out.println("3. Exit");
            System.out.println();
            int choice = view.getTaskIndex("Option");
            switch (choice) {
                case 0:
                    addTask();
                    break;
                case 1:
                    completeTask();
                    break;
                case 2:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            System.out.println();
        }
    }
}
