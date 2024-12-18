import java.util.ArrayList;
import java.util.List;

public class SharedResource {
    private static List<Integer> sharedList = new ArrayList<>();
    private static HardLock lock = new HardLock();

    public static void add(Integer value){
        synchronized(lock){
            sharedList.add(value);
        }
    }

    public static boolean remove(Integer value){
        synchronized(lock){
            return sharedList.remove(value);
        }
    }

    public static List<Integer> getSharedList() {
        return sharedList;
    }
}
