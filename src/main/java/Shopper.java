public class Shopper extends User{
    public Shopper(     String fullName,
                           String email,
                           String mobileNumber,
                           String login,
                           String password,
                           String street,
                           String city,
                           String state
    ){
        super(fullName, email, mobileNumber, login, password,street,city,state);
    }
    public void changeProductQuantity(int quantity){
        System.out.println("Changing product quantity to " + quantity);
    }
    public  void requestPurchase(){
        System.out.println("Requesting purchase");
    }
}
