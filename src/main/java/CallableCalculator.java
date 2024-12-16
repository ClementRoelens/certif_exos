import java.util.concurrent.Callable;

public class CallableCalculator implements Callable {
    private final int number;

    public CallableCalculator(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        return number*number*number;
    }
}
