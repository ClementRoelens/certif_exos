import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class Exo20 {
    public static void main(String[] args) {
        final String ALPHABET = "abcdefghijklmnopqrstuvwxyz ";
        final String TARGET = "merde";
        ThreadLocalRandom random = ThreadLocalRandom.current();
        AtomicLong count = new AtomicLong();
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        DecimalFormat formatter = new DecimalFormat("#,###");
        AtomicBoolean isFound = new AtomicBoolean(false);
        AtomicLong start = new AtomicLong();
        AtomicLong end = new AtomicLong();


        Runnable smartMonkey =  () -> {
            try {
                final AtomicReference<String> generatedWord = new AtomicReference<>("");
                while (!isFound.get()){
                    generatedWord.set("");
                    for (int i = 0; i < TARGET.length(); i++) {
                        generatedWord.getAndUpdate(word -> word + ALPHABET.charAt(random.nextInt(ALPHABET.length())));
                    }
                    if (!generatedWord.get().equals(TARGET)){
                        final long finalCount = count.getAndIncrement();
                        if (finalCount % 10_000_000 == 0){
                            if (!isFound.get()){
                            System.out.println("Déjà " + formatter.format(finalCount) + " essais !");
                            }
                        }
                    } else {
                        isFound.set(true);
                        end.set(System.currentTimeMillis());
                        executor.shutdown();
                        System.out.println("Bravo, le singe a généré la bonne phrase !\n" +
                                "Il aura fallu " + formatter.format(count.get()) + " essais et " + formatter.format(end.get() - start.get()) + "ms");
                    }

                }
            } catch (Throwable cause){
                System.out.println("Problème lors de la recherche");
                System.out.println(cause.getMessage());
            }
        };

        start.set(System.currentTimeMillis());
        for (int i = 0; i < 1000; i++) {
            if (!isFound.get() && !executor.isShutdown()){
                try {
                    executor.submit(smartMonkey);
                } catch (Throwable cause){
                    System.out.println(cause.getMessage());
                }
            }
        }

        try {
            executor.awaitTermination(2,TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
