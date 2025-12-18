import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarketModeratorMerchantTest {
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
                "PL44500105175407324931",
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

        assertEquals(4, mm.getAllSuspectedMerchants().size());

        mm.removeSuspectedMerchant("rm@test.com");

        assertEquals(3, mm.getAllSuspectedMerchants().size());
    }

    @Test
    void testBanUserMethod() {
        MarketModerator mm = new MarketModerator(
                "Mod123", "mod@example.com", "+123456789",
                "modLogin", "password123",
                "St", "C", "S", "EMP123"
        );

        User u = new Merchant(
                "Bob1243", "bob@example.com", "+111222333",
                "loginBob", "password000",
                "Street", "City", "State", "EMP777"
        );

        assertDoesNotThrow(() -> mm.banUser(u));
    }
    @Test
    void testGetByEmail(){
        MarketModerator mm1 = new MarketModerator(
                "Mod1", "mod1@example.com", "+123456789",
                "modLogi1n", "password123",
                "St", "C", "S", "EMP123");
        Merchant m1 = new Merchant(
                "Joh1n", "rm1@test.com", "+114431534531",
                "log1in", "password1888",
                "PL61109010140000071219812874",
                "Street", "City", "1State"
        );
        mm1.addSuspectedMerchant(m1);
        assertEquals(m1,mm1.getSuspectedMerchantByEmail(m1.getEmail()));
    }

    @Test
    void testAutoEmailUpdate(){
        MarketModerator mm12 = new MarketModerator(
                "Mod12", "mod12@example.com", "+1223456789",
                "mod2Logi1n", "passwo2rd123",
                "St2", "C2", "S2", "EMP123");
        Merchant m12 = new Merchant(
                "Joh12n", "rm1@te2st.com", "+1144321534531",
                "lo2g1in", "passw2ord1888",
                "PL61109010140000071219812874",
                "Stre2et", "Ci2ty", "1St2ate"
        );
        mm12.addSuspectedMerchant(m12);
        m12.setEmail("mod123@example.com");
        assertEquals(m12,mm12.getSuspectedMerchantByEmail("mod123@example.com"));
    }

}