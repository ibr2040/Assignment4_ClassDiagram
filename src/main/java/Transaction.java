import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

enum Status implements Serializable{
    FAILED,
    SUCCESSEFUL,
    WAITS
}

public class Transaction implements Serializable{

    private static final List<Transaction> extent = new ArrayList<>();
    private static final String EXTENT_FILE = "transaction_extent.ser";

    private String receiveBankInformation;
    private String payerInformation;
    private double value;
    private String currency;
    private LocalDate dateOfTransaction;

    private final StripeClient stripeClient;

    public Transaction(String receiveBankInformation, String payerInformation, double value, String currency, LocalDate dateOfTransaction,StripeClient stripeClient) {
        if (receiveBankInformation==null || receiveBankInformation.isBlank()){
            throw new IllegalArgumentException("Bank Information cannot be empty ");
        }

        if (payerInformation==null || payerInformation.isBlank()){
            throw new IllegalArgumentException("Payer Information cannot be empty ");
        }
        if (value<0){
            throw new IllegalArgumentException("Value cannot be negative ");
        }
        if (currency==null || currency.isBlank()){
            throw new IllegalArgumentException("Currency Information cannot be empty ");
        }
        if (dateOfTransaction==null || dateOfTransaction.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Date cannot be in the future ");
        }

        if (stripeClient == null) {
            throw new IllegalArgumentException("Stripe client cannot be null");
        }

        if (receiveBankInformation.length() < 5) {
            throw new IllegalArgumentException("Bank information too short");
        }

        if (currency.length() != 3) {
            throw new IllegalArgumentException("Currency must be ISO-3 format");
        }

        this.receiveBankInformation = receiveBankInformation;
        this.payerInformation = payerInformation;
        this.value = value;
        this.currency = currency;
        this.dateOfTransaction = dateOfTransaction;
        this.stripeClient=stripeClient;

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
        List<Transaction> list = (List<Transaction>) ois.readObject();
        extent.clear();
        extent.addAll(list);
        ois.close();
    }

    public String getReceiveBankInformation() {
        return receiveBankInformation;
    }

    public String getPayerInformation() {
        return payerInformation;
    }

    public double getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDate getDateOfTransaction() {
        return dateOfTransaction;
    }

    public Status getStatus() {
        if (value == 0) {
            return Status.FAILED;
        }
        if (dateOfTransaction.isEqual(LocalDate.now())) {
            return Status.WAITS;
        }
        return Status.SUCCESSEFUL;
    }

    public StripeClient getStripeClient() {
        return stripeClient;
    }

    public void sendDataToStripe(){
        System.out.println("Sending transaction to: " + stripeClient.getLinkToPaymentService());
    }

    public void receiveConfirmation(){
        System.out.println("Transaction processed successfully for client: " + stripeClient.getClientId());
    }

    public static List<Transaction> getExtent() {
        return extent;
    }

    
}
