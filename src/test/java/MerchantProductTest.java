import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerchantProductTest {

    @Test
    void testCreateProductCreatesReverseConnection() {
        Merchant m = new Merchant(
                "12345","asdfasdf@gmail.com","+34562345",
                "asdfsadfas","sasdsssddfasdf","PL437585256",
                "asdfasdf","sdaffasdf","sadf"
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
        Merchant m = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sasdsssddfasdf","PL437585256","asdfasdf","sdaffasdf","sadf");

        Product p1 = m.createProduct("/images/Lapto3p.png", 1200,"T33","Electronics",true);
        Product p2 = m.createProduct("/images/Laptop1.png", 1300,"T122","Electronics",true);

        assertEquals(2, m.getProducts().size());
        assertEquals(m, p1.getMerchant());
        assertEquals(m, p2.getMerchant());
    }

    @Test
    void testCannotRemoveCompositionProductIndividually() {
        Merchant m = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfdadsadsaasdf","PL337585256","asdfasdf","sdaffasdf","sadf");
        Product p = m.createProduct("/images/Laptop.png", 1200,"T332","Electronics",true);

        assertThrows(UnsupportedOperationException.class, () -> m.removeProduct(p));
    }

    @Test
    void testCannotRemoveNullProduct() {
        Merchant m = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfadsdsdf","PL327585256","asdfasdf","sdaffasdf","sadf");

        assertThrows(IllegalArgumentException.class, () -> m.removeProduct(null));
    }

    @Test
    void testCannotRemoveProductNotBelongingToMerchant() {
        Merchant m1 = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfaDSDSDsdf","PL7585256","asdfasdf","sdaffasdf","sadf");
        Merchant m2 = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfDSSDSasdf","PL7585256","asdfasdf","sdaffasdf","sadf");

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
        assertThrows(IllegalArgumentException.class, () -> new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf","","asdfasdf","sdaffasdf","sadf"));
        assertThrows(IllegalArgumentException.class, () -> new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfasdf",null,"asdfasdf","sdaffasdf","sadf"));
    }


    @Test
    void testDeletingMerchantDeletesAllProducts() {
        Merchant m = new Merchant("12345","asdfasdf@gmail.com","+34562345","asdfsadfas","sadfaddsdsasdsdf","PL327585256","asdfasdf","sdaffasdf","sadf");
        Product p1 = m.createProduct("/images/Laptop.png", 1200,"T33","Electronics",true);
        Product p2 = m.createProduct("/images/Laptop.png", 1200,"33T","Electronics",true);

        m.deleteMerchant();

        assertNull(p1.getMerchant());
        assertNull(p2.getMerchant());
        assertTrue(m.getProducts().isEmpty());
    }
}
