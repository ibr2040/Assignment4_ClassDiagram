import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Merchant extends User {
    private final List<Product> products=new ArrayList<>();
    private String bankAccountNumber;

    public Merchant(
            String fullName,
            String email,
            String mobileNumber,
            String login,
            String password,
            String bankAccountNumber,
            String street,
            String city,
            String state
    ) {
        super(fullName, email, mobileNumber, login, password, street, city, state);

        if (bankAccountNumber == null || bankAccountNumber.isBlank()) {
            throw new IllegalArgumentException("Bank account number cannot be empty");
        }
        if (bankAccountNumber.length()<5){
            throw new IllegalArgumentException("Bank account number length should be 5 digits");
        }
        if (!bankAccountNumber.startsWith("PL")){
            throw new IllegalArgumentException("Bank account number must start with PL");
        }

        this.bankAccountNumber = bankAccountNumber;
    }
    public Product createProduct(String image,double price,String title,String category,boolean availability){
        Product p=new Product(image,price,title,category,availability,this);
        products.add(p);
        return p;
    }

    public void addExistingProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Cannot add null product");
        }

        if (products.contains(product)) {
            throw new IllegalStateException("Product already belongs to this merchant");
        }

        if (product.getMerchant() != null) {
            throw new IllegalStateException("Product already belongs to another merchant");
        }

        if (product.isCompositionProduct()) {
            throw new IllegalStateException("This product cannot be removed individually because it is permanently linked to this merchant."
            );
        }

        product.setMerchant(this);
        products.add(product);
    }

    public void removeProduct (Product product){
        if (product == null) {
            throw new IllegalArgumentException("Cannot remove null product");
        }

        if (!products.contains(product)) {
            throw new IllegalArgumentException("This product does not belong to this merchant");
        }

        if (product.isCompositionProduct()) {
            throw new UnsupportedOperationException(
                    "Cannot remove a composition product individually."
            );
        }

        product.removeMerchant();
        products.remove(product);
    }

    public void deleteMerchant(){
        for(Product p:new ArrayList<>(products)){
            p.internalDestroy();
        }
        products.clear();
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void viewInventory() {
        System.out.println("Merchant view inventory");
    }

    public void editProductInformation() {
        System.out.println("Merchant edit product information");
    }

    public void addProduct() {
        System.out.println("Merchant add product");
    }
}
