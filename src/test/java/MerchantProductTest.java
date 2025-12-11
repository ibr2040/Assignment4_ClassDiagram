import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerchantProductTest {

    @Test
    void testCreateProductCreatesReverseConnection() {
        Merchant m = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf","7585256","asdfasdf","sdaffasdf","sadf");
        Product p = m.createProduct("Laptop", 1200,"T","CAT",true);

        assertEquals(m, p.getMerchant());
        assertTrue(m.getProducts().contains(p));
    }

    @Test
    void testMultipleProductsCanBeCreated() {
        Merchant m = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf","7585256","asdfasdf","sdaffasdf","sadf");

        Product p1 = m.createProduct("Laptop", 1200,"T","CAT",true);
        Product p2 = m.createProduct("Laptop1", 1300,"T1","CAT1",true);

        assertEquals(2, m.getProducts().size());
        assertEquals(m, p1.getMerchant());
        assertEquals(m, p2.getMerchant());
    }

    @Test
    void testAddExistingProductCreatesReverseConnection() {
        Merchant m = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf","7585256","asdfasdf","sdaffasdf","sadf");

        Product p = m.createProduct("Laptop", 1200,"T","CAT",true);

        m.addExistingProduct(p);

        assertEquals(m, p.getMerchant());
        assertTrue(m.getProducts().contains(p));
    }

    @Test
    void testCannotAddExistingProductThatAlreadyBelongsToMerchant() {
        Merchant m = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf","7585256","asdfasdf","sdaffasdf","sadf");

        Product p = m.createProduct("Laptop", 1200,"T","CAT",true);

        assertThrows(IllegalStateException.class, () -> m.addExistingProduct(p));
    }


    @Test
    void testCannotAddExistingProductBelongingToAnotherMerchant() {
        Merchant m1 = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf","7585256","asdfasdf","sdaffasdf","sadf");
        Merchant m2 = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf","7585256","asdfasdf","sdaffasdf","sadf");

        Product p = m1.createProduct("Laptop", 1200,"T","CAT",true);

        assertThrows(IllegalStateException.class, () -> m2.addExistingProduct(p));
    }

    @Test
    void testCannotAddNullProduct() {
        Merchant m = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf","7585256","asdfasdf","sdaffasdf","sadf");

        assertThrows(IllegalArgumentException.class, () -> m.addExistingProduct(null));
    }

    @Test
    void testCannotRemoveCompositionProductIndividually() {
        Merchant m = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf","7585256","asdfasdf","sdaffasdf","sadf");
        Product p = m.createProduct("Laptop", 1200,"T","CAT",true);

        assertThrows(UnsupportedOperationException.class, () -> m.removeProduct(p));
    }

    @Test
    void testCannotRemoveNullProduct() {
        Merchant m = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf","7585256","asdfasdf","sdaffasdf","sadf");

        assertThrows(IllegalArgumentException.class, () -> m.removeProduct(null));
    }

    @Test
    void testCannotRemoveProductNotBelongingToMerchant() {
        Merchant m1 = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf","7585256","asdfasdf","sdaffasdf","sadf");
        Merchant m2 = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf","7585256","asdfasdf","sdaffasdf","sadf");

        Product p = m2.createProduct("Laptop", 1200,"T","CAT",true);

        assertThrows(IllegalArgumentException.class, () -> m1.removeProduct(p));
    }

    @Test
    void testProductCannotExistWithoutMerchant() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("Laptop", 1200,"T","CAT","asdfasfd",true));
    }

    @Test
    void testMerchantBankAccountCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf","","asdfasdf","sdaffasdf","sadf"));
        assertThrows(IllegalArgumentException.class, () -> new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf",null,"asdfasdf","sdaffasdf","sadf"));
    }

    @Test
    void testGetProductsReturnsUnmodifiableList() {
        Merchant m = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf","7585256","asdfasdf","sdaffasdf","sadf");
        m.createProduct("Laptop", 1200,"T","CAT",true);

        var list = m.getProducts();

        assertThrows(UnsupportedOperationException.class, list::clear);
    }


    @Test
    void testDeletingMerchantDeletesAllProducts() {
        Merchant m = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf","7585256","asdfasdf","sdaffasdf","sadf");
        Product p1 = m.createProduct("Laptop", 1200,"T","CAT",true);
        Product p2 = m.createProduct("Laptop", 1200,"T","CAT",true);

        m.deleteMerchant();

        assertNull(p1.getMerchant());
        assertNull(p2.getMerchant());
        assertTrue(m.getProducts().isEmpty());
    }
}
