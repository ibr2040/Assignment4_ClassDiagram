import java.util.ArrayList;
import java.util.List;

public class Support extends Moderator {
    private List<String> ticketsHistory = new ArrayList<>();

    public Support(String fullName, String email, String mobileNumber, String login, String password, String street, String city, String state, String employeeNumber) {
        super(fullName, email, mobileNumber, login, password, street, city, state, employeeNumber);
    }

    public void closeTicket(String ticket) {
        if (!ticketsHistory.contains(ticket)) {
            throw new IllegalArgumentException("Ticket not found in history");
        }
        System.out.println("Ticket closed: " + ticket);
    }

    public void addTicketToHistory(String ticket) {
        if (ticket == null || ticket.isBlank()) {
            throw new IllegalArgumentException("Ticket cannot be empty");
        }
        ticketsHistory.add(ticket);
    }

    public List<String> getTicketsHistory() {
        return ticketsHistory;
    }
}
