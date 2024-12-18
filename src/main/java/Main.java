import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        // Exo 14

        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("\nTous les fichiers ont été chargés\n"));

        Thread firstThread = new Thread(() -> {
            System.out.println("Chargement du premier fichier");
            try {
                readFile("src/main/resources/file_one.txt");
                System.out.println("Premier fichier chargé");
                barrier.await();
                System.out.println("Envoi des données du premier fichier");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrompu");
                e.printStackTrace();
            } catch (BrokenBarrierException e){
                System.out.println("Problème avec CyclicBarrier");
                e.printStackTrace();
            }
        });
        Thread secondThread = new Thread(() -> {
            System.out.println("Chargement du deuxième fichier");
            try {
                readFile("src/main/resources/file_two.txt");
                System.out.println("Deuxième fichier chargé");
                barrier.await();
                System.out.println("Envoi des données du deuxième fichier");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrompu");
                e.printStackTrace();
            } catch (BrokenBarrierException e){
                System.out.println("Problème avec CyclicBarrier");
                e.printStackTrace();
            }
        });
        Thread thirdFileThread = new Thread(() -> {
            System.out.println("Chargement du troisième fichier");
            try {
                readFile("src/main/resources/file_three.txt");
                System.out.println("Troisième fichier chargé");
                barrier.await();
                System.out.println("Envoi des données du troisième fichier");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrompu");
                e.printStackTrace();
            } catch (BrokenBarrierException e){
                System.out.println("Problème avec CyclicBarrier");
                e.printStackTrace();
            }
        });

        firstThread.start();
        secondThread.start();
        thirdFileThread.start();
        try {
            firstThread.join();
            secondThread.join();
            thirdFileThread.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrompu");
            e.printStackTrace();
        }


        // Exo 15

        System.out.println("\nExo 15\n");

        CyclicBarrier secondBarrier = new CyclicBarrier(2);
        firstThread = new Thread(() -> {
            try {
                int i = 1;
                Thread.sleep(150);
                System.out.println(Thread.currentThread().getName() + " atteint l'étape " + i++);
                secondBarrier.await();
                Thread.sleep(250);
                System.out.println(Thread.currentThread().getName() + " atteint l'étape " + i++);
                secondBarrier.await();
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + " atteint l'étape " + i);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrompu");
                e.printStackTrace();
            } catch (BrokenBarrierException e){
                System.out.println("Problème avec CyclicBarrier");
                e.printStackTrace();
            }
        });
        secondThread = new Thread(() -> {
            try {
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + " atteint l'étape 1");
                secondBarrier.await();
                Thread.sleep(180);
                System.out.println(Thread.currentThread().getName() + " atteint l'étape 2");
                secondBarrier.await();
                Thread.sleep(300);
                System.out.println(Thread.currentThread().getName() + " atteint l'étape 3");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrompu");
                e.printStackTrace();
            } catch (BrokenBarrierException e){
                System.out.println("Problème avec CyclicBarrier");
                e.printStackTrace();
            }
        });

        firstThread.start();
        secondThread.start();
        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrompu");
            e.printStackTrace();
        }


        // Exo 16

        System.out.println("\nExo 16\n");
        int[] numberArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        int[] partialSum = new int[4];
        Thread[] threads = new Thread[4];
        CyclicBarrier thirdBarrier = new CyclicBarrier(4, () -> {
            System.out.println("Les sommes partielles ont toutes été calculées");
            System.out.print("La somme totale est ");
            IntStream stream = IntStream.of(numberArray);
            System.out.println(stream.sum());
        });

        for (int i = 0; i < 4; i++) {
            final int finalI = i;
            List<Integer> numbersSummed = new ArrayList<>();
            threads[i] = new Thread(() -> {
                for (int j = finalI; j < numberArray.length ; j += 4){
                    partialSum[finalI] += numberArray[j];
                    numbersSummed.add(numberArray[j]);
                }
                System.out.println(Thread.currentThread().getName() + " a calculé la somme de " + numbersSummed + "\nRésultat : " + partialSum[finalI]);
                try {
                    thirdBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrompu");
                e.printStackTrace();
            }
        }
    }

    private static void readFile(String path){
        try (BufferedReader reader = new BufferedReader(new FileReader(path));
                BufferedWriter writer = new BufferedWriter(new FileWriter(path.split(".txt")[0].concat("_in_caps_lock.txt")))) {
            reader.lines().forEach(l -> {
                try {
                    writer.write(l.toUpperCase());
                    writer.newLine();
                } catch (IOException e) {
                    System.out.println("Erreur d'écriture");
                }
            });
            writer.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
