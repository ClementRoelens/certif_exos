package model;

public class ActionFigurine extends AbstractToy implements Toy{
    public ActionFigurine(String name) {
        super(name);
    }

    @Override
    public String getDetails() {
        return "Je suis une figurine d'action";
    }

    @Override
    public String getName() {
        return name;
    }
}
