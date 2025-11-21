import java.util.ArrayList;
import java.util.List;

public class Admin extends Moderator{

    List<String> archiveLogs=new ArrayList<>();

    public Admin(String fullName,
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

    public List<String> getArchiveLogs() {
        return archiveLogs;
    }

    public void addArchiveLog(String log){
        if (log==null || log.isBlank()){
            throw new IllegalArgumentException("Log cannot be empty");
        }
        archiveLogs.add(log);
    }

    public void suspendModer(Moderator moderator) {
        System.out.println("Moderator suspended: " + moderator.getEmployeeNumber());
    }

    public void editArchiveLogs(int index, String newValue) {
        if (index < 0 || index >= archiveLogs.size())
            throw new IllegalArgumentException("Invalid index");
        archiveLogs.set(index, newValue);
    }




}
