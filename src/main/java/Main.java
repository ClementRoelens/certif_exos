import java.io.File;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        System.out.println("\nInitialisation de la configuration\n");
        ConfigurationManager config = ConfigurationManager.getInstance(new File("C:\\Users\\Administrateur\\Documents\\Exos\\singleton-exo\\src\\main\\java\\application.properties"));
        System.out.println("Valeur de hostname : " + config.giveValue("hostname"));
        System.out.println("Valeur de port : " + config.giveValue("port"));
        System.out.println("Valeur de appname : " + config.giveValue("appname"));

        System.out.println("\nMaintenant, retentons avec un autre dictionnaire");

        System.out.println("Tentative d'initialisation de la seconde configuration\n");
        ConfigurationManager configBis = ConfigurationManager.getInstance(new File("C:\\Users\\Administrateur\\Documents\\Exos\\singleton-exo\\src\\main\\java\\application_bis.properties"));
        System.out.println("Valeur de hostname : " + configBis.giveValue("hostname"));
        System.out.println("Valeur de port : " + configBis.giveValue("port"));
        System.out.println("Valeur de appname : " + configBis.giveValue("appname"));
    }
}
