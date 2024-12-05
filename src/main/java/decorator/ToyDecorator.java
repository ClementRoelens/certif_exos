package decorator;

import model.Toy;

public abstract class ToyDecorator implements Toy {
    protected Toy toy;

    public ToyDecorator(Toy toy) {
        this.toy = toy;
    }

    @Override
    public String getDetails() {
        return toy.getDetails();
    }

    @Override
    public String getName() {
        return toy.getName();
    }
}
