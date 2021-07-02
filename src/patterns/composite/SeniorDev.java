package patterns.composite;

// leaf
public class SeniorDev implements Employee {

    private String name;
    private long empId;

    public SeniorDev(long empId, String name) {
        this.empId = empId;
        this.name = name;
    }

    @Override
    public void showEmployeeDetails() {
        System.out.println(empId + " " + name );
    }
}
