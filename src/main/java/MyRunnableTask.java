public class MyRunnableTask implements Runnable {
    private final int number;

    public MyRunnableTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Le carré de " + number + " est " + number*number);
    }
}
