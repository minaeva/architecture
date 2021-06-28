package patterns.composite;

public class Main {

    public static void main(String[] args) {
        Developer dev1 = new Developer(101, "First");
        Developer dev2 = new Developer(102, "Second");
        Developer dev3 = new Developer(103, "Third");
        SeniorDev dev4 = new SeniorDev(104, "Fourth");
        SeniorDev dev5 = new SeniorDev(105, "Fifth");
        SeniorDev dev6 = new SeniorDev(106, "Sixth");

        Manager managerPeter = new Manager("Peter");
        managerPeter.addEmployee(dev1);
        managerPeter.addEmployee(dev2);
        managerPeter.addEmployee(dev3);

        Manager managerJohn = new Manager("John");
        managerJohn.addEmployee(dev5);
        managerJohn.addEmployee(dev4);

        Manager ceo = new Manager("ceo");
        ceo.addEmployee(managerJohn);
        ceo.addEmployee(managerPeter);
        ceo.addEmployee(dev6);


        ceo.showEmployeeDetails();
    }
}
