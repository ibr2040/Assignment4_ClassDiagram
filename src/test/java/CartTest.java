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
    public void testAddProductIncreaseQuantity() {
        Cart cart = new Cart();
        Product p = unavailableProduct("Iphone15");

        cart.addProduct(p);

        assertEquals(1, cart.getQuantityOfProducts());
    }

    @Test
    public void testAddNullProductThrowsException() {
        Cart cart = new Cart();
        assertThrows(IllegalArgumentException.class, () -> cart.addProduct(null));
    }

    @Test
    public void testAddAvailableProductThrowsException() {
        Cart cart = new Cart();

        Product availableProduct = new Product(
                "/images/photo.png",
                300,
                "Samsung",
                "Phones",
                "desc",
                true
        );

        assertThrows(IllegalArgumentException.class, () -> cart.addProduct(availableProduct));
    }

    @Test
    public void testAddDuplicateProductThrowsException() {
        Cart cart = new Cart();
        Product p = unavailableProduct("Iphone15");

        cart.addProduct(p);

        assertThrows(IllegalArgumentException.class, () -> cart.addProduct(p));
    }

    @Test
    public void testClearCart() {
        Cart cart = new Cart();
        cart.addProduct(unavailableProduct("Iphone15"));

        cart.clear();

        assertEquals(0, cart.getQuantityOfProducts());
    }

    @Test
    public void testChangeQuantityDecrease() {
        Cart cart = new Cart();
        cart.addProduct(unavailableProduct("Iphone15"));
        cart.addProduct(unavailableProduct("Iphone13"));

        cart.changeQuantity(1);

        assertEquals(1, cart.getQuantityOfProducts());
    }

    @Test
    public void testChangeQuantityNegative() {
        Cart cart = new Cart();
        assertThrows(IllegalArgumentException.class, () -> cart.changeQuantity(-1));
    }

    @Test
    public void testChangeQuantityAboveCurrentThrowsException() {
        Cart cart = new Cart();
        cart.addProduct(unavailableProduct("Iphone15"));

        assertThrows(IllegalArgumentException.class, () -> cart.changeQuantity(-5));
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
