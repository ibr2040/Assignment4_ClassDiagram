import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CampaignTest {

    @Test
    void testValidCampaignCreation() {
        Campaign c = new Campaign("Promo", 1000);
        assertEquals(0, c.getCampaignFee());
    }

    @Test
    void testInvalidCampaignName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Campaign("", 500);
        });
    }

    @Test
    void testInvalidCampaignBudget() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Campaign("Promo", -10);
        });
    }
}
