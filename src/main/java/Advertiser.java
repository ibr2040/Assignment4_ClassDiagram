import java.util.HashSet;

public class Advertiser{
    private User user;
    private HashSet<Campaign> campaigns = new HashSet <> ();

    public Advertiser(User user,Campaign campaign){

        if (campaign == null) {
            throw new IllegalArgumentException("Advertiser must have at least one initial campaign");
        }

        if (user==null){
            throw  new IllegalArgumentException();
        }
        this.user=user;
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

    public String getEmail(){
        return user.getEmail();
    }
}
