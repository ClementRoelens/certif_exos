import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String,String> dictionnary = new HashMap<>();
        dictionnary.put("hostname","localhost");
        dictionnary.put("port","8080");
        dictionnary.put("appname","MyApp");

        System.out.println("\nInitialisation de la configuration\n");
        ConfigurationManager config = ConfigurationManager.getInstance(dictionnary);
        System.out.println("Valeur de hostname : " + config.giveValue("hostname"));
        System.out.println("Valeur de port : " + config.giveValue("port"));
        System.out.println("Valeur de appname : " + config.giveValue("appname"));

        System.out.println("\nMaintenant, retentons avec un autre dictionnaire");

        HashMap<String,String> dictionnaryBis = new HashMap<>();
        dictionnaryBis.put("hostname","distanthost");
        dictionnaryBis.put("port","42");
        dictionnaryBis.put("appname","genhgernjigerg");

        System.out.println("Tentative d'initialisation de la seconde configuration\n");
        ConfigurationManager configBis = ConfigurationManager.getInstance(dictionnaryBis);
        System.out.println("Valeur de hostname : " + configBis.giveValue("hostname"));
        System.out.println("Valeur de port : " + configBis.giveValue("port"));
        System.out.println("Valeur de appname : " + configBis.giveValue("appname"));
    }
}
