package patterns.composite;

// leaf
public class Developer implements Employee {

    private String name;
    private long empId;

    public Developer(long empId, String name) {
        this.empId = empId;
        this.name = name;
    }

    @Override
    public void showEmployeeDetails() {
        System.out.println(empId + " " + name);
    }
}
