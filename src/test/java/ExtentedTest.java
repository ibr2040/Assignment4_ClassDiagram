import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExtentedTest {
    @Test
    void testDurationExtentPersistence() throws Exception {
        DurationDate.Date d1 = new DurationDate.Date(2024,5,10,12);
        DurationDate.Date d2 = new DurationDate.Date(2024,5,20,12);
        DurationDate dd = new DurationDate(d1,d2);


        DurationDate.saveExtent();
        DurationDate.getExtent().clear();
        DurationDate.loadExtent();


        assertEquals(2, DurationDate.getExtent().size());
    }
        @Test
        void testMerchantExtentPersistence() throws Exception {
            Merchant m = new Merchant("Bob","bob@x.com","+123456789","log321","password123","PL1234567890","st","c","s");


            Merchant.saveExtent();
            Merchant.getExtent().clear();
            Merchant.loadExtent();


            assertEquals(3, Merchant.getExtent().size());
        }
    @Test
    void testCampaignExtentPersistence() throws Exception {
        Campaign.getExtent().clear();


        Campaign c = new Campaign("TestCamp", 100, List.of(new Product("/images/asdf.png",12,"sdfa","Electronics","sadff",true)));

        Campaign.saveExtent();
        Campaign.getExtent().clear();
        Campaign.loadExtent();


        assertEquals(1, Campaign.getExtent().size());
    }
    @Test
    void testStripeClientExtentPersistence() throws Exception {

        StripeClient.getExtent().clear();

        StripeClient c = new StripeClient("TestStripeClient");
        StripeClient.saveExtent();
        StripeClient.getExtent().clear();
        StripeClient.loadExtent();
         assertEquals(1, StripeClient.getExtent().size());
    }
    @Test
    void testAdvertiserExtentPersistence() throws Exception {
        Advertiser.getExtent().clear();
        Advertiser m = new Advertiser("Bob","bob@x.com","+123456789","log321","password123","st","c","s",new Campaign("TestCamp", 100, List.of(new Product("/images/a.png",133,"Liww","Electronics","fdfdd",true))));


        Advertiser.saveExtent();
        Advertiser.getExtent().clear();
        Advertiser.loadExtent();


        assertEquals(1, Advertiser.getExtent().size());
    }
}
