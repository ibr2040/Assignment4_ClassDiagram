import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CampaignProductTest {
    @Test
    void testCampaignMustStartWithAtLeastOneProduct() {
        assertThrows(IllegalArgumentException.class,
                () -> new Campaign("Winter Sale",1000, List.of()));
    }

    @Test
    void testCampaignConstructorCreatesReverseConnection() {
        Product p = new Product(
                "/images/laptop.png",
                1200,
                "Laptop",
                "Electronics",
                "Good laptop",
                true
        );
        Campaign c = new Campaign("TechSale", 10000,List.of(p));

        assertEquals(c, p.getCampaign());
        assertTrue(c.getProducts().contains(p));
    }

    @Test
    void testReverseConnectionCreatedOnAdd() {
        Product p1 = new Product(
                "/images/laptop.png",
                1200,
                "Laptop",
                "Electronics",
                "Powerful laptop",
                true
        );
        Product p2 = new Product(
                "/images/mouse.png",
                20,
                "Mouse",
                "Electronics",
                "Wireless mouse",
                true
        );

        Campaign c = new Campaign("TechSale", 10242,List.of(p1));
        c.addProduct(p2);

        assertEquals(c, p2.getCampaign());
        assertTrue(c.getProducts().contains(p2));
    }

    @Test
    void testCannotAddNullProduct() {
        Product p = new Product(
                "/images/phone.png",
                800,
                "Phone",
                "Electronics",
                "Smart phone",
                true
        );
        Campaign c = new Campaign("Christmas", 5000, List.of(p));
        assertThrows(IllegalArgumentException.class, () -> c.addProduct(null));
    }

    @Test
    void testCannotAddDuplicateProduct() {
        Product p = new Product(
                "/images/tv.png",
                500,
                "TV3",
                "Electronics",
                "Smart TV",
                true
        );
        Campaign c = new Campaign("Electronics",9000, List.of(p));

        assertThrows(IllegalStateException.class, () -> c.addProduct(p));
    }

    @Test
    void testProductCannotBelongToTwoCampaigns() {
        Product p = new Product(
                "/images/speaker.png",
                200,
                "Speaker",
                "Electronics",
                "Portable speaker",
                true
        );

        Campaign c1 = new Campaign("MusicSale", 5000, List.of(p));

        Product p2 = new Product(
                "/images/tv.png",
                500,
                "TV3",
                "Electronics",
                "Smart TV",
                true
        );

        Campaign c2 = new Campaign("BlackFriday", 7000, List.of(p2));

        assertThrows(IllegalStateException.class, () -> c2.addProduct(p));
    }

    @Test
    void testReverseConnectionRemovedOnRemove() {
        Product p1 = new Product(
                "/images/laptop.png",
                1200,
                "Laptop",
                "Electronics",
                "Powerful laptop",
                true
        );

        Product p2 = new Product(
                "/images/mouse.png",
                20,
                "Mouse",
                "Electronics",
                "Wireless mouse",
                true
        );

        Campaign c = new Campaign("TechSale", 10000, List.of(p1, p2));

        c.removeProduct(p1);

        assertNull(p1.getCampaign());
        assertFalse(c.getProducts().contains(p1));
    }

    @Test
    void testCannotRemoveProductNotInCampaign() {
        Product p1 = new Product(
                "/images/laptop.png",
                1200,
                "Laptop",
                "Electronics",
                "Powerful laptop",
                true
        );

        Product p2 = new Product(
                "/images/headphones.png",
                80,
                "Headphones",
                "Electronics",
                "Good sound",
                true
        );

        Campaign c = new Campaign("TechSale", 5000, List.of(p1));

        assertThrows(IllegalArgumentException.class, () -> c.removeProduct(p2));
    }

    @Test
    void testCannotRemoveLastProductDueToMultiplicity() {
        Product p = new Product(
                "/images/laptop.png",
                1200,
                "Laptop",
                "Electronics",
                "Good laptop",
                true
        );

        Campaign c = new Campaign("TechSale", 5000, List.of(p));

        assertThrows(IllegalStateException.class, () -> c.removeProduct(p));
    }

    @Test
    void testGetProductsReturnsUnmodifiableList() {
        Product p = new Product(
                "/images/laptop.png",
                1200,
                "Laptop",
                "Electronics",
                "Good laptop",
                true
        );

        Campaign c = new Campaign("TechSale", 5000, List.of(p));


        List<Product> list = c.getProducts();

        assertThrows(UnsupportedOperationException.class, list::clear);

        assertEquals(1, c.getProducts().size());
        assertEquals(p, c.getProducts().get(0));
    }
    @Test
    void testProductConstructorValid() {
        Product p = new Product(
                "/images/phone.png",
                800,
                "Phone",
                "Electronics",
                "Smartphone",
                true
        );

        assertEquals("Phone", p.getTitle());
        assertEquals(800, p.getPrice());
    }

    @Test
    void testProductTitleCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("/images/p.png", 100, "", "Electronics", "desc", true)
        );

        assertThrows(IllegalArgumentException.class, () ->
                new Product("/images/p.png", 100, null, "Electronics", "desc", true)
        );
    }

    @Test
    void testSetCampaignSetsCorrectly() {
        Product p = new Product(
                "/images/p1.png",
                800,
                "Phone",
                "Electronics",
                "desc",
                true
        );

        Campaign c = new Campaign("Sale", 5000, List.of(p));

        assertEquals(c, p.getCampaign());
    }

    @Test
    void testRemoveCampaign() {
        Product p = new Product(
                "/images/phone.png",
                800,
                "Phone",
                "Electronics",
                "Good phone",
                true
        );

        Campaign c = new Campaign("SpringSale", 10000, List.of(p));

        p.removeCampaign();

        assertNull(p.getCampaign());
    }
}