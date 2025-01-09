import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public abstract class MonsterUtils {

    public static Character getRandomMonster() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/monsters.txt"))) {
            Random random = new Random();
            List<String> monsters = reader.lines().toList();
            String monster = monsters.get(random.nextInt(0, monsters.size()));

            return new Character(
                    monster.split(",")[0],
                    Integer.parseInt(monster.split(",")[1].trim()),
                    Integer.parseInt(monster.split(",")[2].trim())
            );
        }
    }
}
