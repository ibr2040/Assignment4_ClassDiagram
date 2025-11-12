public class Merchant extends User{
    private String bankAccount;
    public Merchant(
            String fullName,
            String email,
            String mobileNumber,
            String shippingAddress,
            String login,
            String password,
            String bankAccount
    ){
        super(fullName,email,mobileNumber,shippingAddress,login,password);
        this.bankAccount = bankAccount;
    }
    public void viewInventory(){
        System.out.println("Merchant view inventory");
    }
    public void editProductInformation(){
        System.out.println("Merchant edit product information");
    }
    public void addProduct(){
        System.out.println("Merchant add product");
    }
}
