public class Merchant extends User {

    private String bankAccountNumber;

    public Merchant(
            String fullName,
            String email,
            String mobileNumber,
            String login,
            String password,
            String bankAccountNumber,
            String street,
            String city,
            String state
    ) {
        super(fullName, email, mobileNumber, login, password, street, city, state);

        if (bankAccountNumber == null || bankAccountNumber.isBlank()) {
            throw new EmptyFieldException("Bank account number cannot be empty");
        }

        // Only digits, length 10–20
        if (!bankAccountNumber.matches("\\d{10,20}")) {
            throw new InvalidFormatException("Bank account number must contain 10–20 digits, and no letters");
        }

        this.bankAccountNumber = bankAccountNumber;
    }

    public void viewInventory() {
        System.out.println("Merchant view inventory");
    }

    public void editProductInformation() {
        System.out.println("Merchant edit product information");
    }

    public void addProduct() {
        System.out.println("Merchant add product");
    }
}
