public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        Command addCommand = new AddCommand(playlist);
        Command removeCommand = new RemoveCommand(playlist);
        Control control = new Control();

        Song theDevilInI = new Song("The devil in I", "Slipknot");
        Song enterSandman = new Song("Enter sandman", "Metallica");
        Song holyWars = new Song("Holy wars", "Megadeth");

        control.setCommand(addCommand);
        control.pressButton(theDevilInI);
        control.pressButton(enterSandman);
        control.pressButton(holyWars);

        System.out.println("\nPlaylist de base : ");
        System.out.println(playlist);

        System.out.println("\nAjoutons une chanson de Orelsan\n");

        Song orelsan = new Song("L'odeur de l'essence", "Orelsan");

        control.pressButton(orelsan);

        System.out.println(playlist);

        System.out.println("\nMaintenant retirons-la pour avoir une playlist 100% metal\n");

        control.setCommand(removeCommand);
        control.pressButton(orelsan);

        System.out.println(playlist);
    }
}
