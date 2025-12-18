import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdvertiserInheritanceTest {
    private User createUser() {
        return new User(
                "Eve Advertiser",
                "adv@mail.com",
                "+123456789",
                "login",
                "password123",
                "Street",
                "City",
                "State"
        );
    }

    @Test
    void advertiserDelegatesToUser() {
        User user = createUser();

        Merchant merchant = new Merchant(user, "PL12345");

        Product product = new Product(
                "/images/test.png",
                10,
                "Test Product",
                "Electronics",
                true,
                merchant
        );

        Campaign campaign = new Campaign(
                "TestCampaign",
                100,
                List.of(product)
        );

        Advertiser advertiser = new Advertiser(user, campaign);

        assertEquals(user.getEmail(), advertiser.getEmail());
    }
}