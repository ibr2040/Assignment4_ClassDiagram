import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    public void testTransactionExtentSaveLoad() throws Exception {
        Transaction.getExtent().clear();

        StripeClient sc = new StripeClient("abc12");
        Transaction t = new Transaction("BankA", "UserB", 200, "USD", LocalDate.now(), sc);

        Transaction.saveExtent();

        Transaction.getExtent().clear();
        Transaction.loadExtent();

        assertEquals(1, Transaction.getExtent().size());
        assertEquals("USD", Transaction.getExtent().get(0).getCurrency());
    }

}