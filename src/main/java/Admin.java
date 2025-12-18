import java.util.ArrayList;
import java.util.List;

public class Admin{

    static List<String> archiveLogs=new ArrayList<>();


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
