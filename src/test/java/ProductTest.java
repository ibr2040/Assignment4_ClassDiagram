import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    public void testValidProductCreation() {
        Product p = new Product(
                "/images/img.png",
                50.0,
                "Laptop",
                "Electronics",
                "Gaming laptop",
                true
        );

        assertEquals("/images/img.png", p.getImage());
        assertEquals(50.0, p.getPrice());
        assertEquals("Laptop", p.getTitle());
        assertEquals("Electronics", p.getCategory());
        assertEquals("Gaming laptop", p.getDescription());
        assertTrue(p.getAvailability());
    }

    @Test
    public void testPriceCannotBeNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    "/images/x.png",
                    -10,
                    "Phone",
                    "Electronics",
                    "desc",
                    true
            );
        });
    }

    @Test
    public void testTitleCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    "/images/x.png",
                    10,
                    "",
                    "Electronics",
                    "desc",
                    true
            );
        });
    }

    @Test
    public void testImageMustBePngOrJpg() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    "/images/photo.gif",
                    20,
                    "Toy",
                    "Kids",
                    "desc",
                    true
            );
        });
    }

    @Test
    public void testCategoryMustBeAllowed() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    "/images/photo.png",
                    20,
                    "Toy",
                    "UnknownCategory",
                    "desc",
                    true
            );
        });
    }

    @Test
    public void testStaticAdvertisementFee() {
        Product.setAdvertismentFee(100.0);
        assertEquals(100.0, Product.getAdvertismentFee());
    }

    @Test
    public void testStaticAdvertisementFeeNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            Product.setAdvertismentFee(-100);
        });
    }

    @Test
    public void testTitleMustBeAtLeast3Chars() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    "/images/x.png",
                    50,
                    "AB",
                    "Electronics",
                    "desc",
                    true
            );
        });
    }

    @Test
    public void testPriceCannotBeZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    "/images/x.png",
                    0,
                    "Phone",
                    "Electronics",
                    "desc",
                    true
            );
        });
    }

    @Test
    public void testPriceCannotExceedMaximum() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    "/images/x.png",
                    200000,
                    "Phone",
                    "Electronics",
                    "desc",
                    true
            );
        });
    }
    @Test
    public void testImageCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    "",
                    10,
                    "Phone",
                    "Electronics",
                    "desc",
                    true
            );
        });
    }

    @Test
    public void testDescriptionTooLong() {
        String longDesc = "a".repeat(301);
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    "/images/x.png",
                    50,
                    "Phone",
                    "Electronics",
                    longDesc,
                    true
            );
        });
    }
    @Test
    public void testAvailabilityCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    "/images/x.png",
                    50,
                    "Phone",
                    "Electronics",
                    "desc",
                    null
            );
        });
    }

    @Test
    public void testProductExtentSaveLoad() throws Exception {
        Product.getExtent().clear();

        Product p = new Product(
                "/images/phone.png",
                120,
                "Phone",
                "Electronics",
                "desc",
                true
        );

        Product.saveExtent();

        Product.getExtent().clear();
        Product.loadExtent();

        assertEquals(1, Product.getExtent().size());
        assertEquals("Phone", Product.getExtent().get(0).getTitle());


        assertNotSame(p, Product.getExtent().get(0));
    }
}
