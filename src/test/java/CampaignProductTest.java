import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CampaignProductTest {
    @Test
    void testCampaignMustStartWithAtLeastOneProduct() {
        assertThrows(IllegalArgumentException.class,
                () -> new Campaign("Winter Sale", List.of()));
    }

    @Test
    void testCampaignConstructorCreatesReverseConnection() {
        Product p = new Product("Laptop", 1200);
        Campaign c = new Campaign("TechSale", List.of(p));

        assertEquals(c, p.getCampaign());
        assertTrue(c.getProducts().contains(p));
    }

    @Test
    void testReverseConnectionCreatedOnAdd() {
        Product p1 = new Product("Laptop", 1200);
        Product p2 = new Product("Mouse", 20);

        Campaign c = new Campaign("TechSale", List.of(p1));
        c.addProduct(p2);

        assertEquals(c, p2.getCampaign());
        assertTrue(c.getProducts().contains(p2));
    }

    @Test
    void testCannotAddNullProduct() {
        Product p = new Product("Phone", 800);
        Campaign c = new Campaign("Christmas", List.of(p));

        assertThrows(IllegalArgumentException.class, () -> c.addProduct(null));
    }

    @Test
    void testCannotAddDuplicateProduct() {
        Product p = new Product("TV", 500);
        Campaign c = new Campaign("Electronics", List.of(p));

        assertThrows(IllegalStateException.class, () -> c.addProduct(p));
    }

    @Test
    void testProductCannotBelongToTwoCampaigns() {
        Product p = new Product("Speaker", 200);
        Campaign c1 = new Campaign("MusicSale", List.of(p));
        Campaign c2 = new Campaign("BlackFriday", List.of(new Product("TV", 500)));

        assertThrows(IllegalStateException.class, () -> c2.addProduct(p));
    }

    @Test
    void testReverseConnectionRemovedOnRemove() {
        Product p1 = new Product("Laptop", 1200);
        Product p2 = new Product("Mouse", 20);

        Campaign c = new Campaign("TechSale", List.of(p1, p2));
        c.removeProduct(p1);

        assertNull(p1.getCampaign());
        assertFalse(c.getProducts().contains(p1));
    }

    @Test
    void testCannotRemoveProductNotInCampaign() {
        Product p1 = new Product("Laptop", 1200);
        Product p2 = new Product("Headphones", 80);

        Campaign c = new Campaign("TechSale", List.of(p1));

        assertThrows(IllegalArgumentException.class, () -> c.removeProduct(p2));
    }

    @Test
    void testCannotRemoveLastProductDueToMultiplicity() {
        Product p = new Product("Laptop", 1200);

        Campaign c = new Campaign("TechSale", List.of(p));

        assertThrows(IllegalStateException.class, () -> c.removeProduct(p));
    }

    @Test
    void testGetProductsReturnsUnmodifiableList() {
        Product p = new Product("Laptop", 1200);
        Campaign c = new Campaign("TechSale", List.of(p));

        List<Product> list = c.getProducts();

        assertThrows(UnsupportedOperationException.class, list::clear);

        assertEquals(1, c.getProducts().size());
        assertEquals(p, c.getProducts().get(0));
    }
    @Test
    void testProductConstructorValid() {
        Product p = new Product("Phone", 800);
        assertEquals("Phone", p.getTitle());
        assertEquals(800, p.getPrice());
    }

    @Test
    void testProductTitleCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Product("", 100));
        assertThrows(IllegalArgumentException.class, () -> new Product(null, 100));
    }

    @Test
    void testSetCampaignSetsCorrectly() {
        Product p = new Product("Phone", 800);
        Campaign c = new Campaign("Sale", List.of(p));

        assertEquals(c, p.getCampaign());
    }

    @Test
    void testRemoveCampaign() {
        Product p = new Product("Phone", 800);
        Campaign c = new Campaign("SpringSale", List.of(p));

        p.removeCampaign();

        assertNull(p.getCampaign());
    }
}