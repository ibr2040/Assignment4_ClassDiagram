public class Moderator extends User{
    private String employeeNumber;

    public Moderator(String fullName,
                     String email,
                     String mobileNumber,
                     String login,
                     String password,
                     String street,
                     String city,
                     String state,
                     String employeeNumber) {
        super(fullName, email, mobileNumber, login, password, street, city, state);
        if (employeeNumber==null || employeeNumber.isBlank()){
            throw new IllegalArgumentException("Employee number cannot be empty");
        }
        this.employeeNumber=employeeNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }
}
