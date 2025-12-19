import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarketModeratorMerchantTest {
    @Test
    void testAddSuspectedMerchant() {
        MarketModerator mm = new MarketModerator(
                "Mod", "mod@example.com", "+123456789", "modLogin",
                "longpassword", "Main", "NY", "NY", "EMP001",null
        );

        User m = new User(
                "John Doe", "merchant@example.com", "+555000111",
                "johnLogin", "merchantPass123",
                "PL61109010140000071219812874",
                "Street", "City"
        );
        m.setMerchant("PL61109010140000071219812874");

        mm.addSuspectedMerchant(m.getMerchant());

        assertEquals(m.getMerchant(), mm.getSuspectedMerchantByEmail("merchant@example.com"));
    }

    @Test
    void testDuplicateSuspectedMerchantThrows() {
        MarketModerator mm = new MarketModerator(
                "Mod", "mod@example.com", "+123456789", "modLogin",
                "longpassword", "Main", "NY", "NY", "EMP001",null
        );

        User m1 = new User(
                "J", "aaa@bbb.com", "+13453455511", "l1", "password999",
                "PL61109010140000071219812874",
                "st", "c"
        );
        m1.setMerchant("PL61109010140000071219812874");

        User m2 = new User(
                "K", "aaa@bbb.com", "+234535422", "l2", "password111",
                "PL44500105175407324931",
                "xx", "yy"
        );
        m2.setMerchant("PL44500105175407324931");

        mm.addSuspectedMerchant(m1.getMerchant());

        assertThrows(IllegalArgumentException.class, () ->
                mm.addSuspectedMerchant(m2.getMerchant())
        );
    }

    @Test
    void testRemoveSuspectedMerchant() {
        MarketModerator mm = new MarketModerator(
                "Mod", "mod@example.com", "+123456789", "modLogin",
                "longpassword", "Main", "NY", "NY", "EMP001",null
        );

        User m = new User(
                "John123", "rm123@test.com", "+11443123534531",
                "login", "password888",
                "PL61109123010140000071219812874",
                "Stre123et", "City123"
        );
        m.setMerchant("PL61109010140000071219812874");
        mm.addSuspectedMerchant(m.getMerchant());

        assertEquals(4, mm.getAllSuspectedMerchants().size());

        mm.removeSuspectedMerchant("rm@test.com");

        assertEquals(3, mm.getAllSuspectedMerchants().size());
    }

    @Test
    void testBanUserMethod() {
        MarketModerator mm = new MarketModerator(
                "Mod123", "mod@example.com", "+123456789",
                "modLogin", "password123",
                "St", "C", "S", "EMP123",null
        );

        User u = new User(
                "Bob1243", "bob@example.com", "+111222333",
                "loginBob", "password000",
                "PLStreet", "City", "State"
        );

        assertDoesNotThrow(() -> mm.banUser(u));
    }
    @Test
    void testGetByEmail(){
        MarketModerator mm1 = new MarketModerator(
                "Mod1", "mod1@example.com", "+123456789",
                "modLogi1n", "password123",
                "St", "C", "S", "EMP123",null);
        User m1 = new User(
                "John", "rm@test.com", "+11443534531",
                "login", "password888",
                "PL61109010140000071219812874",
                "Street", "City"
        );
        m1.setMerchant("PL61109010140000071219812874");
        mm1.addSuspectedMerchant(m1.getMerchant());
        assertEquals(m1.getMerchant(),mm1.getSuspectedMerchantByEmail(m1.getEmail()));
    }

    @Test
    void testAutoEmailUpdate(){
        MarketModerator mm12 = new MarketModerator(
                "Mod12", "mod12@example.com", "+1223456789",
                "mod2Logi1n", "passwo2rd123",
                "St2", "C2", "S2", "EMP123",null);
        User m12 = new User(
                "John", "rm@test.com", "+11443534531",
                "login", "password888",
                "PL61109010140000071219812874",
                "Street", "City"
        );
        m12.setMerchant("PL61109010140000071219812874");
        mm12.addSuspectedMerchant(m12.getMerchant());
        m12.setEmail("mod123@example.com");
        assertEquals(m12.getMerchant(),mm12.getSuspectedMerchantByEmail("rm@test.com"));
    }

}