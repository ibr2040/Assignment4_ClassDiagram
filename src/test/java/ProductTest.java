import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    public void testValidProductCreation(){
        Product p=new Product(
          "img.png",
          50.0,
          "Laptop",
          "Electronics",
          "Gaming laptop",
          true
        );

        assertEquals("img.png", p.getImage());
        assertEquals(50.0, p.getPrice());
        assertEquals("Laptop", p.getTitle());
        assertEquals("Electronics", p.getCategory());
        assertEquals("Gaming laptop", p.getDescription());
        assertTrue(p.getAvailability());
    }

    @Test
    public void testPriceCannotBeNegative(){
        assertThrows(IllegalArgumentException.class,()->{
           new Product("img",-10,"a","b","c",true);
        });
    }

    @Test
    public void testTitleCannotBeEmpty(){
        assertThrows(IllegalArgumentException.class,()->{
            new Product("img",10,"","c","f",true);
        });
    }

    @Test
    public void testStaticAdvertisementFee(){
        Product.setAdvertismentFee(100.0);
        assertEquals(100.0,Product.getAdvertismentFee());
    }

    @Test
    public void testStaticAdvertisementFeeNegative(){
        assertThrows(IllegalArgumentException.class,()->{
           Product.setAdvertismentFee(-100);
        });
    }
}