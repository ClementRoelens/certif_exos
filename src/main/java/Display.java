public class Display implements Observer {
    @Override
    public void receiveMessage(String message) {
        System.out.println("Le message est \"" + message +"\"");
    }
}
