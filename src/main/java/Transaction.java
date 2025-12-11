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

    private Transaction parent;

    private final List<Transaction> children=new ArrayList<>();

    public Transaction(String receiveBankInformation, String payerInformation, double value, String currency, LocalDate dateOfTransaction,StripeClient stripeClient) {
        if (receiveBankInformation == null || receiveBankInformation.isBlank()) {
            throw new IllegalArgumentException("Bank Information cannot be empty");
        }

        if (!receiveBankInformation.matches("[A-Z0-9]{5,34}")) {
            throw new IllegalArgumentException("Bank information must be valid IBAN-like format");
        }

        if (payerInformation == null || payerInformation.isBlank()) {
            throw new IllegalArgumentException("Payer Information cannot be empty");
        }

        if (payerInformation.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Payer Information cannot contain digits");
        }

        if (value < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }

        if (value == 0) {
            throw new IllegalArgumentException("Value cannot be zero");
        }

        if (value > 1_000_000) {
            throw new IllegalArgumentException("Value exceeds transfer limit");
        }

        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Currency cannot be empty");
        }

        if (!currency.matches("[A-Z]{3}")) {
            throw new IllegalArgumentException("Currency must be ISO-3 format (e.g., USD)");
        }

        if (dateOfTransaction == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }

        if (dateOfTransaction.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the future");
        }

        if (dateOfTransaction.isBefore(LocalDate.now().minusYears(10))) {
            throw new IllegalArgumentException("Date cannot be older than 10 years");
        }

        if (stripeClient == null) {
            throw new IllegalArgumentException("Stripe client cannot be null");
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
    //here functions from assigment 6
    public void addChild(Transaction child) {

        if (child == null) {
            throw new IllegalArgumentException("Child transaction cannot be null");
        }

        if (child == this) {
            throw new IllegalStateException("Transaction cannot be child of itself");
        }

        if (children.contains(child)) {
            throw new IllegalStateException("This child is already connected");
        }

        if (child.getParent() != null) {
            throw new IllegalStateException("This transaction already has a parent and cannot be reassigned.");
        }

        if (isAncestor(child)) {
            throw new IllegalStateException("Cycle detected in reflexive association");
        }

        children.add(child);

        child.setParent(this);
    }
    public void removeChild(Transaction child) {
        if (!children.contains(child)) {
            throw new IllegalArgumentException("Child not found in this parent");
        }

        children.remove(child);

        child.setParent(null);
    }
    public void setParent(Transaction newParent) {
        if (newParent == this) {
            throw new IllegalStateException("Transaction cannot be its own parent");
        }

        if (newParent != null && newParent.isAncestor(this)) {
            throw new IllegalStateException("Cycle detected in reflexive association");
        }

        if (this.parent == newParent) {
            return;
        }

        if (this.parent != null) {
            this.parent.children.remove(this);
        }

        this.parent = newParent;

        if (newParent != null && !newParent.children.contains(this)) {
            newParent.children.add(this);
        }
    }
    public Transaction getParent() {
        return parent;
    }

    public List<Transaction> getChildren() {
        return new ArrayList<>(children);
    }

    private boolean isAncestor(Transaction candidate) {
        Transaction current = this;
        while (current != null) {
            if (current == candidate){
                return true;
            }
            current = current.parent;
        }
        return false;
    }
}
