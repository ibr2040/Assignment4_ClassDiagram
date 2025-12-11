import java.util.ArrayList;
import java.util.List;

public class ProductsQuantityCart {
    private static List<ProductsQuantityCart> productsQuantityCartList = new ArrayList<ProductsQuantityCart>();
   private int quantity;
    private Product product;
    private Cart cart;
    ProductsQuantityCart(int quantity, Product product, Cart cart) {
        if(quantity <= 0||product == null||cart == null){throw new IndexOutOfBoundsException("Incorrect arguments");}
        ProductsQuantityCart pqc = this.checkDublicates(cart,product);
        if(!(pqc == null)){
            pqc.quantity = pqc.quantity+quantity;
            return;
        }
        this.quantity = quantity;
        this.product = product;
        this.cart = cart;
        cart.addProduct(this);
        product.addToCart(this);
        this.productsQuantityCartList.add(this);
    }
    public void remove(){
        this.product.removeFromCart(this);
        this.cart.removeProduct(this);
    }
    private ProductsQuantityCart checkDublicates(Cart c,Product p){
        for (ProductsQuantityCart pqc : productsQuantityCartList){
            if(pqc.product.equals(p)&&pqc.cart.equals(c)){
                return pqc;
            }
        }
        return null;
    }

    public int getQuantity() {
        return quantity;
    }
}
