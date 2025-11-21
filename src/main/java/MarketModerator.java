import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketModerator extends Moderator{
    private Map<String,Merchant> suspectedMerchants = new HashMap<>();

    public MarketModerator(String fullName,
                           String email,
                           String mobileNumber,
                           String login,
                           String password,
                           String street,
                           String city,
                           String state,
                           String employeeNumber) {
        super(fullName, email, mobileNumber, login, password, street, city, state, employeeNumber);
    }

    public void addSuspectedMerchant(Merchant merchant) {
        if (merchant == null)
            throw new IllegalArgumentException("Merchant cannot be null");

        String key = merchant.getEmail();

        if (suspectedMerchants.containsKey(key))
            throw new IllegalArgumentException("Merchant already suspected");

        suspectedMerchants.put(key, merchant);
    }

    public Merchant getSuspectedMerchantByEmail(String email) {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email cannot be empty");
        return suspectedMerchants.get(email);
    }

    public List<Merchant> getAllSuspectedMerchants() {
        return new ArrayList<>(suspectedMerchants.values());
    }

    public void removeSuspectedMerchant(String email) {
        suspectedMerchants.remove(email);
    }

    public void banUser(User user) {
        System.out.println("Banning user: " + user.getEmail());
    }

    public void dischargeCompany(String company) {
        System.out.println("Company discharged: " + company);
    }

    public void deleteProduct(Product p) {
        System.out.println("Product deleted: " + p.getTitle());
    }

}
