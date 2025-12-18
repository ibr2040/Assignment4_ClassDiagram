import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
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
    public void testFullNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                    "",
                    "asd@gmail.com",
                    "+34124345353433",
                    "login",
                    "password323424234",
                    "street",
                    "city",
                    "state"
            );
        });
    }

    @Test
    public void testFullNameTooLong() {
        String longName = "a".repeat(60);
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                    longName,
                    "asd@gmail.com",
                    "+34124345353433",
                    "login",
                    "password323424234",
                    "street",
                    "city",
                    "state"
            );
        });
    }

    @Test
    public void testEmailEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                    "John Doe",
                    "",
                    "+34124345353433",
                    "login",
                    "password323424234",
                    "street",
                    "city",
                    "state"
            );
        });
    }

    @Test
    public void testEmailInvalidFormat() {
        new User(
                "John Doe",
                "skdkaljd@mail.com",
                "+34124345353433",
                "login",
                "password323424234",
                "street",
                "city",
                "state"
        );
    }
    @Test
    public void testMobileNumberEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                    "John Doe",
                    "asd@gmail.com",
                    "",
                    "login",
                    "password323424234",
                    "street",
                    "city",
                    "state"
            );
        });
    }

    @Test
    public void testMobileNumberInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                    "John Doe",
                    "asd@gmail.com",
                    "abc123",
                    "login",
                    "password323424234",
                    "street",
                    "city",
                    "state"
            );
        });
    }

    @Test
    public void testLoginEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                    "John Doe",
                    "asd@gmail.com",
                    "+34124345353433",
                    "",
                    "password323424234",
                    "street",
                    "city",
                    "state"
            );
        });
    }

    @Test
    public void testPasswordTooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                    "John Doe",
                    "asd@gmail.com",
                    "+34124345353433",
                    "login",
                    "short",
                    "street",
                    "city",
                    "state"
            );
        });
    }

    @Test
    public void testStreetEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                    "John Doe",
                    "asd@gmail.com",
                    "+34124345353433",
                    "login",
                    "password323424234",
                    "",
                    "city",
                    "state"
            );
        });
    }

    @Test
    public void testCityEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                    "John Doe",
                    "asd@gmail.com",
                    "+34124345353433",
                    "login",
                    "password323424234",
                    "street",
                    "",
                    "state"
            );
        });
    }

    @Test
    public void testStateEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                    "John Doe",
                    "asd@gmail.com",
                    "+34124345353433",
                    "login",
                    "password323424234",
                    "street",
                    "city",
                    ""
            );
        });
    }

    @Test
    public void testUpdateProfileNullAddress() {

        User user = createUser();

        assertThrows(IllegalArgumentException.class, () ->
                user.updateProfile(
                        "John Doe",
                        "email@mail.com",
                        "+123456789",
                        null,
                        "newLogin",
                        "newPassword123"
                )
        );
    }

    @Test
    public void testUserExtentSaveLoad() throws Exception {
        User.getExtent().clear();
        assertEquals(0, User.getExtent().size());

        User u1 = createUser();
        User u2 = createUser();

        Advertiser a = new Advertiser(
                u1,
                new Campaign(
                        "g",
                        1,
                        List.of(new Product(
                                "/images/asdf.png",
                                20,
                                "TV3",
                                "Electronics",
                                "asdf",
                                true
                        ))
                )
        );

        Merchant m = new Merchant(
                u2,
                "PL2348982749287"
        );


        assertEquals(2, User.getExtent().size());

        User.saveExtent();

        User.getExtent().clear();
        assertEquals(0, User.getExtent().size());

        User.loadExtent();

        List<User> list = User.getExtent();
        assertEquals(2, list.size());

        assertInstanceOf(User.class, list.get(0));
        assertInstanceOf(User.class, list.get(1));

        assertNotSame(u1, list.get(0));
        assertNotSame(u2, list.get(1));
    }

    @Test
    public void testMerchantBankAccountExceptions() {

        assertThrows(IllegalArgumentException.class, () ->
                new Merchant(createUser(), "a@a.com")
        );


        assertThrows(IllegalArgumentException.class, () ->
                new Merchant(createUser(), "a@a.com")
        );

        assertThrows(IllegalArgumentException.class, () ->
                new Merchant(createUser(), "a@a.com")
        );
    }

    @Test
    public void testValidMerchantCreation() {
        Merchant m = new Merchant(createUser(), "PL4892379472384");
        assertNotNull(m);
    }

    @Test
    public void testValidAdvertiserCreation() {
        Advertiser a = new Advertiser(createUser(),new Campaign("g3443",1,List.of(new Product("/images/asdf.png",20, "TV3", "Electronics","asdf",true))));
        assertNotNull(a);
    }
}