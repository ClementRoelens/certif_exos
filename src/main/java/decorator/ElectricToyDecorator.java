package decorator;

import model.Toy;

public class ElectricToyDecorator extends ToyDecorator {
    public ElectricToyDecorator(Toy toy) {
        super(toy);
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " (et j'ai besoin de piles)";
    }

    @Override
    public String getName() {
        return super.getName() + " électrique";
    }
}
