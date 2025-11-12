import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {

    private static final List<Cart> extent=new ArrayList<>();
    public static List<Cart> getExtent(){
        return extent;
    }

    private static final String EXTENT_FILE = "cart_extent.ser";

    private List<Product> unavailableProducts=new ArrayList<>();

    public Cart() {
        extent.add(this);
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
            unavailableProducts.removeLast();
        }
    }

    public List<Product> getUnavailableProducts() {
        return unavailableProducts;
    }
}
