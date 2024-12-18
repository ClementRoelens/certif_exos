public class BankAccount {
    private double balance = 50;

    public synchronized void deposit(double amount) throws Exception {
        if (amount <= 0){
            throw new Exception("Le montant doit être positif");
        }
        balance += amount;
    }

    public synchronized void withdraw(double amount) throws Exception {
        if (balance - amount < 0){
            throw new Exception("Solde insuffisant");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
