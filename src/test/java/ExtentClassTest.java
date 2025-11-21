import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExtentClassTest {
    @Test
    public void testRegisterAndSaveLoadAll() throws Exception {


        ExtentClass.getAllObjects().clear();
        assertEquals(0, ExtentClass.getAllObjects().size());

        Product p = new Product(
                "/images/test.png",
                100,
                "Phone",
                "Electronics",
                "desc",
                true
        );

        StripeClient sc = new StripeClient("CLIENT12345");

        assertEquals(2, ExtentClass.getAllObjects().size());



        ExtentClass.saveAll();



        ExtentClass.getAllObjects().clear();
        assertEquals(0, ExtentClass.getAllObjects().size());



        ExtentClass.loadAll();

        assertEquals(2, ExtentClass.getAllObjects().size());

        List<Object> loaded = ExtentClass.getAllObjects();


        assertTrue(loaded.get(0) instanceof Product || loaded.get(1) instanceof Product);
        assertTrue(loaded.get(0) instanceof StripeClient || loaded.get(1) instanceof StripeClient);


        assertNotSame(p, loaded.get(0));
        assertNotSame(sc, loaded.get(1));
    }
}