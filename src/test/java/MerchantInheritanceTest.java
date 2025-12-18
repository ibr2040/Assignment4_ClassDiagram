import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerchantInheritanceTest {
    private User createUser() {
        return new User(
                "Bob Merchant",
                "merchant@mail.com",
                "+123456789",
                "login",
                "password123",
                "Street",
                "City",
                "State"
        );
    }

    @Test
    void merchantUsesUserData() {
        Merchant merchant = new Merchant(createUser(), "PL12345");

        assertEquals("Bob Merchant", merchant.getFullName());
        assertEquals("merchant@mail.com", merchant.getEmail());
    }

    @Test
    void merchantDelegatesToUser() {
        User user = createUser();
        Merchant merchant = new Merchant(user, "PL12345");

        assertEquals(user.getFullName(), merchant.getFullName());
        assertEquals(user.getEmail(), merchant.getEmail());
    }
}