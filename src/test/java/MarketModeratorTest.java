import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarketModeratorTest {
    @Test
    void testAddSuspectedMerchant() {
        MarketModerator mm = new MarketModerator(
                "Mod", "mod@example.com", "+123456789", "modLogin",
                "longpassword", "Main", "NY", "NY", "EMP001"
        );

        Merchant merchant = new Merchant(
                "John Doe", "merchant@example.com", "+555000111",
                "johnLogin", "merchantPass123",
                "PL61109010140000071219812874",
                "Street", "City", "State"
        );

        mm.addSuspectedMerchant(merchant);

        assertEquals(merchant, mm.getSuspectedMerchantByEmail("merchant@example.com"));
    }

    @Test
    void testDuplicateSuspectedMerchantThrows() {
        MarketModerator mm = new MarketModerator(
                "Mod", "mod@example.com", "+123456789", "modLogin",
                "longpassword", "Main", "NY", "NY", "EMP001"
        );

        Merchant m1 = new Merchant(
                "J", "aaa@bbb.com", "+13453455511", "l1", "password999",
                "PL61109010140000071219812874",
                "st", "c", "s"
        );

        Merchant m2 = new Merchant(
                "K", "aaa@bbb.com", "+234535422", "l2", "password111",
                "DE44500105175407324931",
                "xx", "yy", "zz"
        );

        mm.addSuspectedMerchant(m1);

        assertThrows(IllegalArgumentException.class, () ->
                mm.addSuspectedMerchant(m2)
        );
    }

    @Test
    void testRemoveSuspectedMerchant() {
        MarketModerator mm = new MarketModerator(
                "Mod", "mod@example.com", "+123456789", "modLogin",
                "longpassword", "Main", "NY", "NY", "EMP001"
        );

        Merchant m = new Merchant(
                "John", "rm@test.com", "+11443534531",
                "login", "password888",
                "PL61109010140000071219812874",
                "Street", "City", "State"
        );

        mm.addSuspectedMerchant(m);

        assertEquals(1, mm.getAllSuspectedMerchants().size());

        mm.removeSuspectedMerchant("rm@test.com");

        assertEquals(0, mm.getAllSuspectedMerchants().size());
    }

    @Test
    void testBanUserMethod() {
        MarketModerator mm = new MarketModerator(
                "Mod", "mod@example.com", "+123456789",
                "modLogin", "password123",
                "St", "C", "S", "EMP123"
        );

        User u = new Regular(
                "Bob", "bob@example.com", "+111222333",
                "loginBob", "password000",
                "Street", "City", "State", "EMP777"
        );

        assertDoesNotThrow(() -> mm.banUser(u));
    }
}