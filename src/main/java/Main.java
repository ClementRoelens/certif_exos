import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {

        // Exo 7

        AtomicInteger counter1 = new AtomicInteger(0);

        try (ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor()) {
            executor.scheduleAtFixedRate(
                    () -> {
                        System.out.println("Message périodique " + counter1.incrementAndGet());
                        if (counter1.get() == 5){
                            executor.shutdown();
                        }
                    },
                    1,
                    2,
                    TimeUnit.SECONDS);
            executor.awaitTermination(9,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Thread interrompu");
            e.printStackTrace();
        }

        System.out.println("\nExo 7 - programme terminé");


        // Exo 8

        AtomicInteger counter2 = new AtomicInteger(0);
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter2.incrementAndGet();
                }
            });
        }

        startAndJoin(threads);
        System.out.println("\nExo 8 - valeur finale du compteur : " + counter2.get());


        // Exo 9

        AtomicInteger counter3 = new AtomicInteger(0);
        Thread[] threads2 = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads2[i] = new Thread(() -> {
                if (counter3.get() < 10) {
                    counter3.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + " a incrémenté le compteur à " + counter3.get());
                }
            });
        }

        startAndJoin(threads2);
        System.out.println("\nExo 9 - valeur finale du compteur : " + counter3.get());


        // Exo 10

        AtomicLong counter4 = new AtomicLong(1);
        Thread[] threads3 = new Thread[5];

        for (int i = 0; i < 5; i++) {
            final int finalI = i+1;
            threads3[i] = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " : " +  counter4.getAndSet(counter4.get() * finalI) + " x " + finalI + " = " + counter4.get());
            });
        }

        startAndJoin(threads3);
        System.out.println("\nExo 10 - valeur finale : " + counter4.get());
    }

    private static void startAndJoin(Thread[] threads){
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
