import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {

    private static final List<Cart> extent=new ArrayList<>();

    private static final String EXTENT_FILE = "cart_extent.ser";

    private List<Product> unavailableProducts=new ArrayList<>();


    public static List<Cart> getExtent(){
        return extent;
    }

    public Cart() {
        extent.add(this);
        ExtentClass.register(this);
    }

    public static void saveExtent() throws IOException {
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(EXTENT_FILE));
        oos.writeObject(extent);
        oos.close();
    }

    public static void loadExtent() throws IOException, ClassNotFoundException {
        File f=new File(EXTENT_FILE);
        if (!f.exists()){
            return;
        }
        ObjectInputStream ios=new ObjectInputStream(new FileInputStream(EXTENT_FILE));
        List<Cart> list=(List<Cart>) ios.readObject();
        extent.clear();
        extent.addAll(list);
        ios.close();
    }

    public int getQuantityOfProducts(){
        return unavailableProducts.size();
    }

    public void addProduct(Product p){
        if (p==null){
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (p.getAvailability())
            throw new IllegalArgumentException("Cannot add AVAILABLE product to unavailableProducts");

        if (unavailableProducts.contains(p))
            throw new IllegalArgumentException("Product already in cart");

        unavailableProducts.add(p);
    }

    public void clear(){
        unavailableProducts.clear();
    }

    public void changeQuantity(int newQuantity){
        if (newQuantity<0){
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        while(unavailableProducts.size()>newQuantity){
            unavailableProducts.remove(unavailableProducts.size()-1);
        }
    }

    public List<Product> getUnavailableProducts() {
        return unavailableProducts;
    }
}
