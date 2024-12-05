package observer;


public class ChristmasImp implements Observer {

    @Override
    public void notifyInCrafting(String name) {
        System.out.println("Le lutin a lancé la fabrication de " + name);
    }

    @Override
    public void notifyCrafted(String name) {
        System.out.println("Le lutin a fini " + name);
    }
}
