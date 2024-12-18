import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {

        // Exo 11

        BankAccount account = new BankAccount();
        System.out.println("Solde de base : " + account.getBalance());
        Random rand = new Random();
        Thread[] threads = createThreads(
                3,
                () -> {
                    for (int j = 0; j < 4; j++) {
                        final int amount = rand.nextInt(20, 50);
                        if (Math.random() > 0.5) {
                            try {
                                account.deposit(amount);
                                System.out.println(Thread.currentThread().getName() + " a déposé " + amount + ". Solde actuel : " + account.getBalance());
                            } catch (Exception e) {
                                System.out.println(Thread.currentThread().getName() + " n'a pas pu déposer " + amount + " : " + e.getMessage());
                            }
                        } else {
                            try {
                                account.withdraw(amount);
                                System.out.println(Thread.currentThread().getName() + " a retiré " + amount + ". Solde actuel : " + account.getBalance());
                            } catch (Exception e) {
                                System.out.println(Thread.currentThread().getName() + " n'a pas pu retirer " + amount + " : " + e.getMessage());
                            }
                        }
                    }
                });

        start(threads);
        join(threads);
        System.out.println("\nExo 11 : solde final : " + account.getBalance() + "\n");

        // Exo 12

        Thread addThread = new Thread(() -> {
            for (int i = 0; i < 4; i++) {
                SharedResource.add(i);
                System.out.println(Thread.currentThread().getName() + " a ajouté " + i);
            }
        });
        Thread removeThread = new Thread(() -> {
            for (int i = 0; i < 4; i++) {
                if (SharedResource.remove(i)) {
                    System.out.println(Thread.currentThread().getName() + " a supprimé " + i);
                } else {
                    System.out.println(Thread.currentThread().getName() + " n'a rien à supprimer");
                }
            }
        });

        addThread.start();
        removeThread.start();
        try {
            addThread.join();
            removeThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrompu");
            e.printStackTrace();
        }

        System.out.println("Exo 12 - état final de la liste : " + SharedResource.getSharedList() + "\n");

        // Exo 13

        Lock reentrantLock = new ReentrantLock();
        System.out.println("\nExo 13\n");

        Thread[] threads2 = createThreads(3,() -> {
            System.out.println(Thread.currentThread().getName() + " essaie d'utiliser l'imprimante");
            try {
                if (reentrantLock.tryLock(5, TimeUnit.MICROSECONDS)){
                    System.out.println(Thread.currentThread().getName() + " a l'autorisation d'utiliser l'imprimante");
                    Printer.print(Thread.currentThread().getName());
                    System.out.println(Thread.currentThread().getName() + " a imprimé son document et libère la place");
                    reentrantLock.unlock();
                } else {
                    System.out.println(Thread.currentThread().getName() + " ne peut utiliser l'imprimante (temps d'attente dépassé)");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        start(threads2);


    }

    private static Thread[] createThreads(int nThreads, Runnable task) {
        Thread[] threads = new Thread[nThreads];

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new Thread(task);
        }
        return threads;
    }

    private static void start(Thread[] threads){
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static void join(Thread[] threads){
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
