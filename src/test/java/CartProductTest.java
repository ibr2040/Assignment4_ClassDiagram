import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartProductTest {
    @Test
    public void TestAddProductToCart() {
        Cart cart = new Cart();
        Product p = new Product("asdf",12,"sdfa","asdf","sadff",true);
        ProductsQuantityCart q=new ProductsQuantityCart(2,p,cart);
        assertTrue(p.getCartsContainingProduct().contains(q));
        assertTrue(cart.getProductsInTheCart().contains(q));
    }
    @Test
    public void TestRemoveProductFromCart(){
        Cart cart = new Cart();
        Product p = new Product("asdf",12,"sdfa","asdf","sadff",true);
        ProductsQuantityCart q=new ProductsQuantityCart(2,p,cart);
        q.remove();
        assertFalse(p.getCartsContainingProduct().contains(q));
        assertFalse(cart.getProductsInTheCart().contains(q));
    }
    @Test
    public void TestAddCartFromProduct(){
        Cart cart = new Cart();
        Product p = new Product("asdf",12,"sdfa","asdf","sadff",true);
        ProductsQuantityCart q=new ProductsQuantityCart(2,p,cart);
        assertTrue(p.getCartsContainingProduct().contains(q));
        assertTrue(cart.getProductsInTheCart().contains(q));
    }
    @Test
    public void TestRemoveCartFromProduct(){
        Cart cart = new Cart();
        Product p = new Product("asdf",12,"sdfa","asdf","sadff",true);
        ProductsQuantityCart q=new ProductsQuantityCart(2,p,cart);
        p.removeFromCart(q);
        assertFalse(p.getCartsContainingProduct().contains(q));
        assertFalse(cart.getProductsInTheCart().contains(q));
    }
    @Test
    public void TestAddingAlreadyExistingProductInCart(){
        Cart cart = new Cart();
        Product p = new Product("asdf",12,"sdfa","asdf","sadff",true);
        Product p2 = p;
        ProductsQuantityCart q = new ProductsQuantityCart(2,p,cart);
        ProductsQuantityCart q1 =new ProductsQuantityCart(2,p,cart);
        assertEquals(p.getCartsContainingProduct().size(),1);
        assertEquals(cart.getProductsInTheCart().size(),1);
        assertEquals(q.getQuantity(),4);
    }
}
