import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Merchant extends User {
    private List<Product> products=new ArrayList<>();
    private String bankAccountNumber;;
    private MarketModerator suprevisor;

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
        List<Product> copy = new ArrayList<>(products);

        products.clear();

        for (Product p : copy) {
            p.removeMerchant();
        }
    }
    public void removeSuprevisor(){
        this.suprevisor=null;
    }
    public List<Product> getProducts() {
        return products;
    }

    public void viewInventory() {
        System.out.println("Merchant view inventory");
    }

    public void editProductInformation(Product p,String image,double price,String title,String category) {
        if(!(image == null)){p.setImage(this,image);}
        if(!(title == null)){p.setTitle(this,title);}
        if(!(category == null)){p.setCategory(this,category);}
        if(!(price == 0)){p.setPrice(this,price);}
    }

    public void addProduct() {
        System.out.println("Merchant add product");
    }
    public void setEmail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!(suprevisor == null)) {
            suprevisor.updateEmail(this, this.email, email);
        }
        this.email = email;
    }
    public void addSupervisor(MarketModerator suprevisor) {
        this.suprevisor=suprevisor;
    }
}
