import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {
    @Test
    void testAddArchiveLog() {
        Admin admin = new Admin();

        admin.addArchiveLog("Log1");

        assertEquals(2, admin.getArchiveLogs().size());
        assertEquals("Log1", admin.getArchiveLogs().get(admin.getArchiveLogs().size()-1));
    }

    @Test
    void testAddEmptyLogThrows() {
        Admin admin = new Admin();

        assertThrows(IllegalArgumentException.class, () ->
                admin.addArchiveLog(" ")
        );
    }

    @Test
    void testEditArchiveLogs() {
        Admin admin = new Admin();

        admin.addArchiveLog("old");
        admin.editArchiveLogs(0, "new");

        assertEquals("new", admin.getArchiveLogs().get(0));
    }

    @Test
    void testEditArchiveLogsInvalidIndex() {
        Admin admin = new Admin();

        assertThrows(IllegalArgumentException.class, () ->
                admin.editArchiveLogs(5, "something")
        );
    }

    @Test
    void testSuspendModerator() {
        Admin admin = new Admin();

        Moderator mod = new Moderator(
                "Mod", "mod@example.com", "+999888777",
                "log", "password999",
                "Street", "City", "State", "EMP444",null
        );

        assertDoesNotThrow(() -> admin.suspendModer(mod));
    }

}