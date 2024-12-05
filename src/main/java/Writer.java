
import java.io.FileWriter;
import java.io.IOException;

public class Writer implements Observer {
    @Override
    public void receiveMessage(String message) {
        try (FileWriter fileWriter = new FileWriter("src/main/resources/log.txt", true)){
            fileWriter.write(message+"\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
