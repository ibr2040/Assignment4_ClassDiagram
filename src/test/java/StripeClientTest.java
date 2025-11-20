import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StripeClientTest {

    @Test
    public void testClientIdCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                new StripeClient("")
        );
    }

    @Test
    public void testClientIdCannotBeBlank() {
        assertThrows(IllegalArgumentException.class, () ->
                new StripeClient("   ")
        );
    }

    @Test
    public void testClientIdCannotContainSpaces() {
        assertThrows(IllegalArgumentException.class, () ->
                new StripeClient("client id")
        );
    }

    @Test
    public void testClientIdMustBeAlphanumeric() {
        assertThrows(IllegalArgumentException.class, () ->
                new StripeClient("client$#")
        );
    }

    @Test
    public void testClientIdTooShort() {
        assertThrows(IllegalArgumentException.class, () ->
                new StripeClient("abcd")  // 4 chars (min is 5)
        );
    }

    @Test
    public void testClientIdTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
                new StripeClient("abcdefghijklmnopqrstu") // 21 chars (max is 20)
        );
    }

    @Test
    public void testValidClientId() {
        StripeClient sc = new StripeClient("client123");
        assertEquals("client123", sc.getClientId());
    }


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