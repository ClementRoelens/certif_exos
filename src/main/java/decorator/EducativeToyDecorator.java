package decorator;

import model.Toy;

public class EducativeToyDecorator extends ToyDecorator {
    public EducativeToyDecorator(Toy toy) {
        super(toy);
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " (et je suis éducatif)";
    }

    @Override
    public String getName() {
        return super.getName() + " éducatif/ve";
    }
}
