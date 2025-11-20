import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CampaignTest {

    @Test
    void testValidCampaignCreation() {
        Campaign c = new Campaign("Promo", 1000);
        assertEquals(0, c.getCampaignFee()); // метод пока возвращает 0
    }

    @Test
    void testInvalidCampaignName() {
        assertThrows(InvalidFormatException.class, () -> {
            new Campaign("", 500);
        });
    }

    @Test
    void testInvalidCampaignBudget() {
        assertThrows(InvalidValueException.class, () -> {
            new Campaign("Promo", -10);
        });
    }
}
