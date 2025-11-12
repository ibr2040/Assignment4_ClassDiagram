import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StripeClient implements Serializable {

    private static final List<StripeClient> extent=new ArrayList<>();
    private static final String EXTENT_FILE="stripe_extent.ser";


    private String clientId;

    public StripeClient(String clientId) {
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalArgumentException("Client ID cannot be empty");
        }
        this.clientId = clientId;
        extent.add(this);
    }

    public static void saveExtent() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EXTENT_FILE));
        oos.writeObject(extent);
        oos.close();
    }

    public static void loadExtent() throws Exception {
        File f = new File(EXTENT_FILE);
        if (!f.exists()) return;

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(EXTENT_FILE));
        List<StripeClient> list = (List<StripeClient>) ois.readObject();
        extent.clear();
        extent.addAll(list);
        ois.close();
    }

    public String getLinkToPaymentService(){
        return "https://stripe.com/client/"+clientId;
    }

    public void transferFunds() {
        System.out.println("Funds transferred via Stripe for client: " + clientId);
    }

    public String getClientId() {
        return clientId;
    }
}
