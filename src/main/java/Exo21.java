import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Exo21 {
    static ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            connect();
        }
        executor.awaitTermination(5,TimeUnit.SECONDS);
        executor.shutdown();
    }

    private static void connect(){
        executor.execute(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("okay");
            } catch (InterruptedException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        });
    }
}
