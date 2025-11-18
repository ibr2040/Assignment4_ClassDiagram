import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
    private String image;
    private double price;
    private String title;
    private String category;
    private String description;
    private Boolean availability;
    private static double advertismentFee;

    private static final List<Product> extent=new ArrayList<>();
    private static final String EXTENT_FILE="product_extent.ser";

    public static List<Product> getExtent() {
        return extent;
    }

    public Product(String image, double price, String title, String category, String description, Boolean availability) {

        if (image==null || image.isBlank()){
            throw new IllegalArgumentException("Image cannot be empty");
        }
        if (title==null || title.isBlank()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (category==null || category.isBlank()){
            throw new IllegalArgumentException("Category cannot be empty");
        }
        if (price<0){
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if(price==0){
            throw new IllegalArgumentException("Price cannot be zero");
        }

        if (!image.endsWith(".png") && !image.endsWith(".jpg")){
            throw new IllegalArgumentException("Image must be PNG or JPG");
        }

        this.image = image;
        this.price = price;
        this.title = title;
        this.category = category;
        this.description = description;
        this.availability = availability;

        extent.add(this );
    }

    public static void setAdvertismentFee(double fee){
        if (fee<0){
            throw new IllegalArgumentException("Advertisement fee cannot be negative");
        }
        advertismentFee=fee;
    }

    public static void saveExtent() throws Exception{
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(EXTENT_FILE));
        oos.writeObject(extent);
        oos.close();
    }

    public static void loadExtent() throws Exception {
        File f=new File(EXTENT_FILE);
        if (!f.exists()){
            return;
        }

        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(EXTENT_FILE));
        List<Product> list=(List<Product>) ois.readObject();
        extent.clear();
        extent.addAll(list);
        ois.close();

    }


    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public static double getAdvertismentFee() {
        return advertismentFee;
    }
}
