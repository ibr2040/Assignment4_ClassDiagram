abstract class User {
    private String fullName;
    private String email;
    private String mobileNumber;
    private String shippingAddress;
    private String login;
    private String password;

     User(
            String fullName,
            String email,
            String mobileNumber,
            String shippingAddress,
            String login,
            String password
            ){
        this.email=email;
        this.fullName=fullName;
        this.mobileNumber=mobileNumber;
        this.shippingAddress=shippingAddress;
        this.login=login;
        this.password=password;
    }
    public boolean updateProfile(
            String fullName,
            String email,
            String mobileNumber,
            String shippingAddress,
            String login,
            String password) {
        this.email=email;
        this.fullName=fullName;
        this.mobileNumber=mobileNumber;
        this.shippingAddress=shippingAddress;
        this.login=login;
        this.password=password;
        return true;
    }
}
