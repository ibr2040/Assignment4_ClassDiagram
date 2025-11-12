public class Advertiser extends User{
    public Advertiser(     String fullName,
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
