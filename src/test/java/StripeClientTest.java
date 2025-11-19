import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StripeClientTest {
    @Test
    public void testStripeClientExtentSaveLoad() throws Exception {

        StripeClient.getExtent().clear();
        assertEquals(0, StripeClient.getExtent().size());

        StripeClient s1 = new StripeClient("clientA123");
        StripeClient s2 = new StripeClient("clientB987");

        assertEquals(2, StripeClient.getExtent().size());

        StripeClient.saveExtent();


        StripeClient.getExtent().clear();
        assertEquals(0, StripeClient.getExtent().size());

        StripeClient.loadExtent();
        assertEquals(2, StripeClient.getExtent().size());


        assertEquals("clientA123", StripeClient.getExtent().get(0).getClientId());
        assertEquals("clientB987", StripeClient.getExtent().get(1).getClientId());

        assertNotSame(s1, StripeClient.getExtent().get(0));
        assertNotSame(s2, StripeClient.getExtent().get(1));
    }

}