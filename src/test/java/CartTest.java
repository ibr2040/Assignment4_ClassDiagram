import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    public void testAddProductIncreaseQuantity(){
        Cart cart=new Cart();
        Product p=new Product("image",2455,"Iphone 15","Phones","Iphone",true);

        cart.addProduct(p);
        assertEquals(1,cart.getQuantityOfProducts());
    }

    @Test
    public void testAddNullProductThrowsException(){
        Cart cart=new Cart();
        assertThrows(IllegalArgumentException.class,()->cart.addProduct(null));
    }

    @Test
    public void testClearCart(){
        Cart cart=new Cart();
        cart.addProduct(new Product("image",2455,"Iphone 15","Phones","Iphone",true));

        cart.clear();
        assertEquals(0,cart.getQuantityOfProducts());
    }

    @Test
    public void testChangeQuantity(){
        Cart cart =new Cart();
        cart.addProduct(new Product("image",2455,"Iphone 15","Phones","Iphone",true));
        cart.addProduct(new Product("image",2453,"Iphone 13","Phones","Iphone",true));

        cart.changeQuantity(1);
        assertEquals(1,cart.getQuantityOfProducts());
    }

    @Test
    public void testChangeQuantityNegative(){
        Cart cart=new Cart();
        assertThrows(IllegalArgumentException.class,()->cart.changeQuantity(-1));
    }

    @Test
    public void testCartExtentSaveLoad() throws IOException, ClassNotFoundException {
        Cart.getExtent().clear();

        Cart c1=new Cart();
        Cart c2=new Cart();

        Cart.saveExtent();
        Cart.getExtent().clear();
        Cart.loadExtent();
        assertEquals(2,Cart.getExtent().size());
    }


}