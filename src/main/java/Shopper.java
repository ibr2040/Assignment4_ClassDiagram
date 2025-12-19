public class Shopper{
    private User user;
    public Shopper( User user
    ){
        this.user=user;
    }
    public void changeProductQuantity(int quantity){
        System.out.println("Changing product quantity to " + quantity);
    }
    public  void requestPurchase(){
        System.out.println("Requesting purchase");
    }
}
