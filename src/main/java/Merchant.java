public class Merchant extends User{
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
    ){
        super(fullName,email,mobileNumber,login,password,street,city,state);
        this.bankAccountNumber = bankAccountNumber;
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
