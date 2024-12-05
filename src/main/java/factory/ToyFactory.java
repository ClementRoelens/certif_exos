package factory;

import model.Toy;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class ToyFactory  {
      protected List<Observer> observers = new ArrayList<>();
      public abstract Toy createToy(String name);

      public void addObserver(Observer observer) {
            observers.add(observer);
      }

      public void removeObserver(Observer observer) {
            observers.remove(observer);
      }
}
