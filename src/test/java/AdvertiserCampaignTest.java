import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdvertiserCampaignTest {
    @Test
    public void testAdvertiserDeleteLastCampaign() {
        Merchant mer = new Merchant("1234","asdfsa@gmail.com","+341243","sadfsadf","sadfsadf","213415654","sdafsafd","sadfsafd","asdfdsaf");
        Product pro = new Product("asdf",20, "TV", "g",true,mer);
        Campaign com = new Campaign("ahhhhhh",123,List.of(pro));
        Advertiser adv = new Advertiser("a","email@mail.com","+12345678","email@mail.com","q@email.su","q","q","q",
                com);
        assertThrows(IllegalArgumentException.class, () -> {adv.cancelCampaign(com);});
    }
    @Test
    public void testAdvertiserAddCampaign() {
        assertThrows(IllegalArgumentException.class, () ->{Advertiser adv = new Advertiser("a","email@mail.com","+12345678","email@mail.com","q@email.su","q","q","q",null);});
    }
    @Test
    public void testAdvertiserGetCampaigns() {
        Merchant mer = new Merchant("1234","asdfsa@gmail.com","+341243","sadfsadf","sadfsadf","213415654","sdafsafd","sadfsafd","asdfdsaf");
        Product pro = new Product("asdf",20, "TV", "g",true,mer);;
        Campaign com = new Campaign("ahhhhhh",123,List.of(pro));
        Advertiser adv = new Advertiser("a","email@mail.com","+12345678","email@mail.com","q@email.su","q","q","q",
                com);
        assertThrows(IllegalArgumentException.class, () -> {adv.addCampaign(com);});
    }
    @Test
    public void testAdvertiserGetCampaign() {
        Merchant mer = new Merchant("1234","asdfsa@gmail.com","+341243","sadfsadf","sadfsadf","213415654","sdafsafd","sadfsafd","asdfdsaf");
        Product pro = new Product("asdf",20, "TV", "g",true,mer);
        Product pro1 = new Product("asdf",20, "TV", "g",true,mer);
        Campaign com = new Campaign("ahhhhhh",123,List.of(pro));
        Campaign com1 = new Campaign("ahhhhhh",123,List.of(pro1));
        Advertiser adv = new Advertiser("a","email@mail.com","+12345678","email@mail.com","q@email.su","q","q","q",
                com);
        assertDoesNotThrow(() -> {adv.addCampaign(com1);});
    }
    @Test
    public void testAdvertiserUpdateCampaign() {
        Merchant mer = new Merchant("1234","asdfsa@gmail.com","+341243","sadfsadf","sadfsadf","213415654","sdafsafd","sadfsafd","asdfdsaf");
        Product pro = new Product("asdf",20, "TV", "g",true,mer);
        Product pro1 = new Product("asdf",20, "TV", "g",true,mer);
        Campaign com = new Campaign("ahhhhhh",123,List.of(pro));
        Campaign com1 = new Campaign("ahhhhhh",123,List.of(pro1));
        Advertiser adv = new Advertiser("a","email@mail.com","+12345678","email@mail.com","q@email.su","q","q","q",
                com);
        adv.addCampaign(com1);
        assertDoesNotThrow(() -> {adv.cancelCampaign(com1);});
    }
}
