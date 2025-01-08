import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventCollection {
    private final List<String> events;

    public EventCollection() throws IOException {
        events = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/events.txt"))) {
            reader.lines().forEach(events::add);
        }
    }

    public List<String> getEvents() {
        return events;
    }
}
