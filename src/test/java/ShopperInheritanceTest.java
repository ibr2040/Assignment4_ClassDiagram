import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopperInheritanceTest {
    private User createUser() {
        return new User(
                "Alice Shopper",
                "shopper@mail.com",
                "+123456789",
                "login",
                "password123",
                "Street",
                "City",
                "State"
        );
    }

    @Test
    void shopperUsesUserData() {
        User user = createUser();
        Shopper shopper = new Shopper(user);

        assertEquals("Alice Shopper", shopper.getFullName());
    }

    @Test
    void shopperDelegatesToUser() {
        User user = createUser();
        Shopper shopper = new Shopper(user);

        assertEquals(user.getFullName(), shopper.getFullName());
    }
}