import java.util.ArrayList;
import java.util.List;

public class Manager implements Employee {
    private final List<Employee> employees;
    private String name;

    public Manager(String name) {
        employees = new ArrayList<>();
        this.name = name;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public void showDetails() {
        System.out.println("Je suis " + name + ". Sous mes ordres : ");
        for (Employee employee : employees) {
            System.out.print("-");
            employee.showDetails();
        }
    }
}
