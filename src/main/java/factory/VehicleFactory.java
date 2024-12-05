package factory;

import model.Construction;
import model.Toy;
import model.Vehicle;
import observer.Observer;

public class VehicleFactory extends ToyFactory{
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
            return new Vehicle(name);
        } catch (InterruptedException e) {
            System.out.println("Le jouet n'a pas pu être créé");
            throw new RuntimeException(e);
        }
    }
}
