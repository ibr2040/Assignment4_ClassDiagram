import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarketModeratorTest {
    private User createUser() {
        return new User(
                "John Doe",
                "asd@gmail.com",
                "+34124345353433",
                "login",
                "password323424234",
                "street",
                "city",
                "state"
        );
    }

    @Test
    void testAddSuspectedMerchant() {
        MarketModerator mm = new MarketModerator(
                "Mod", "mod@example.com", "+123456789", "modLogin",
                "longpassword", "Main", "NY", "NY", "EMP001"
        );

        Merchant merchant = new Merchant(
                createUser(), "PL23487942372"
        );

        mm.addSuspectedMerchant(merchant);

        assertEquals(merchant, mm.getSuspectedMerchantByEmail("asd@gmail.com"));
    }

    @Test
    void testDuplicateSuspectedMerchantThrows() {
        MarketModerator mm = new MarketModerator(
                "Mod", "mod@example.com", "+123456789", "modLogin",
                "longpassword", "Main", "NY", "NY", "EMP001"
        );

        Merchant m1 = new Merchant(
                createUser(), "PL32423492739"
        );

        Merchant m2 = new Merchant(
                createUser(), "PL3479987389278972");

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
                createUser(), "PL234792872874284232"
        );

        mm.addSuspectedMerchant(m);

        assertEquals(1, mm.getAllSuspectedMerchants().size());

        mm.removeSuspectedMerchant("asd@gmail.com");

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