import java.util.Map;

public class ConfigurationManager {
    private String hostname;
    // Pour simplifier j'ai mis un String pour le port
    private String port;
    private String appname;
    private static ConfigurationManager instance;

    private ConfigurationManager(String hostname, String port, String appname) {
        this.hostname = hostname;
        this.port = port;
        this.appname = appname;
    }

    public static synchronized ConfigurationManager getInstance(Map<String,String> dictionnary) {
        if (instance == null) {
            instance = new ConfigurationManager(dictionnary.get("hostname"), dictionnary.get("port"), dictionnary.get("appname"));
        }
        return instance;
    }

    @Override
    public String toString() {
        return String.format("La configuration est : \n" +
                "hostname : %s\n" +
                "port : %s\n" +
                "appname : %s",
                hostname, port, appname);
    }
}
