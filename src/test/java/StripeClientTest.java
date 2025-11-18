import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StripeClientTest {
    @Test
    public void testProductExtentSaveLoad() throws Exception {
        Product.getExtent().clear();

        Product p = new Product("img.png", 120, "Phone", "Electronics", "desc", true);

        Product.saveExtent();

        Product.getExtent().clear();
        Product.loadExtent();

        assertEquals(1, Product.getExtent().size());
        assertEquals("Phone", Product.getExtent().get(0).getTitle());
    }

}