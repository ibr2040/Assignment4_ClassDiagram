import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdvertiserCampaignTest {

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
    public void testAdvertiserDeleteLastCampaign() {
        Merchant mer = new Merchant(createUser(),"PL12345678");

        Product pro = new Product(
                "/images/tv.png",
                20,
                "TVs",
                "Electronics",
                true,
                mer
        );
        Campaign com = new Campaign("ahhhhhh",123,List.of(pro));
        Advertiser adv = new Advertiser(createUser(), com);
        assertThrows(IllegalArgumentException.class, () -> {adv.cancelCampaign(com);});
    }

    @Test
    public void testAdvertiserAddCampaign() {
        assertThrows(IllegalArgumentException.class, () ->{Advertiser adv = new Advertiser(createUser(),null);});
    }

    @Test
    public void testAdvertiserGetCampaigns() {
        Merchant mer = new Merchant(createUser(),"PL12345678");

        Product pro = new Product(
                "/images/tv.png",
                20,
                "TVs",
                "Electronics",
                true,
                mer
        );
        Campaign com = new Campaign("ahhhhhh",123,List.of(pro));
        Advertiser adv = new Advertiser(createUser(),com);
        assertThrows(IllegalArgumentException.class, () -> {adv.addCampaign(com);});
    }
    @Test
    public void testAdvertiserGetCampaign() {
        Merchant mer = new Merchant(createUser(),"PLO23482424234");
        Product pro = new Product("/images/asdf.png",20, "TV2", "Electronics",true,mer);
        Product pro1 = new Product("/images/asdf.png",20, "TV3", "Electronics",true,mer);
        Campaign com = new Campaign("ahhhhhh",123,List.of(pro));
        Campaign com1 = new Campaign("ahhhhhh",123,List.of(pro1));
        Advertiser adv = new Advertiser(createUser(),
                com);
        assertDoesNotThrow(() -> {adv.addCampaign(com1);});
    }
    @Test
    public void testAdvertiserUpdateCampaign() {
        Merchant mer = new Merchant(createUser(),"PL324293429442");
        Product pro = new Product("/images/asdf.png",20, "TV3", "Electronics",true,mer);
        Product pro1 = new Product("/images/asdfd.png",20, "TV3", "Electronics",true,mer);
        Campaign com = new Campaign("ahhhhhh",123,List.of(pro));
        Campaign com1 = new Campaign("ahhhhhh",123,List.of(pro1));
        Advertiser adv = new Advertiser(createUser(),com);
        adv.addCampaign(com1);
        assertDoesNotThrow(() -> {adv.cancelCampaign(com1);});
    }
}
