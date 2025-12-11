import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionAssociationTest {
    StripeClient sc = new StripeClient("asdfsadf");
    @Test
    void testAddChildCreatesReverseConnection() {
        Transaction parent = new Transaction("A", "B", 100, "USD",LocalDate.now(),sc);
        Transaction child = new Transaction("C", "D", 50, "USD",LocalDate.now(),sc);

        parent.addChild(child);

        assertEquals(parent, child.getParent());
        assertTrue(parent.getChildren().contains(child));
    }

    @Test
    void testRemoveChildRemovesReverseConnection() {
        Transaction parent = new Transaction("A", "B", 100, "USD",LocalDate.now(),sc);
        Transaction child = new Transaction("C", "D", 50, "USD",LocalDate.now(),sc);

        parent.addChild(child);
        parent.removeChild(child);

        assertNull(child.getParent());
        assertFalse(parent.getChildren().contains(child));
    }

    @Test
    void testCannotAddNullChild() {
        Transaction parent = new Transaction("A", "B", 100, "USD",LocalDate.now(),sc);

        assertThrows(IllegalArgumentException.class, () -> parent.addChild(null));
    }

    @Test
    void testCannotAddSelfAsChild() {
        Transaction t = new Transaction("A", "B", 100, "USD",LocalDate.now(),sc);

        assertThrows(IllegalStateException.class, () -> t.addChild(t));
    }

    @Test
    void testCannotAddDuplicateChild() {
        Transaction parent = new Transaction("A", "B", 100, "USD",LocalDate.now(),sc);
        Transaction child = new Transaction("A", "B", 100, "USD",LocalDate.now(),sc);

        parent.addChild(child);

        assertThrows(IllegalStateException.class, () -> parent.addChild(child));
    }

    @Test
    void testChildCannotHaveTwoParents() {
        Transaction p1 = new Transaction("A", "B", 100, "USD",LocalDate.now(),sc);
        Transaction p2 = new Transaction("C", "D", 50, "USD",LocalDate.now(),sc);


        Transaction child = new Transaction("X", "Y", 150, "USD",LocalDate.now(),sc);

        p1.addChild(child);

        assertThrows(IllegalStateException.class, () -> p2.addChild(child));
    }

    @Test
    void testCycleDetectionWorks() {
        Transaction t1 = new Transaction("A", "B", 100, "USD",LocalDate.now(),sc);
        Transaction t2 = new Transaction("C", "D", 50, "USD",LocalDate.now(),sc);
        Transaction t3 = new Transaction("X", "Y", 10, "USD",LocalDate.now(),sc);

        t1.addChild(t2);
        t2.addChild(t3);

        assertThrows(IllegalStateException.class, () -> t3.addChild(t1));
    }

    @Test
    void testRemoveNonExistingChildThrows() {
        Transaction t1 = new Transaction("A", "B", 100, "USD",LocalDate.now(),sc);
        Transaction t2 = new Transaction("C", "D", 50, "USD",LocalDate.now(),sc);

        assertThrows(IllegalArgumentException.class, () -> t1.removeChild(t2));
    }
}
