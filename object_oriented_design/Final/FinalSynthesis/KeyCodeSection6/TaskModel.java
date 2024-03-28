public class TaskModel {
    private Task[] tasks;
    private int taskCount;

    public TaskModel(int capacity) {
        this.tasks = new Task[capacity];
        this.taskCount = 0;
    }

    public void addTask(Task task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
        } else {
            System.out.println("Task list is full.");
        }
    }

    public void completeTask(int index) {
        if (index >= 0 && index < taskCount) {
            Task task = tasks[index];
            task.setCompleted(true);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public Task[] getTasks() {
        Task[] result = new Task[taskCount];
        for (int i = 0; i < taskCount; i++) {
            result[i] = tasks[i];
        }
        return result;
    }
}