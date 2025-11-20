import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    private final StripeClient sc = new StripeClient("CLIENT12345");

    @Test
    public void testReceiveBankInformationCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("", "JohnDoe", 100, "USD", LocalDate.now(), sc));
    }

    @Test
    public void testReceiveBankInformationInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("INVALID!", "JohnDoe", 100, "USD", LocalDate.now(), sc));
    }
    @Test
    public void testPayerInformationCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("PL61109010140000071219812874", "", 100, "USD", LocalDate.now(), sc));
    }

    @Test
    public void testPayerInformationCannotContainDigits() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("PL61109010140000071219812874", "John123", 100, "USD", LocalDate.now(), sc));
    }
    @Test
    public void testValueCannotBeNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("PL61109010140000071219812874", "JohnDoe", -1, "USD", LocalDate.now(), sc));
    }

    @Test
    public void testValueCannotBeZero() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("PL61109010140000071219812874", "JohnDoe", 0, "USD", LocalDate.now(), sc));
    }

    @Test
    public void testValueCannotExceedLimit() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("PL61109010140000071219812874", "JohnDoe", 2_000_000, "USD", LocalDate.now(), sc));
    }

    @Test
    public void testCurrencyCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("PL61109010140000071219812874", "JohnDoe", 100, "", LocalDate.now(), sc));
    }

    @Test
    public void testDateCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("PL61109010140000071219812874", "JohnDoe", 100, "USD", null, sc));
    }

    @Test
    public void testDateCannotBeInFuture() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("PL61109010140000071219812874", "JohnDoe", 100, "USD", LocalDate.now().plusDays(1), sc));
    }

    @Test
    public void testDateCannotBeOlderThan10Years() {
        LocalDate oldDate = LocalDate.now().minusYears(11);
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("PL61109010140000071219812874", "JohnDoe", 100, "USD", oldDate, sc));
    }
    @Test
    public void testCurrencyMustBeISO3() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("PL61109010140000071219812874", "JohnDoe", 100, "US", LocalDate.now(), sc)); // too short
    }

    @Test
    public void testStripeClientCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("PL61109010140000071219812874", "JohnDoe", 100, "USD", LocalDate.now(), null));
    }

    @Test
    public void testTransactionExtentSaveLoad() throws Exception {

        Transaction.getExtent().clear();
        StripeClient.getExtent().clear();


        StripeClient sc = new StripeClient("CLIENT12345");


        Transaction t = new Transaction(
                "PL61109010140000071219812874",
                "JohnDoe",
                200,
                "USD",
                LocalDate.now().minusDays(1),
                sc
        );


        assertEquals(1, Transaction.getExtent().size());


        Transaction.saveExtent();


        Transaction.getExtent().clear();
        assertEquals(0, Transaction.getExtent().size());


        Transaction.loadExtent();
        assertEquals(1, Transaction.getExtent().size());

        Transaction loaded = Transaction.getExtent().get(0);

        assertEquals("USD", loaded.getCurrency());
        assertEquals(200, loaded.getValue());
        assertEquals("JohnDoe", loaded.getPayerInformation());
        assertEquals("PL61109010140000071219812874", loaded.getReceiveBankInformation());


        assertNotNull(loaded.getStripeClient());
        assertEquals("CLIENT12345", loaded.getStripeClient().getClientId());

        assertNotSame(t, loaded);
    }

}