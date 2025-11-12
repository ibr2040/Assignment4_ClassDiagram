import java.io.Serializable;

abstract class User {

    private String fullName;
    private String email;
    private String mobileNumber;
    private ShippingAddress shippingAddress;
    private String login;
    private String password;

    User(
            String fullName,
            String email,
            String mobileNumber,
            String login,
            String password,
            String street,
            String city,
            String state
    ) {
        this.email = email;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.login = login;
        this.password = password;
        this.shippingAddress = new ShippingAddress(street, city, state);
    }

    public boolean updateProfile(
            String fullName,
            String email,
            String mobileNumber,
            ShippingAddress shippingAddress,
            String login,
            String password) {
        this.email = email;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.shippingAddress = shippingAddress;
        this.login = login;
        this.password = password;
        return true;
    }

    public void getAddress() {
        System.out.println(shippingAddress);
    }

    public class ShippingAddress implements Serializable {
        private String street;
        private String city;
        private String state;

        ShippingAddress(String street, String city, String state) {
            this.street = street;
            this.city = city;
            this.state = state;
        }

        public String toString() {
            return street + " " + city + " " + state;
        }
    }
}
