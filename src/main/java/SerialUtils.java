import java.io.*;

public abstract class SerialUtils {

    public static void serialize(String path, Serializable serializedObject) throws IOException {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path))){
            stream.writeObject(serializedObject);
        }
    }

    public static Serializable deserialize(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(path))){
            return (Serializable) stream.readObject();
        }
    }
}
