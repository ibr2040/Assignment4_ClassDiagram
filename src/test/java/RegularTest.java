import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegularTest {
    @Test
    void testRegularCreation() {
        Regular r = new Regular(
                "Bob", "bob@example.com", "+111222333",
                "bobLogin", "password123",
                "Street", "City", "State", "EMP777"
        );

        assertEquals("EMP777", r.getEmployeeNumber());
        assertEquals("bob@example.com", r.getEmail());
    }

}