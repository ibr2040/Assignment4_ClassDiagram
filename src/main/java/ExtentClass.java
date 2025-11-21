import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExtentClass {
    private static final String FILE="all_extent.ser";

    private static final List<Object> allObjects=new ArrayList<>();

    public static void register(Object o) {
        allObjects.add(o);
    }

    public static List<Object> getAllObjects() {
        return allObjects;
    }

    public static void saveAll() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE));
        oos.writeObject(allObjects);
        oos.close();
    }

    public static void loadAll() throws Exception {
        File f = new File(FILE);
        if (!f.exists()) return;

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE));
        List<Object> loaded = (List<Object>) ois.readObject();
        allObjects.clear();
        allObjects.addAll(loaded);
        ois.close();
    }
}
