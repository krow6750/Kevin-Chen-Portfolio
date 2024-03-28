/**
 * Name: Kevin Chen
 * Assignment: Lab 5
 * Date: 04/03/2023
 * Notes: Organization reimplementation using Trees
 */

/*
This class has to be redesigned to use the generic tree node system instead of the former system. 
I've included a backup of this file in case it is useful to you. 
You'll need to populate the body based on the module and add the requested additional functionality.
*/


import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class OrganizationImpl implements Organization {

    private TreeNode<Employee> root;

    public OrganizationImpl(String name, double pay, Gender gender) {
        root = new LeafNode<>(new Supervisor(name, pay, gender));
    }

    private void addSupervisee(String supervisorName, Employee supervisee) {
        root = root.map((e) -> e.getName().equals(supervisorName) ? e.addSupervisee(supervisorName, supervisee) : e);
    }

    private void addSupervisedEmployee(String supervisorName, Employee employee) {
        root = root.addChild((e) -> e.getName().compareTo(employee.getName()) < 0, new LeafNode<>(employee));
        addSupervisee(supervisorName, employee);
    }

    @Override
    public void addEmployee(String name, double pay, Gender gender, String supervisorName) {
        addEmployee(new InternalEmployee(name, pay, gender), supervisorName);
    }

    @Override
    public void addContractEmployee(String name, double pay, Gender gender, int endDate, int endMonth, int endYear, String supervisorName) {
        addEmployee(new ContractEmployee(name, pay, gender, endDate, endMonth, endYear), supervisorName);
    }

    public int count(Predicate<Employee> predicate) {
        return root.reduce(0, (n, e) -> predicate.test(e) ? n + 1 : n);
    }


    @Override
    public int getSize() {
        return count(_e -> true);
    }

    @Override
    public int getSizeByGender(Gender gender) {
        return count(e -> e.getGender().equals(gender));
    }

    @Override
    public List<String> allEmployees() {
        return allEmployees(_e -> true);
    }

    public List<String> allEmployees(Predicate<Employee> predicate) {
        ArrayList<String> employees = new ArrayList<>();
        for (Employee employee : root.toList())
            if (predicate.test(employee))
                employees.add(employee.getName());
        return employees;
    }

    public void addEmployee(NonManagerEmployee employee, String supervisorName) {
        addSupervisedEmployee(supervisorName, employee);
    }

    //@Override
    public int countPayAbove(double amount) {
        return count(b -> b.getAnnualPay() > amount);
    }

    //@Override
    public int terminatedBefore(int date, int month, int year) {
        LocalDate threshold;

        try {
            threshold = LocalDate.of(year, month, date);
        } catch (DateTimeException e) {
            return 0;
        }
        return count(b -> {
            if (b.getEmploymentEndDate().equals("XXXXXXXX"))
                return false;
            else {
                LocalDate d = LocalDate.parse(b.getEmploymentEndDate(), DateTimeFormatter.ofPattern("MMddyyyy"));
                return d.isBefore(threshold);
            }
        });
    }

    public void printEmployees() {
        root.print();
    }
}