import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerchantProductTest {
    private User createUser() {
        return new User(
                "John Doe",
                "asd@gmail.com",
                "+34124345353433",
                "login",
                "password323424234",
                "street",
                "city",
                "state"
        );
    }

    @Test
    void testCreateProductCreatesReverseConnection() {
        Merchant m = new Merchant(
                createUser(),"PL9498893748294"
        );

        Product p = m.createProduct(
                "/images/asdf.png",
                12,
                "sdfa",
                "Electronics",
                true
        );

        assertEquals(m, p.getMerchant());
        assertTrue(m.getProducts().contains(p));
    }

    @Test
    void testMultipleProductsCanBeCreated() {
        Merchant m = new Merchant(createUser(),"PL437585256");

        Product p1 = m.createProduct("/images/Lapto3p.png", 1200,"T33","Electronics",true);
        Product p2 = m.createProduct("/images/Laptop1.png", 1300,"T122","Electronics",true);

        assertEquals(2, m.getProducts().size());
        assertEquals(m, p1.getMerchant());
        assertEquals(m, p2.getMerchant());
    }

    @Test
    void testAddExistingProductCreatesReverseConnection() {
        Merchant m = new Merchant(createUser(),"PL3437585256");

        Product p = new Product("/images/asdf.png",12,"sdfa","Electronics","sadff",true);

        m.addExistingProduct(p);

        assertEquals(m, p.getMerchant());
        assertTrue(m.getProducts().contains(p));
    }

    @Test
    void testCannotAddExistingProductThatAlreadyBelongsToMerchant() {
        Merchant m = new Merchant(
                createUser(),
                "PL3437585256"
        );

        Product p = new Product("/images/asdf.png",12,"sdfa","Electronics",true, m);

        assertThrows(IllegalStateException.class, () -> m.addExistingProduct(p));
    }


    @Test
    void testCannotAddExistingProductBelongingToAnotherMerchant() {
        Merchant m1 = new Merchant(createUser(),"PL43437585256");
        Merchant m2 = new Merchant(createUser(),"PL43432427585256");

        Product p = m1.createProduct("/images/Laptop.png", 1200,"T33","Electronics",true);

        assertThrows(IllegalStateException.class, () -> m2.addExistingProduct(p));
    }

    @Test
    void testCannotAddNullProduct() {
        Merchant m = new Merchant(createUser(),"PL43432427585256");

        assertThrows(IllegalArgumentException.class, () -> m.addExistingProduct(null));
    }

    @Test
    void testCannotRemoveCompositionProductIndividually() {
        Merchant m = new Merchant(createUser(),"PL337585256");
        Product p = m.createProduct("/images/Laptop.png", 1200,"T332","Electronics",true);

        assertThrows(UnsupportedOperationException.class, () -> m.removeProduct(p));
    }

    @Test
    void testCannotRemoveNullProduct() {
        Merchant m = new Merchant(createUser(),"PL327585256");

        assertThrows(IllegalArgumentException.class, () -> m.removeProduct(null));
    }

    @Test
    void testCannotRemoveProductNotBelongingToMerchant() {
        Merchant m1 = new Merchant(createUser(),"PL7585256");
        Merchant m2 = new Merchant(createUser(),"PL7585256");

        Product p = m2.createProduct("/images/Laptop.png", 1200,"T22","Electronics",true);

        assertThrows(IllegalArgumentException.class, () -> m1.removeProduct(p));
    }

    @Test
    void testProductCannotExistWithoutMerchant() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("Laptop", 1200,"T","CAT","asdfasfd",true));
    }

    @Test
    void testMerchantBankAccountCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Merchant(createUser(),"4234982749247892"));
        assertThrows(IllegalArgumentException.class, () -> new Merchant(createUser(),"4234982749247892"));
    }



    @Test
    void testDeletingMerchantDeletesAllProducts() {
        Merchant m = new Merchant(createUser(),"PL327585256");
        Product p1 = m.createProduct("/images/Laptop.png", 1200,"T33","Electronics",true);
        Product p2 = m.createProduct("/images/Laptop.png", 1200,"33T","Electronics",true);

        m.deleteMerchant();

        assertNull(p1.getMerchant());
        assertNull(p2.getMerchant());
        assertTrue(m.getProducts().isEmpty());
    }
}
