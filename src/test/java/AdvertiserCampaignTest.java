import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdvertiserCampaignTest {
    @Test
    public void testAdvertiserDeleteLastCampaign() {
        User mer = new User(
                "John Doe",
                "asd@gmail.com",
                "+34124345353433",
                "login",
                "password323424234",
                "PL12345678",
                "street",
                "city"
        );
        mer.setMerchant("PL12345678");
        Product pro = new Product(
                "/images/tv.png",
                20,
                "TVs",
                "Electronics",
                true,
                mer.getMerchant()
        );
        Campaign com = new Campaign("ahhhhhh",123,List.of(pro));
        User adv = new User("a","email@mail.com","+12345678","email@mail.com","q@email.su","q","q","q");
        adv.setAdvertiser(com);
        assertThrows(IllegalArgumentException.class, () -> {adv.getAdvertiser().cancelCampaign(com);});
    }

    @Test
    public void testAdvertiserAddCampaign() {
        User adv = new User("a","email@mail.com","+12345678","email@mail.com","q@email.su","q","q","q");
        assertThrows(IllegalArgumentException.class, () ->{adv.setAdvertiser(null);});
    }

    @Test
    public void testAdvertiserGetCampaigns() {
        User mer = new User(
                "John Doe",
                "asd@gmail.com",
                "+34124345353433",
                "login",
                "password323424234",
                "PL12345678",
                "street",
                "city"
        );
        mer.setMerchant("PL12345678");
        Product pro = new Product(
                "/images/tv.png",
                20,
                "TVs",
                "Electronics",
                true,
                mer.getMerchant()
        );
        Campaign com = new Campaign("ahhhhhh",123,List.of(pro));
        User adv = new User("a","email@mail.com","+12345678","email@mail.com","q@email.su","q","q","q");
        adv.setAdvertiser(com);
        assertThrows(IllegalArgumentException.class, () -> {adv.getAdvertiser().addCampaign(com);});
    }
    @Test
    public void testAdvertiserGetCampaign() {
        User mer = new User("1234","asdfsa@gmail.com","+341234243","sadfsadf","sad4242fsadf","PL343213415654","sdafsafd","sadfsafd");
        mer.setMerchant("PL343213415654");
        Product pro = new Product("/images/asdf.png",20, "TV2", "Electronics",true,mer.getMerchant());
        Product pro1 = new Product("/images/asdf.png",20, "TV3", "Electronics",true,mer.getMerchant());
        Campaign com = new Campaign("ahhhhhh",123,List.of(pro));
        Campaign com1 = new Campaign("ahhhhhh",123,List.of(pro1));
        User adv = new User("a","email@mail.com","+12345678","email@mail.com","q@email.su","q","q","q");
        adv.setAdvertiser(com);
        assertDoesNotThrow(() -> {adv.getAdvertiser().addCampaign(com1);});
    }
    @Test
    public void testAdvertiserUpdateCampaign() {
        User mer = new User("1234","asdfsa@gmail.com","+3434534535534","sadfsadf","sadfs3453adf","PL213415654","sdafsafd","sadfsafd");
        mer.setMerchant("PL213415654");
        Product pro = new Product("/images/asdf.png",20, "TV3", "Electronics",true,mer.getMerchant());
        Product pro1 = new Product("/images/asdfd.png",20, "TV3", "Electronics",true,mer.getMerchant());
        Campaign com = new Campaign("ahhhhhh",123,List.of(pro));
        Campaign com1 = new Campaign("ahhhhhh",123,List.of(pro1));
        User adv = new User("a","email@mail.com","+12345678","email@mail.com","q@email.su","q","q","q");
        adv.setAdvertiser(com);
        adv.getAdvertiser().addCampaign(com1);
        assertDoesNotThrow(() -> {adv.getAdvertiser().cancelCampaign(com1);});
    }
}
