package edu.brandeis.cosi103a.ip2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class SupplyTest {
    private Supply supply;

    @Before
    public void setUp() {
        supply = new Supply();
    }

    @Test
    public void testSupplyInitialization() {
        assertEquals(60, supply.getCount("Bitcoin"));
        assertEquals(40, supply.getCount("Ethereum"));
        assertEquals(30, supply.getCount("Dogecoin"));
        assertEquals(14, supply.getCount("Method"));
        assertEquals(8, supply.getCount("Module"));
        assertEquals(8, supply.getCount("Framework"));
    }

    @Test
    public void testBuyCard() {
        assertNotNull(supply.buyCard("Bitcoin"));
        assertEquals(59, supply.getCount("Bitcoin"));
    }

    @Test
    public void testBuyCardOutOfStock() {
        // Buy all Bitcoins
        for (int i = 0; i < 60; i++) {
            supply.buyCard("Bitcoin");
        }
        assertNull(supply.buyCard("Bitcoin"));
        assertEquals(0, supply.getCount("Bitcoin"));
    }

    @Test
    public void testBuyNonexistentCard() {
        assertNull(supply.buyCard("NonexistentCard"));
    }

    @Test
    public void testIsAvailable() {
        assertTrue(supply.isAvailable("Bitcoin"));
        // Buy all bitcoins
        for (int i = 0; i < 60; i++) {
            supply.buyCard("Bitcoin");
        }
        assertFalse(supply.isAvailable("Bitcoin"));
    }

    @Test
    public void testGetCard() {
        Card bitcoin = supply.getCard("Bitcoin");
        assertNotNull(bitcoin);
        assertEquals("Bitcoin", bitcoin.getName());
    }

    @Test
    public void testGetNonexistentCard() {
        assertNull(supply.getCard("InvalidCard"));
    }

    @Test
    public void testAreAllFrameworksGone() {
        assertFalse(supply.areAllFrameworksGone());
        // Buy all Frameworks
        for (int i = 0; i < 8; i++) {
            supply.buyCard("Framework");
        }
        assertTrue(supply.areAllFrameworksGone());
    }

    @Test
    public void testGetAvailableCards() {
        List<Card> available = supply.getAvailableCards();
        assertEquals(6, available.size()); // 6 different card types
    }
}
