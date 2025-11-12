public class Advertiser extends User{
    Advertiser(            String fullName,
                           String email,
                           String mobileNumber,
                           String shippingAddress,
                           String login,
                           String password){
        super(fullName, email, mobileNumber, shippingAddress, login, password);
    }
    public void addCampaign(){
        System.out.println("Advertiser: add campaign");
    }
    public void editCampaign(){
        System.out.println("Advertiser: edit campaign");
    }
    public void cancelCampaign(){
        System.out.println("Advertiser: cancel campaign");
    }
}
