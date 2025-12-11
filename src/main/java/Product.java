import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Product implements Serializable {
    private static final List<String> allowedCategories= Arrays.asList("Phones", "Electronics", "Kids", "Computers", "Toys");

    private String image;
    private double price;
    private String title;
    private String category;
    private String description;
    private Boolean availability;

    private static double advertismentFee;
    private static final List<Product> extent=new ArrayList<>();
    private static final String EXTENT_FILE="product_extent.ser";

    private HashSet<ProductsQuantityCart> productQuantityCartList = new HashSet<>();

    private Campaign campaign;

    private Merchant merchant;

    private boolean isCompositionProduct;

    public Product(String image, double price, String title, String category, Boolean availability,Merchant merchant) {
        if (merchant == null) {
            throw new IllegalArgumentException("Merchant cannot be null for composition constructor");
        }

        if (image == null || image.isBlank()) {
            throw new IllegalArgumentException("Image cannot be empty");
        }

        if (!image.matches("/images/[A-Za-z0-9_]+\\.(png|jpg)")) {
            throw new IllegalArgumentException("Image must be in /images/... path and PNG/JPG");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        if (title.length() < 3) {
            throw new IllegalArgumentException("Title must have at least 3 characters");
        }

        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }

        if (!allowedCategories.contains(category)) {
            throw new IllegalArgumentException("Category not allowed: " + category);
        }

        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if (price == 0) {
            throw new IllegalArgumentException("Price cannot be zero");
        }

        if (price > 100_000) {
            throw new IllegalArgumentException("Price exceeds allowed maximum");
        }

        if (availability == null) {
            throw new IllegalArgumentException("Availability cannot be null");
        }
        this.isCompositionProduct = true;
        this.image = image;
        this.price = price;
        this.title = title;
        this.category = category;
        this.availability = availability;

        extent.add(this );
    }

    public Product(String image, double price, String title, String category, String description, Boolean availability) {

        if (image == null || image.isBlank()) {
            throw new IllegalArgumentException("Image cannot be empty");
        }

        if (!image.matches("/images/[A-Za-z0-9_]+\\.(png|jpg)")) {
            throw new IllegalArgumentException("Image must be in /images/... path and PNG/JPG");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        if (title.length() < 3) {
            throw new IllegalArgumentException("Title must have at least 3 characters");
        }

        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }

        if (!allowedCategories.contains(category)) {
            throw new IllegalArgumentException("Category not allowed: " + category);
        }

        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if (price == 0) {
            throw new IllegalArgumentException("Price cannot be zero");
        }

        if (price > 100_000) {
            throw new IllegalArgumentException("Price exceeds allowed maximum");
        }

        if (description != null && description.length() > 300) {
            throw new IllegalArgumentException("Description too long (max 300 chars)");
        }

        if (availability == null) {
            throw new IllegalArgumentException("Availability cannot be null");
        }
        this.isCompositionProduct=false;
        this.image = image;
        this.price = price;
        this.title = title;
        this.category = category;
        this.description = description;
        this.availability = availability;

        extent.add(this);
    }
    public void internalDestroy(){
        this.merchant=null;
    }
    public Campaign getCampaign() {
        return campaign;
    }
    public void removeCampaign(){
        this.campaign=null;
    }
    public boolean isCompositionProduct() {
        return isCompositionProduct;
    }
    public void addToCart(ProductsQuantityCart cart){
        if (productQuantityCartList.contains(cart)||cart==null){return;}
        productQuantityCartList.add(cart);
    }
    public void removeFromCart(ProductsQuantityCart cart){
        if(!productQuantityCartList.contains(cart)||cart==null){return;}
        productQuantityCartList.remove(cart);
        cart.remove();
    }

    public void setCampaign(Campaign campaign) {
        if (this.campaign != null && campaign != null) {
            throw new IllegalStateException("Product already assigned to a campaign");
        }
        this.campaign = campaign;
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
    public void setMerchant(Merchant merchant) {
        if (isCompositionProduct) {
            throw new UnsupportedOperationException(
                    "This product is already assigned to a merchant and cannot be reassigned."
            );
        }
        this.merchant = merchant;
    }

    public void removeMerchant() {
        if (isCompositionProduct) {
            throw new UnsupportedOperationException(
                    "This product must always remain linked to its merchant and cannot be detached.");
        }
        this.merchant = null;
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

    public static List<Product> getExtent() {
        return extent;
    }

    public static double getAdvertismentFee() {
        return advertismentFee;
    }

    public Merchant getMerchant(){
        return merchant;
    }

    public HashSet<ProductsQuantityCart> getCartsContainingProduct() {
        return this.productQuantityCartList;
    }
}
