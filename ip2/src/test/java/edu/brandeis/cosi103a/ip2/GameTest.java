package edu.brandeis.cosi103a.ip2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game(100); // max 100 turns for testing
    }

    @Test
    public void testGameInitialization() {
        game.initialize();
        
        // Each player should have 10 cards (7 Bitcoin + 3 Method)
        assertEquals(10, game.getPlayer1().getDeckSize());
        assertEquals(10, game.getPlayer2().getDeckSize());
        
        // Each player should have 5 cards in hand
        assertEquals(5, game.getPlayer1().getHand().size());
        assertEquals(5, game.getPlayer2().getHand().size());
    }

    @Test
    public void testGameSupplyAfterInitialization() {
        game.initialize();
        
        // Check that cards were removed from supply
        assertEquals(46, game.getSupply().getCount("Bitcoin")); // 60 - 7 - 7 = 46
        assertEquals(8, game.getSupply().getCount("Method"));   // 14 - 3 - 3 = 8
    }

    @Test
    public void testGameEndsWhenFrameworksGone() {
        game.initialize();
        
        // Force end game condition by buying all but one Framework
        for (int i = 0; i < 7; i++) {
            game.getSupply().buyCard("Framework");
        }
        
        assertTrue(game.getSupply().getCount("Framework") > 0);
        
        // Play until game ends or max turns reached
        game.play();
        
        // Game should have ended with all frameworks gone
        assertTrue(game.getSupply().areAllFrameworksGone());
    }

    @Test
    public void testPlayFullGame() {
        game.play();
        
        assertTrue(game.getTurnCount() > 0);
        
        // Both players should have gained some cards
        assertTrue(game.getPlayer1().getDeckSize() >= 10);
        assertTrue(game.getPlayer2().getDeckSize() >= 10);
    }

    @Test
    public void testPlayerCanAcquireCards() {
        game.initialize();
        
        int p1InitialSize = game.getPlayer1().getDeckSize();
        int p2InitialSize = game.getPlayer2().getDeckSize();
        
        // Play game
        game.play();
        
        // At least one player should have acquired a card
        assertTrue(game.getPlayer1().getDeckSize() > p1InitialSize || 
                   game.getPlayer2().getDeckSize() > p2InitialSize);
    }

    @Test
    public void testGameEndsWithFrameworkCondition() {
        game.play();
        
        // Frameworks should be gone or max turns reached
        assertTrue(game.getSupply().areAllFrameworksGone() || game.getTurnCount() >= 100);
    }
}
