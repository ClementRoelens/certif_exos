import java.util.Random;
import java.util.concurrent.*;

public class Exo22 {
    public static void main(String[] args) {
        Random random = new Random();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            CompletableFuture<Integer> step1 = new CompletableFuture<>();
            executor.submit(() -> step1.complete(random.nextInt(100)));
            CompletableFuture<Void> step2 = step1.thenRunAsync(() ->{
                try {
                    System.out.println("Le nombre généré est " + step1.get());
                    System.out.println(step1.get() % 2 == 0 ?
                            "Le nombre généré est pair" :
                            "Le nombre généré est impair");
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("Erreur : " + e.getMessage());
                }
            });
            step2.join();
        }
    }
}
