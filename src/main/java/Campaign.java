import java.io.*;
import java.util.*;


public class Campaign implements Serializable {
    private static final List<Campaign> extent = new ArrayList<>();
    private static final String EXT_FILE = "campaign_extent.ser";

    private final List<Product> products=new ArrayList<>();
    private String campaignName;
    private int budget;
    private String pricePerInteraction;


    public Campaign(String campaignName, int budget,List<Product> initialProducts) {
        if (!campaignName.equals("")) {
            this.campaignName = campaignName;
        } else {
            throw new IllegalArgumentException("Invalid campaign name");
        }
        if (initialProducts == null || initialProducts.isEmpty()) {
            throw new IllegalArgumentException("Campaign must start with at least 1 product (1..*)");
        }
        if (budget > 0) {
            this.budget = budget;
        } else {
            throw new IllegalArgumentException("Invalid budget");
        }

        for (Product p: initialProducts){
            addProduct(p);
        }
        extent.add(this);
    }

    public void addProduct(Product product){
        if (product==null){
            throw new IllegalArgumentException("Cannot add null product");
        }

        if (products.contains(product)){
            throw new IllegalStateException("Product already added to this campaign");
        }

        if (product.getCampaign() != null) {
            throw new IllegalStateException("Product already belongs to another campaign");
        }

        products.add(product);
        product.setCampaign(this);
    }

    public void removeProduct(Product product){
        if (!products.contains(product)){
            throw new IllegalArgumentException("Product not found in campaign");
        }

        if (products.size() == 1) {
            throw new IllegalStateException("Cannot remove last product");
        }

        products.remove(product);
        product.removeCampaign();
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
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