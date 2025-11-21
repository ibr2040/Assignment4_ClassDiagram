import java.io.*;
import java.util.ArrayList;
import java.util.List;

abstract class User implements Serializable{

    private static final List<User> extent=new ArrayList<>();
    private static final String EXTENT_FILE = "user_extent.ser";

    private String fullName;
    private String email;
    private String mobileNumber;
    private ShippingAddress shippingAddress;
    private String login;
    private String password;

    private static final int MAX_NAME_LENGTH=50;

    public User(
            String fullName,
            String email,
            String mobileNumber,
            String login,
            String password,
            String street,
            String city,
            String state
    ) {
        if (fullName==null || fullName.isBlank()){
            throw new IllegalArgumentException("Full name cannot be empty");
        }

        if (fullName.length()>MAX_NAME_LENGTH){
            throw new IllegalArgumentException("Full name too long");
        }
        if (email==null ||email.isBlank()){
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
            throw new IllegalArgumentException("Invalid email format");
        }
        if (mobileNumber==null ||mobileNumber.isBlank()){
            throw new IllegalArgumentException("Mobile number cannot be empty");
        }
        if(!mobileNumber.matches("\\+?[0-9]{7,15}")){
            throw new IllegalArgumentException("Invalid number format");
        }
        if (login==null || login.isBlank()){
            throw new IllegalArgumentException("Login cannot be empty");
        }
        if (password==null || password.length()<9){
            throw new IllegalArgumentException("Password must be at least 9 chars");
        }
        this.email = email;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.login = login;
        this.password = password;
        this.shippingAddress = new ShippingAddress(street, city, state);

        extent.add(this);
    }

    public boolean updateProfile(
            String fullName,
            String email,
            String mobileNumber,
            ShippingAddress shippingAddress,
            String login,
            String password) {
        if (shippingAddress==null){
            throw new IllegalArgumentException("Address cannot be empty");
        }
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
    public static List<User> getExtent(){
        return extent;
    }

    public static void saveExtent() throws Exception{
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(EXTENT_FILE));
        oos.writeObject(extent);
        oos.close();
    }

    public static void loadExtent() throws Exception{
        File f=new File(EXTENT_FILE);
        if (!f.exists()){
            return;
        }

        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(EXTENT_FILE));
        List<User> list=(List<User>) ois.readObject();
        extent.clear();
        extent.addAll(list);
        ois.close();
    }



    public class ShippingAddress implements Serializable {
        private String street;
        private String city;
        private String state;

        ShippingAddress(String street, String city, String state) {
            if (street==null || street.isBlank()){
                throw new IllegalArgumentException("Street cannot be empty");
            }
            if (city==null || city.isBlank()){
                throw new IllegalArgumentException("City cannot be empty");
            }
            if (state==null || state.isBlank()){
                throw new IllegalArgumentException("State cannot be empty");
            }
            this.street = street;
            this.city = city;
            this.state = state;
        }

        public String toString() {
            return street + " " + city + " " + state;
        }
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
