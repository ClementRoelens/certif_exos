import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {

        // Exo 1

        MyFirstThread threadOne = new MyFirstThread();
        MyFirstThread threadTwo = new MyFirstThread();
        MyFirstThread threadThree = new MyFirstThread();

        threadOne.start();
        threadTwo.start();
        threadThree.start();

        // Exo 2

        Thread squareOne = new Thread(new MyRunnableTask(3));
        Thread squareTwo = new Thread(new MyRunnableTask(5));
        Thread squareThree = new Thread(new MyRunnableTask(4));
        Thread squareFour = new Thread(new MyRunnableTask(1));
        Thread squareFive = new Thread(new MyRunnableTask(2));

        squareOne.start();
        squareTwo.start();
        squareThree.start();
        squareFour.start();
        squareFive.start();

        // Exo 3

        try (ExecutorService executorService = Executors.newSingleThreadExecutor()){
            Callable<Integer> task;
            Future<Integer> future;

            task = new CallableCalculator(1);
            future = executorService.submit(task);
            System.out.println("Le cube de 1 est " +future.get());

            task = new CallableCalculator(2);
            future = executorService.submit(task);
            System.out.println("Le cube de 2 est " +future.get());

            task = new CallableCalculator(3);
            future = executorService.submit(task);
            System.out.println("Le cube de 3 est " +future.get());

            task = new CallableCalculator(4);
            future = executorService.submit(task);
            System.out.println("Le cube de 4 est " +future.get());

            task = new CallableCalculator(5);
            future = executorService.submit(task);
            System.out.println("Le cube de 5 est " +future.get());
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
