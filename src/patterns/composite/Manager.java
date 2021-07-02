package patterns.composite;

import java.util.ArrayList;
import java.util.List;

// composite
public class Manager implements Employee {

    private List<Employee> employeeList = new ArrayList<>();
    private String name;

    public Manager(String name) {
        this.name = name;
    }

    @Override
    public void showEmployeeDetails() {
        System.out.println("start of manager " + this.name + " with " + employeeList.size() +
                " employees: ");
        for (Employee emp : employeeList) {
            emp.showEmployeeDetails();
        }
        System.out.println("end of manager " + this.name);
    }

    public void addEmployee(Employee emp) {
        employeeList.add(emp);
    }

    public void removeEmployee(Employee emp) {
        employeeList.remove(emp);
    }

}
