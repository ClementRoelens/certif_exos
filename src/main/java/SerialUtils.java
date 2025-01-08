import java.io.*;

public class SerialUtils {
    private static int serialNo = 1;

    public static void serialize(String path, Serializable serializedObject) throws IOException {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path))){
            stream.writeObject(serializedObject);
            serialNo++;
        }
    }

    public static Serializable deserialize(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(path))){
            return (Serializable) stream.readObject();
        }
    }

    public static int getSerialNo() {
        return serialNo;
    }
}
