public class Main{
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
    
        Task task1 = taskManager.addTask("Do laundry", "Wash and fold clothes");
        Task task2 = taskManager.addTask("Buy groceries", "Go to the store and buy food");
    
        System.out.println("All tasks:");
        taskManager.printTasks();
    
        taskManager.completeTask(task1);
    
        System.out.println("\nCompleted tasks:");
        taskManager.printCompletedTasks();
    
        taskManager.removeTask(task2);
    
        System.out.println("Remaining Tasks:");
        taskManager.printTasks();
    }
    
}