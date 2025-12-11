import java.util.HashSet;

public class Advertiser extends User{
    private HashSet<Campaign> campaigns = new HashSet <> ();
    public Advertiser(     String fullName,
                           String email,
                           String mobileNumber,
                           String login,
                           String password,
                           String street,
                           String city,
                           String state,
                           Campaign campaign
    ){
        super(fullName, email, mobileNumber, login, password,street,city,state);
        campaigns.add(campaign);
    }
    public void addCampaign(Campaign campaign){
        if(!this.campaigns.contains(campaign) && campaign!=null) {
            this.campaigns.add(campaign);
            System.out.println("Advertiser: add campaign");
        }else{throw  new IllegalArgumentException("Campaign already exists");}
    }
    public void editCampaign(){
        System.out.println("Advertiser: edit campaign");
    }
    public void cancelCampaign(Campaign campaign){
        if (this.campaigns.size()==1) {throw  new IllegalArgumentException("Illegal operation exception");}else {
            this.campaigns.remove(campaign);
            System.out.println("Advertiser: cancel campaign");
        }
    }
}
