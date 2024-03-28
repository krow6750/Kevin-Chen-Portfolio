import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        List<Employee> employees = readEmployeesFromCSV("company.csv");
        Map<Integer, Employee> employeeMap = createEmployeeMap(employees);
        Employee ceo = getCEOFrontList(employees);
        buildHierarchy(ceo, employeeMap);
        printHierarchy(ceo, 0);
    }

    public static List<Employee> readEmployeesFromCSV(String filename) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String role = data[2];
                int managerId = Integer.parseInt(data[3]);
                Employee employee;
                if (role.equals("CEO")) {
                    employee = new CEO(id, name);
                } else if (role.equals("Manager")) {
                    employee = new Manager(id, name);
                } else if (role.equals("Supervisor")) {
                    employee = new Supervisor(id, name);
                } else {
                    employee = new Employee(id, name);
                }
                employees.add(employee);
                employee.setManagerId(managerId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static Map<Integer, Employee> createEmployeeMap(List<Employee> employees) {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.getId(), employee);
        }
        return employeeMap;
    }

    public static Employee getCEOFrontList(List<Employee> employees) {
        for (Employee employee : employees) {
            if (employee instanceof CEO) {
                return employee;
            }
        }
        return null;
    }

    public static void buildHierarchy(Employee employee, Map<Integer, Employee> employeeMap) {
        List<Employee> directReports = new ArrayList<>();
        for (Employee e : employeeMap.values()) {
            if (e.getManagerId() == employee.getId()) {
                directReports.add(e);
            }
        }
        employee.setDirectReports(directReports);
        for (Employee directReport : directReports) {
            buildHierarchy(directReport, employeeMap);
        }
    }

    public static void printHierarchy(Employee employee, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.print("- ");
        employee.print();
        for (Employee directReport : employee.getDirectReports()) {
            printHierarchy(directReport, level + 1);
        }
    }
}

class Employee {
    private int id;
    public String name;
    private int managerId;
    private List<Employee> directReports;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
        this.directReports = new ArrayList<>();
    }

    public void print(){
        System.out.println(name + " (Employee)");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }
}

class CEO extends Employee {
    public CEO(int id, String name) {
        super(id, name);
    }

    public void print(){
        System.out.println(name + " (CEO)");
    }
}

class Manager extends Employee {
    public Manager(int id, String name) {
        super(id, name);
    }

    public void print(){
        System.out.println(name + " (Manager)");
    }
}

class Supervisor extends Employee {
    public Supervisor(int id, String name) {
        super(id, name);
    }

    public void print(){
        System.out.println(name + " (Supervisor)");
    }
}