package edu.brandeis.cosi103a.ip2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
//import java.util.List;

public class PlayerTest {
    private Player player;
    private Card bitcoinCard;
    private Card methodCard;

    @Before
    public void setUp() {
        player = new Player("TestPlayer");
        bitcoinCard = new Card("Bitcoin", 0, 0, 1);
        methodCard = new Card("Method", 2, 1, 0);
    }

    @Test
    public void testPlayerInitialization() {
        assertEquals("TestPlayer", player.getName());
        assertEquals(0, player.getDeckSize());
        assertEquals(0, player.getScore());
    }

    @Test
    public void testAddCardToDeck() {
        player.addCard(bitcoinCard);
        assertEquals(1, player.getDeckSize());
        player.addCard(methodCard);
        assertEquals(2, player.getDeckSize());
    }

    @Test
    public void testCalculateScore() {
        player.addCard(bitcoinCard);
        player.addCard(methodCard);
        player.addCard(new Card("Module", 5, 3, 0));
        assertEquals(4, player.getScore()); // 0 + 1 + 3 = 4
    }

    @Test
    public void testDrawHand() {
        player.addCard(bitcoinCard);
        player.addCard(methodCard);
        player.drawHand();
        assertEquals(2, player.getHand().size());
    }

    @Test
    public void testPlayCards() {
        player.addCard(bitcoinCard);
        player.addCard(methodCard);
        player.drawHand();
        
        player.playCards();
        assertEquals(1, player.getCoins()); // Bitcoin gives 1 coin
    }

    @Test
    public void testBuyCard() {
        player.addCard(bitcoinCard);
        player.drawHand();
        
        player.playCards();
        assertEquals(1, player.getCoins());
        
        Card method = new Card("Method", 2, 1, 0);
        player.buyCard(method);
        
        assertEquals(-1, player.getCoins()); // 1 - 2 = -1
        assertEquals(2, player.getDeckSize()); // bitcoin + method
    }

    @Test
    public void testEndTurn() {
        player.addCard(bitcoinCard);
        player.addCard(methodCard);
        player.drawHand();
        
        player.playCards();
        assertEquals(1, player.getCoins());
        
        player.endTurn();
        assertEquals(0, player.getCoins());
        assertEquals(0, player.getHand().size());
    }

    @Test
    public void testGetDeck() {
        player.addCard(bitcoinCard);
        player.addCard(methodCard);
        assertEquals(2, player.getDeckSize());
    }
}