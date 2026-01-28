package edu.brandeis.cosi103a.ip2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {
    private Card automationCard;
    private Card cryptocurrencyCard;

    @Before
    public void setUp() {
        automationCard = new Card("Method", 2, 1, 0);      // name, cost, value, coinValue
        cryptocurrencyCard = new Card("Bitcoin", 0, 0, 1);
    }

    @Test
    public void testAutomationCardProperties() {
        assertEquals("Method", automationCard.getName());
        assertEquals(2, automationCard.getCost());
        assertEquals(1, automationCard.getValue());
        assertEquals(0, automationCard.getCoinValue());
    }

    @Test
    public void testCryptocurrencyCardProperties() {
        assertEquals("Bitcoin", cryptocurrencyCard.getName());
        assertEquals(0, cryptocurrencyCard.getCost());
        assertEquals(0, cryptocurrencyCard.getValue());
        assertEquals(1, cryptocurrencyCard.getCoinValue());
    }

    @Test
    public void testCardEquality() {
        Card card1 = new Card("Method", 2, 1, 0);
        Card card2 = new Card("Method", 2, 1, 0);
        assertEquals(card1, card2);
    }

    @Test
    public void testCardInequality() {
        Card method = new Card("Method", 2, 1, 0);
        Card module = new Card("Module", 5, 3, 0);
        assertNotEquals(method, module);
    }
}
