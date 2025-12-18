public class Moderator extends User{
    private String employeeNumber;
    private Admin admin;


    public Moderator(String fullName,
                     String email,
                     String mobileNumber,
                     String login,
                     String password,
                     String street,
                     String city,
                     String state,
                     String employeeNumber,Admin admin) {
        super(fullName, email, mobileNumber, login, password, street, city, state);
        if (employeeNumber==null || employeeNumber.isBlank()){
            throw new IllegalArgumentException("Employee number cannot be empty");
        }
        this.employeeNumber=employeeNumber;
        this.admin=admin;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }
}
