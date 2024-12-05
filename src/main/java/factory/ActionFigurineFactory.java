package factory;

import model.ActionFigurine;
import model.Toy;
import observer.Observer;

import java.util.Timer;
import java.util.TimerTask;

public class ActionFigurineFactory extends ToyFactory {
    @Override
    public Toy createToy(String name) {
        for (Observer observer : observers) {
            observer.notifyInCrafting(name);
        }
        try {
            Thread.sleep(2000);
            for (Observer observer : observers) {
                observer.notifyCrafted(name);
            }
            return new ActionFigurine(name);
        } catch (InterruptedException e) {
            System.out.println("Le jouet n'a pas pu être créé");
            throw new RuntimeException(e);
        }
    }
}
