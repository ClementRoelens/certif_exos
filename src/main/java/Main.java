import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        // Exo 4

        System.out.println("\nExo 4\n");
        try (ExecutorService executor = Executors.newFixedThreadPool(3)) {
            for (int i = 1; i <= 10; i++) {
                int finalI = i;
                executor.execute(() -> System.out.println("Thread " + finalI + " exécuté par " + Thread.currentThread().getName()));
            }
        }

        // Exo 5

        System.out.println("\nExo 5\n");
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            for (int i = 1; i <= 10; i++) {
                int finalI = i;
                Callable<Integer> callable = () -> finalI * finalI;
                Future<Integer> future = executor.submit(callable);
                System.out.println("Résultat de la tâche " + i + " : " + future.get());
            }
        } catch (ExecutionException e) {
            System.out.println("Problème d'exécutio");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Thread interrompu");
            e.printStackTrace();
        }

        // Exo 6

        System.out.println("\nExo 6\n");
        try (ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor()) {
            for (int i = 1; i <= 3; i++) {
                int finalI = i;
                executor.schedule(
                        () -> System.out.println("Tâche " + finalI + " exécutée après " + finalI + " seconde(s)"),
                        finalI,
                        TimeUnit.SECONDS
                );
            }
        }
        System.out.println("\nToutes les tâches ont été exécutées. Arrêt du programme");
    }
}
