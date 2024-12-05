import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventManager {
    private final Set<Observer> observers = new HashSet<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void sendMessage(String message) {
        for (Observer observer : observers) {
            observer.receiveMessage(message);
        }
    }
}
