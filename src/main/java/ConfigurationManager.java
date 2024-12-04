import java.util.Map;

public class ConfigurationManager {
//    private String hostname;
    // Pour simplifier j'ai mis un String pour le port
//    private String port;
//    private String appname;
    private Map<String,String> config;
    private static ConfigurationManager instance;

    private ConfigurationManager(Map<String,String> config) {
//        this.hostname = hostname;
//        this.port = port;
//        this.appname = appname;
        this.config = config;
    }

    public static synchronized ConfigurationManager getInstance(Map<String,String> config) {
        if (instance == null) {
            instance = new ConfigurationManager(config);
        }
        return instance;
    }

//    @Override
//    public String toString() {
//        return String.format("La configuration est : \n" +
//                "hostname : %s\n" +
//                "port : %s\n" +
//                "appname : %s",
//                hostname, port, appname);
//    }

    public String giveValue(String key){
        return config.get(key);
    }
}
