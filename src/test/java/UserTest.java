import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    // Utility — создаём анонимного наследника, потому что User — abstract
    private User createUser(
            String fullName,
            String email,
            String mobileNumber,
            String login,
            String password,
            String street,
            String city,
            String state
    ) {
        return new User(fullName, email, mobileNumber, login, password, street, city, state) {};
    }

    // ─────────────────────────────────────────────
    //                FULL NAME TESTS
    // ─────────────────────────────────────────────

    @Test
    public void testFullNameEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                createUser(
                        "",
                        "email@mail.com",
                        "+123456789",
                        "login",
                        "password123",
                        "Street",
                        "City",
                        "State"
                )
        );
    }

    @Test
    public void testFullNameTooLong() {
        String longName = "a".repeat(60);
        assertThrows(IllegalArgumentException.class, () ->
                createUser(
                        longName,
                        "email@mail.com",
                        "+123456789",
                        "login",
                        "password123",
                        "Street",
                        "City",
                        "State"
                )
        );
    }

    // ─────────────────────────────────────────────
    //                EMAIL TESTS
    // ─────────────────────────────────────────────

    @Test
    public void testEmailEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                createUser(
                        "John Doe",
                        "",
                        "+123456789",
                        "login",
                        "password123",
                        "Street",
                        "City",
                        "State"
                )
        );
    }

    @Test
    public void testEmailInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () ->
                createUser(
                        "John Doe",
                        "wrong_email",
                        "+123456789",
                        "login",
                        "password123",
                        "Street",
                        "City",
                        "State"
                )
        );
    }

    // ─────────────────────────────────────────────
    //            MOBILE NUMBER TESTS
    // ─────────────────────────────────────────────

    @Test
    public void testMobileNumberEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                createUser(
                        "John Doe",
                        "email@mail.com",
                        "",
                        "login",
                        "password123",
                        "Street",
                        "City",
                        "State"
                )
        );
    }

    @Test
    public void testMobileNumberInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () ->
                createUser(
                        "John Doe",
                        "email@mail.com",
                        "abc123",
                        "login",
                        "password123",
                        "Street",
                        "City",
                        "State"
                )
        );
    }

    // ─────────────────────────────────────────────
    //              LOGIN + PASSWORD TESTS
    // ─────────────────────────────────────────────

    @Test
    public void testLoginEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                createUser(
                        "John Doe",
                        "email@mail.com",
                        "+123456789",
                        "",
                        "password123",
                        "Street",
                        "City",
                        "State"
                )
        );
    }

    @Test
    public void testPasswordTooShort() {
        assertThrows(IllegalArgumentException.class, () ->
                createUser(
                        "John Doe",
                        "email@mail.com",
                        "+123456789",
                        "login",
                        "short",
                        "Street",
                        "City",
                        "State"
                )
        );
    }

    // ─────────────────────────────────────────────
    //           SHIPPING ADDRESS TESTS
    // ─────────────────────────────────────────────

    @Test
    public void testStreetEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                createUser(
                        "John Doe",
                        "email@mail.com",
                        "+123456789",
                        "login",
                        "password123",
                        "",
                        "City",
                        "State"
                )
        );
    }

    @Test
    public void testCityEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                createUser(
                        "John Doe",
                        "email@mail.com",
                        "+123456789",
                        "login",
                        "password123",
                        "Street",
                        "",
                        "State"
                )
        );
    }

    @Test
    public void testStateEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                createUser(
                        "John Doe",
                        "email@mail.com",
                        "+123456789",
                        "login",
                        "password123",
                        "Street",
                        "City",
                        ""
                )
        );
    }

    // ─────────────────────────────────────────────
    //                updateProfile TEST
    // ─────────────────────────────────────────────

    @Test
    public void testUpdateProfileNullAddress() {
        User u = createUser(
                "John Doe",
                "email@mail.com",
                "+123456789",
                "login",
                "password123",
                "Street",
                "City",
                "State"
        );

        assertThrows(IllegalArgumentException.class, () ->
                u.updateProfile(
                        "John Doe",
                        "email@mail.com",
                        "+123456789",
                        null,
                        "newLogin",
                        "newPassword123"
                )
        );
    }

    // ─────────────────────────────────────────────
    //                  EXTENT TEST
    // ─────────────────────────────────────────────

    @Test
    public void testUserExtentSaveLoad() throws Exception {
        User.getExtent().clear();
        assertEquals(0, User.getExtent().size());

        Advertiser a = new Advertiser(
                "John Doe",
                "john@example.com",
                "+123456789",
                "johnLogin",
                "pass123555555",
                "Main St",
                "NY",
                "NY"
        );

        Merchant m = new Merchant(
                "Alice Smith",
                "alice@example.com",
                "+987654321",
                "aliceLogin",
                "pass5555555",
                "0140000071219812874",
                "Market St",
                "LA",
                "CA"
        );

        assertEquals(2, User.getExtent().size());

        User.saveExtent();

        User.getExtent().clear();
        assertEquals(0, User.getExtent().size());

        User.loadExtent();

        List<User> list = User.getExtent();
        assertEquals(2, list.size());

        assertInstanceOf(Advertiser.class, list.get(0));
        assertInstanceOf(Merchant.class, list.get(1));

        assertNotSame(a, list.get(0));
        assertNotSame(m, list.get(1));
    }
    @Test
    public void testMerchantBankAccountExceptions() {
        // пустой
        assertThrows(EmptyFieldException.class, () ->
                new Merchant("Alice", "a@a.com", "+123456789", "login", "password123",
                        "", "Street", "City", "State")
        );

        // слишком короткий
        assertThrows(InvalidFormatException.class, () ->
                new Merchant("Alice", "a@a.com", "+123456789", "login", "password123",
                        "12345", "Street", "City", "State")
        );

        // содержит буквы
        assertThrows(InvalidFormatException.class, () ->
                new Merchant("Alice", "a@a.com", "+123456789", "login", "password123",
                        "12AB345678", "Street", "City", "State")
        );
    }

    @Test
    public void testValidMerchantCreation() {
        Merchant m = new Merchant("Alice", "alice@mail.com", "+123456789", "login", "password123",
                "1234567890", "Street", "City", "State");
        assertNotNull(m);
    }

    @Test
    public void testValidAdvertiserCreation() {
        Advertiser a = new Advertiser("Bob", "bob@mail.com", "+987654321", "login", "password123",
                "Street", "City", "State");
        assertNotNull(a);
    }

}
