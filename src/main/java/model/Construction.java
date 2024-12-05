package model;

public class Construction extends AbstractToy implements Toy{
    public Construction(String name) {
        super(name);
    }

    @Override
    public String getDetails() {
        return "Je suis un jouet de construction";
    }

    @Override
    public String getName() {
        return name;
    }
}
