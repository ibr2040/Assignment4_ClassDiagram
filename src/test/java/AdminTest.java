import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {
    @Test
    void testAddArchiveLog() {
        Admin admin = new Admin(
                "Admin", "admin@example.com", "+111222333",
                "login", "password123",
                "St", "C", "S", "EMP900"
        );

        admin.addArchiveLog("Log1");

        assertEquals(1, admin.getArchiveLogs().size());
        assertEquals("Log1", admin.getArchiveLogs().get(0));
    }

    @Test
    void testAddEmptyLogThrows() {
        Admin admin = new Admin(
                "Admin", "admin@example.com", "+111222333",
                "login", "password123",
                "St", "C", "S", "EMP900"
        );

        assertThrows(IllegalArgumentException.class, () ->
                admin.addArchiveLog(" ")
        );
    }

    @Test
    void testEditArchiveLogs() {
        Admin admin = new Admin(
                "Admin", "admin@example.com", "+111222333",
                "login", "password123",
                "St", "C", "S", "EMP900"
        );

        admin.addArchiveLog("old");
        admin.editArchiveLogs(0, "new");

        assertEquals("new", admin.getArchiveLogs().get(0));
    }

    @Test
    void testEditArchiveLogsInvalidIndex() {
        Admin admin = new Admin(
                "Admin", "admin@example.com", "+111222333",
                "login", "password123",
                "St", "C", "S", "EMP900"
        );

        assertThrows(IllegalArgumentException.class, () ->
                admin.editArchiveLogs(5, "something")
        );
    }

    @Test
    void testSuspendModerator() {
        Admin admin = new Admin(
                "Admin", "admin@example.com", "+111222333",
                "login", "password123",
                "St", "C", "S", "EMP900"
        );

        Moderator mod = new Moderator(
                "Mod", "mod@example.com", "+999888777",
                "log", "password999",
                "Street", "City", "State", "EMP444"
        );

        assertDoesNotThrow(() -> admin.suspendModer(mod));
    }

}