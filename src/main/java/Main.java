import decorator.EducativeToyDecorator;
import decorator.ElectricToyDecorator;
import decorator.GlowingToyDecorator;
import decorator.ToyDecorator;
import factory.ActionFigurineFactory;
import factory.ConstructionFactory;
import factory.ToyFactory;
import factory.VehicleFactory;
import model.Toy;
import observer.ChristmasImp;

public class Main {
    public static void main(String[] args) {
        ChristmasImp imp = new ChristmasImp();

        ToyFactory vehicleFactory = new VehicleFactory();
        vehicleFactory.addObserver(imp);
        ToyFactory actionFigurineFactory = new ActionFigurineFactory();
        actionFigurineFactory.addObserver(imp);
        ToyFactory constructionFactory = new ConstructionFactory();
        constructionFactory.addObserver(imp);

        Toy vehicle = vehicleFactory.createToy("Voiture de course");
        Toy actionFigurine = actionFigurineFactory.createToy("Power Rangers");
        Toy construction = constructionFactory.createToy("K-nex");

        System.out.println();
        System.out.println(vehicle.getDetails() + " nommé " + vehicle.getName());
        System.out.println(actionFigurine.getDetails() + " nommée " + actionFigurine.getName());
        System.out.println(construction.getDetails() + " nommé " + construction.getName());

        System.out.println("\nMaintenant, pimpons nos jouets");

        Toy electricVehicle = new ElectricToyDecorator(vehicle);
        Toy glowingActionFigurine = new GlowingToyDecorator(actionFigurine);
        Toy educativeConstruction = new EducativeToyDecorator(construction);

        System.out.println();
        System.out.println(electricVehicle.getDetails() + " nommé " + electricVehicle.getName());
        System.out.println(glowingActionFigurine.getDetails() + " nommée " + glowingActionFigurine.getName());
        System.out.println(educativeConstruction.getDetails() + " nommé " + educativeConstruction.getName());
    }
}
