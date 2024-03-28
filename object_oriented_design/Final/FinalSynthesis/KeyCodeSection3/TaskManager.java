public class TaskManager {

    private LinkedList<Task> taskList;

    public TaskManager() {
        taskList = new AbstractedList<>();
    }

    public Task addTask(String title, String description) {
        Task task = new Task(title, description);
        taskList.add(task);
        return task;
    }

    public void removeTask(Task t) {
        taskList.remove(t);
    }

    public void completeTask(Task t) {
        t.setCompleted(true);
    }

    public void printTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.print(i + 1);
            System.out.println(" " + task.getTitle() + ":" + task.getDescription());
        }
    }

    public void printCompletedTasks() {
        for (int i = 0, j = 1; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.isCompleted()) {
                System.out.print(j);
                System.out.println(" " + task.getTitle() + ":" + task.getDescription());
                j++;
            }
        }
    }
}
