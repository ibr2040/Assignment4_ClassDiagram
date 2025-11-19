import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

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