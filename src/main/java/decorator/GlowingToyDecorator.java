package decorator;

import model.Toy;

public class GlowingToyDecorator extends ToyDecorator {
    public GlowingToyDecorator(Toy toy) {
        super(toy);
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " (et je brille dans la nuit)";
    }

    @Override
    public String getName() {
        return super.getName() + " brillant(e)";
    }
}
