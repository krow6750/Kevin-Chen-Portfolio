public class TaskManager {
    public static void main(String[] args) {
        TaskView view = new TaskView();
        TaskModel model = new TaskModel(10);
        TaskController controller = new TaskController(view, model);
        controller.run();
    }
}