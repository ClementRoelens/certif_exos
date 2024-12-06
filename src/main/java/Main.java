public class Main {
    public static void main(String[] args) {
        Employee slave = new Slave(123);
        Employee slaveTwo = new Slave(456);
        Employee slaveThree = new Slave(8764);
        
        Manager manager = new Manager("Christophe");
        Manager managerTwo = new Manager("Ihab");
        
        manager.addEmployee(slave);
        manager.addEmployee(slaveTwo);
        managerTwo.addEmployee(manager);
        managerTwo.addEmployee(slaveThree);

        managerTwo.showDetails();
    }
}
