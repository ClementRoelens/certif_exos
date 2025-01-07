import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ActivitiesProvider {
    public static String[] getActivities() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("activitieExamples.txt"))) {
            return reader.lines().toArray(String[]::new);
        }
    }
}
