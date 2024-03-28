

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * This class represents an organization with employees.
 */

public class OrganizationImpl implements Organization {
    private Employee root;

    public OrganizationImpl(String nameCEO, double pay, Gender gender) {
        root = new Supervisor(nameCEO,pay,gender);
    }

    @Override
    public void addEmployee(Employee employee, String supervisor) {
        root = root.addSupervisee(supervisor, employee);
    }

    @Override
    public String printEmployee(String name) {
        Employee employee = root.findEmployee(name);
        if (employee != null) {
         return (employee.getName() + " " + employee.getGender() + " " + employee.getAnnualPay());
        } else {
           return null;
        }
    }

    @Override
    public void addEmployee(String name, double pay, Gender gender, String supervisorName) {
        Employee newEmployee = new NonManagerEmployee(name,pay,gender);
        root = root.addSupervisee(supervisorName,newEmployee);
    }

    @Override
    public void addContractEmployee(String name, double pay, Gender gender, int
            endDate, int endMonth, int endYear, String supervisorName) {
        Employee newEmployee = new ContractEmployee(name,pay,gender,endDate,endMonth,
                endYear);
        root = root.addSupervisee(supervisorName,newEmployee);
    }

    @Override
    public int getSize() {
        return root.count(b -> true);
    }

    @Override
    public int getSizeByGender(Gender gender) {
        return root.count(b -> b.getGender() == gender);
    }

    @Override
    public List<String> allEmployees() {
        return root.toList().stream().map(e->e.getName()).collect(Collectors
                .toList());
    }

    @Override
    public List<String> allEmployees(Predicate<Employee> condition) {
        return root.toList().stream().filter(condition).map(e->e.getName()).collect(Collectors
                .toList());
    }

    @Override
    public int countPayAbove(double amount) {
        return root.count(b -> b.getAnnualPay() > amount);
    }

    @Override
    public int terminatedBefore(int date,int month,int year) {
        LocalDate threshold;

        try {
            threshold = LocalDate.of(year,month,date);
        }
        catch (DateTimeException e) {
            return 0;
        }
        return root.count(b->{
            if (b.getEmploymentEndDate().equals("XXXXXXXX"))
                return false;
            else {
                LocalDate d = LocalDate.parse(b.getEmploymentEndDate(),
                        DateTimeFormatter.ofPattern("MMddyyyy"));
                return d.compareTo(threshold)<0;
            }
        });
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        var list = root.toList();
        for (Employee e : list) {
            sb.append( printEmployee(e.getName()));
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public int getSize(Predicate<Employee> condition) {
        return root.count(condition);
    }
}
