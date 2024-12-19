import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) {

        // Exo 17

        ConcurrentHashMap<String,Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("ProduitA", 50);
        concurrentHashMap.put("ProduitB", 50);
        concurrentHashMap.put("ProduitC", 50);


        Runnable task = () -> {
            for (String key : concurrentHashMap.keySet()) {
                for (int i = 0; i < 3; i++) {
                    concurrentHashMap.compute(key, (k, oldValue) -> oldValue - 1);
                    System.out.println(Thread.currentThread().getName() + " a acheté 1 unité de " +key);
                }
                for (int i = 0; i < 2; i++) {
                    concurrentHashMap.compute(key, (k, oldValue) -> oldValue + 10);
                    System.out.println("Réapprovisionnement de 10 unités de " + key);
                }
            }
        };
        Thread thread1 = new Thread(task, "Acheteur 1");
        Thread thread2 = new Thread(task, "Acheteur 2");

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " a été interrompu");
            e.printStackTrace();
        }

        System.out.println("\nInventaire final : " + concurrentHashMap);

        // Exo 18

        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        Runnable task2 = () -> {
            for (int i = 0; i <= 9; i++) {
                copyOnWriteArrayList.add(Thread.currentThread().getName() + "-Produit-" + i);
            }
        };
        Thread thread3 = new Thread(task2);
        Thread thread4 = new Thread(task2);

        thread3.start();
        thread4.start();
        try {
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " a été interrompu");
            e.printStackTrace();
        }

        System.out.println("\nListe finale des produits : ");
        copyOnWriteArrayList.forEach(System.out::println);


        // Exo 19

        System.out.println("\nExo 19 : \n");
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

        Thread thread5 = new Thread(() -> {
            for (int i = 0; i <= 9; i++) {
                queue.add("Producer-Element-" + i);
                System.out.println("Producer a ajouté : Producer-Element-"+i);
            }
        });
        Thread thread6 = new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                if (queue.remove("Producer-Element-" + i)){
                    System.out.println("Consumer a retiré : Producer-Element-"+i);
                } else {
                    System.out.println("Consumer n'a trouvé aucun élément à retirer");
                }
            }
        });

        thread5.start();
        thread6.start();
        try {
            thread5.join();
            thread6.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " a été interrompu");
            e.printStackTrace();
        }

        System.out.println("\nEtat final de la file : " + queue);
    }
}
