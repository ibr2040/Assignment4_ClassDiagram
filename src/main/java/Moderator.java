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

    public Admin getAdmin() {
        return admin;
    }

    public void addArchiveLog(String log){
        if(admin==null){throw new UnsupportedOperationException("You dont have permissions");}
        if (log==null || log.isBlank()){
            throw new IllegalArgumentException("Log cannot be empty");
        }
        admin.archiveLogs.add(log+" By: "+this.fullName);
    }

    public void suspendModer(Moderator moderator) {
        if(admin==null){throw new UnsupportedOperationException("You dont have permissions");}
        admin.archiveLogs.add("Moderator suspended: " + moderator.getEmployeeNumber()+" By: "+this.fullName);
    }

    public void editArchiveLogs(int index, String newValue) {
        if(admin==null){throw new UnsupportedOperationException("You dont have permissions");}
        if (index < 0 || index >= admin.archiveLogs.size())
            throw new IllegalArgumentException("Invalid index");
        admin.archiveLogs.set(index, newValue+" By: "+this.fullName);
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

}
