import java.io.*;
import java.util.*;


public class Campaign implements Serializable {
    private static final List<Campaign> extent = new ArrayList<>();
    private static final String EXT_FILE = "campaign_extent.ser";


    private String campaignName;
    private int budget;
    private String pricePerInteraction;


    public Campaign(String campaignName, int budget) {
        if (!campaignName.equals("")) {
            this.campaignName = campaignName;
        } else {
            throw new InvalidFormatException("Invalid campaign name");
        }
        if (budget > 0) {
            this.budget = budget;
        } else {
            throw new InvalidValueException("Invalid budget");
        }


        extent.add(this);
    }


    public int getCampaignFee() {
        System.out.println(campaignName + " fee calculation in progress");
        return 0;
    }


    public static List<Campaign> getExtent() { return extent; }


    public static void saveExtent() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EXT_FILE));
        oos.writeObject(extent);
        oos.close();
    }


    public static void loadExtent() throws Exception {
        File f = new File(EXT_FILE);
        if (!f.exists()) return;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(EXT_FILE));
        List<Campaign> list = (List<Campaign>) ois.readObject();
        extent.clear(); extent.addAll(list);
        ois.close();
    }
}