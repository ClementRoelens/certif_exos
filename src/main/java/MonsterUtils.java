import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public abstract class MonsterUtils {

    public static Character getRandomMonster() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/monsters.txt"))) {
            Random random = new Random();
            String monsterLine = "";
            long limit = reader.lines().count();

            for (int i = 0; i < random.nextInt()*limit; i++) {
                monsterLine = reader.readLine();
            }

            return new Character(
                    monsterLine.split(",")[0],
                    Integer.parseInt(monsterLine.split(",")[1].trim()),
                    Integer.parseInt(monsterLine.split(",")[2].trim())
            );
        }
    }
}
