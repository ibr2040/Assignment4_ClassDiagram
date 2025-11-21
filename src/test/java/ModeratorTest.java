import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModeratorTest {
    @Test
    void testModeratorCreationValid() {
        Moderator m = new Moderator(
                "John Doe", "mod@example.com", "+111222333",
                "login", "password123",
                "Main St", "NY", "NY", "EMP001"
        );

        assertEquals("EMP001", m.getEmployeeNumber());
        assertEquals("mod@example.com", m.getEmail());
    }

    @Test
    void testModeratorInvalidEmployeeNumber() {
        assertThrows(IllegalArgumentException.class, () ->
                new Moderator(
                        "John", "a@a.com", "+111222333",
                        "l", "password123",
                        "St", "C", "S", ""
                )
        );
    }

}