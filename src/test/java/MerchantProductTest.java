import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerchantProductTest {

    @Test
    void testCreateProductCreatesReverseConnection() {
        User m12 = new User(
                "John", "rm@test.com", "+11443534531",
                "login", "password888",
                "PL61109010140000071219812874",
                "Street", "City"
        );
        m12.setMerchant("PL61109010140000071219812874");

        Product p = m12.getMerchant().createProduct(
                "/images/asdf.png",
                12,
                "sdfa",
                "Electronics",
                true
        );

        assertEquals(m12.getMerchant(), p.getMerchant());
        assertTrue(m12.getMerchant().getProducts().contains(p));
    }

    @Test
    void testMultipleProductsCanBeCreated() {
        User m = new User(
                "John", "rm@test.com", "+11443534531",
                "login", "password888",
                "PL61109010140000071219812874",
                "Street", "City"
        );
        m.setMerchant("PL61109010140000071219812874");

        Product p1 = m.getMerchant().createProduct("/images/Lapto3p.png", 1200,"T33","Electronics",true);
        Product p2 = m.getMerchant().createProduct("/images/Laptop1.png", 1300,"T122","Electronics",true);

        assertEquals(2, m.getMerchant().getProducts().size());
        assertEquals(m.getMerchant(), p1.getMerchant());
        assertEquals(m.getMerchant(), p2.getMerchant());
    }

    @Test
    void testCannotRemoveCompositionProductIndividually() {
        User m = new User(
                "John", "rm@test.com", "+11443534531",
                "login", "password888",
                "PL61109010140000071219812874",
                "Street", "City"
        );
        m.setMerchant("PL61109010140000071219812874");
        Product p = m.getMerchant().createProduct("/images/Laptop.png", 1200,"T332","Electronics",true);

        assertThrows(UnsupportedOperationException.class, () -> m.getMerchant().removeProduct(p));
    }

    @Test
    void testCannotRemoveNullProduct() {
        User m = new User(
                "John", "rm@test.com", "+11443534531",
                "login", "password888",
                "PL61109010140000071219812874",
                "Street", "City"
        );
        m.setMerchant("PL61109010140000071219812874");

        assertThrows(IllegalArgumentException.class, () -> m.getMerchant().removeProduct(null));
    }

    @Test
    void testCannotRemoveProductNotBelongingToMerchant() {
        User m1 = new User(
                "John", "rm@test.com", "+11443534531",
                "login", "password888",
                "PL61109010140000071219812874",
                "Street", "City"
        );
        m1.setMerchant("PL61109010140000071219812874");
        User m2 = new User(
                "Joh123n", "rm@te12st.com", "+11443534512331",
                "login", "passwor123d888",
                "PL6140000071219812874",
                "S123treet", "City"
        );
        m2.setMerchant("PL611090101400000774");

        Product p = m2.getMerchant().createProduct("/images/Laptop.png", 1200,"T22","Electronics",true);

        assertThrows(IllegalArgumentException.class, () -> m1.getMerchant().removeProduct(p));
    }

    @Test
    void testProductCannotExistWithoutMerchant() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("Laptop", 1200,"T","CAT","asdfasfd",true));
    }

    @Test
    void testMerchantBankAccountCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () ->{
            User m = new User(
                    "John", "rm@test.com", "+11443534531",
                    "login", "password888",
                    "PL61109010140000071219812874",
                    "Street", "City"
            );
            m.setMerchant(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            User m = new User(
                    "John", "rm@test.com", "+11443534531",
                    "login", "password888",
                    "PL61109010140000071219812874",
                    "Street", "City"
            );
            m.setMerchant("");
        });
    }


    @Test
    void testDeletingMerchantDeletesAllProducts() {
        User m = new User(
                "John", "rm@test.com", "+11443534531",
                "login", "password888",
                "PL61109010140000071219812874",
                "Street", "City"
        );
        m.setMerchant("PL61109010140000071219812874");
        Product p1 = m.getMerchant().createProduct("/images/Laptop.png", 1200,"T33","Electronics",true);
        Product p2 = m.getMerchant().createProduct("/images/Laptop.png", 1200,"33T","Electronics",true);

        m.getMerchant().deleteMerchant();

        assertNull(p1.getMerchant());
        assertNull(p2.getMerchant());
        assertTrue(m.getMerchant().getProducts().isEmpty());
    }
}
