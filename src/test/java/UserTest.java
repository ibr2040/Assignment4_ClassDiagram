import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    public void testUserExtentSaveLoad() throws Exception{
        User.getExtent().clear();
        assertEquals(0,User.getExtent().size());

        Advertiser a = new Advertiser(
                "John Doe",
                "john@example.com",
                "+123456789",
                "johnLogin",
                "pass123555555",
                "Main St",
                "NY",
                "NY"
        );

        Merchant m = new Merchant(
                "Alice Smith",
                "alice@example.com",
                "+987654321",
                "aliceLogin",
                "pass5555555",
                "PL61109010140000071219812874",
                "Market St",
                "LA",
                "CA"
        );

        assertEquals(2,User.getExtent().size());

        User.saveExtent();

        User.getExtent().clear();
        assertEquals(0,User.getExtent().size());

        User.loadExtent();

        List<User> list=User.getExtent();
        assertEquals(2,list.size());

        assertInstanceOf(Advertiser.class, list.get(0));
        assertInstanceOf(Merchant.class, list.get(1));

        assertNotSame(a, list.get(0));
        assertNotSame(m, list.get(1));
    }

}