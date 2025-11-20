public class Campaign {
    private String campaignName;
    private int budget;
    private String pricePerInteraction;

    Campaign(String campaignName, int budget) {
        if(!campaignName.equals("")) {
            this.campaignName = campaignName;
        }else{throw new InvalidFormatException("Invalid campaign name");}
        if(budget > 0) {
            this.budget = budget;
        }else{throw new InvalidValueException("Invalid budget");}
    }

    public int getCampaignFee(){
        System.out.println(campaignName+" fee calculation in progress");
        return 0;
    }
}
