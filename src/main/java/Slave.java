public class Slave implements Employee{
    private final int number;

    public Slave(int number) {
        this.number = number;
    }
    @Override
    public void showDetails() {
        System.out.println("Je suis le matricule " + number);
    }
}
