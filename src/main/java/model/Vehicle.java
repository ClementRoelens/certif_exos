package model;

public class Vehicle extends AbstractToy implements Toy{
    public Vehicle(String name) {
        super(name);
    }

    @Override
    public String getDetails() {
        return "Je suis un véhicule";
    }

    @Override
    public String getName(){
        return name;
    }
}
