import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Product unavailableProduct(String title) {
        return new Product(
                "/images/" + title + ".png",
                100,
                title,
                "Phones",
                "desc",
                false
        );
    }

    @Test
    public void testAddNullProductThrowsException() {
        Cart cart = new Cart();
        assertThrows(IllegalArgumentException.class, () -> cart.addProduct(null));
    }



    @Test
    public void testCartExtentSaveLoad() throws Exception {
        Cart.getExtent().clear();

        Cart c1 = new Cart();
        Cart c2 = new Cart();

        Cart.saveExtent();

        Cart.getExtent().clear();
        assertEquals(0, Cart.getExtent().size());

        Cart.loadExtent();

        assertEquals(2, Cart.getExtent().size());

        assertNotSame(c1, Cart.getExtent().get(0));
        assertNotSame(c2, Cart.getExtent().get(1));
    }
}
