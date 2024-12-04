import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConfigurationManager {
    private final Map<String,String> config;
    private static ConfigurationManager instance;

    private ConfigurationManager(Map<String,String> config) {
        this.config = config;
    }

    public static synchronized ConfigurationManager getInstance(File file) {
        if (instance == null) {
            try {
                instance = new ConfigurationManager(readConfig(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public String giveValue(String key){
        return config.get(key);
    }

    private static Map<String,String> readConfig(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        HashMap<String,String> config = new HashMap<>();

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String key = line.split("=")[0];
            String value = line.split("=")[1];
            config.put(key, value);
        }

        return config;
    }

}
