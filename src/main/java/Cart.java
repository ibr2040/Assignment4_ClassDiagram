import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Cart implements Serializable {

    private static final List<Cart> extent=new ArrayList<>();

    private static final String EXTENT_FILE = "cart_extent.ser";

    private List<Product> unavailableProducts=new ArrayList<>();

    private HashSet<ProductsQuantityCart> productsQuantityCartList=new HashSet<>();


    public static List<Cart> getExtent(){
        return extent;
    }

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
        return productsQuantityCartList.size();
    }

    public void addProduct(ProductsQuantityCart p){
        if (p==null){
            throw new IllegalArgumentException("Product cannot be null");
        }

        if(this.productsQuantityCartList.contains(p)||p==null){return;}
        this.productsQuantityCartList.add(p);
    }
    public void removeProduct(ProductsQuantityCart p){
        if (p==null||!this.productsQuantityCartList.contains(p)){return;}
        this.productsQuantityCartList.remove(p);
        p.remove();
    }

    public void clear(){
        for(ProductsQuantityCart p:this.productsQuantityCartList){
            this.removeProduct(p);
        }
    }

    public HashSet<ProductsQuantityCart> getProductsInTheCart() {
        return this.productsQuantityCartList;
    }

    public List<Product> getUnavailableProducts() {
        return unavailableProducts;
    }
}
