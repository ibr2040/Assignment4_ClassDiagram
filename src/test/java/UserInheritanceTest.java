import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInheritanceTest {

    private User createUser() {
        return new User(
                "John Doe",
                "john@mail.com",
                "+123456789",
                "login",
                "password123",
                "Street",
                "City",
                "State"
        );
    }

    @Test
    void userIsIndependentEntity() {
        User user = createUser();
        assertEquals("John Doe", user.getFullName());
    }
}