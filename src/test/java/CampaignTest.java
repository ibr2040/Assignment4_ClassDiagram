import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CampaignTest {

    @Test
    void testValidCampaignCreation() {
        Campaign c = new Campaign("Promo", 1000, List.of(new Product("asdf",12,"sdfa","asdf","sadff",true)));
        assertEquals(0, c.getCampaignFee());
    }

    @Test
    void testInvalidCampaignName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Campaign("", 500,List.of(new Product("asdf",12,"sdfa","asdf","sadff",true)));
        });
    }

    @Test
    void testInvalidCampaignBudget() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Campaign("Promo", -10,List.of(new Product("asdf",12,"sdfa","asdf","sadff",true)));
        });
    }
}
