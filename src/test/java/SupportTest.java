import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SupportTest {

    @Test
    public void testAddTicketToHistory() {
        Support support = new Support(
                "John Doe",
                "john@example.com",
                "+1234567890",
                "johnLogin",
                "superSecret1",
                "Main St",
                "NY",
                "NY",
                "EMP001"
        );

        support.addTicketToHistory("Ticket #1");

        assertEquals(1, support.getTicketsHistory().size());
        assertTrue(support.getTicketsHistory().contains("Ticket #1"));
    }

    @Test
    public void testCloseExistingTicket() {
        Support support = new Support(
                "John Doe",
                "john@example.com",
                "+1234567890",
                "johnLogin",
                "superSecret1",
                "Main St",
                "NY",
                "NY",
                "EMP001"
        );

        support.addTicketToHistory("Ticket #1");

        assertDoesNotThrow(() -> support.closeTicket("Ticket #1"));
    }

    @Test
    public void testCloseTicketNotFound() {
        Support support = new Support(
                "John Doe",
                "john@example.com",
                "+1234567890",
                "johnLogin",
                "superSecret1",
                "Main St",
                "NY",
                "NY",
                "EMP001"
        );

        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                support.closeTicket("Unknown Ticket")
        );
        assertEquals("Ticket not found in history", ex.getMessage());
    }

    @Test
    public void testAddTicketValidation() {
        Support support = new Support(
                "John Doe",
                "john@example.com",
                "+1234567890",
                "johnLogin",
                "superSecret1",
                "Main St",
                "NY",
                "NY",
                "EMP001"
        );

        assertThrows(IllegalArgumentException.class, () ->
                support.addTicketToHistory("")
        );

        assertThrows(IllegalArgumentException.class, () ->
                support.addTicketToHistory(null)
        );
    }
}
