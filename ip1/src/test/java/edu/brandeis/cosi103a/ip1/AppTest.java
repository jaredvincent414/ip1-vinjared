package edu.brandeis.cosi103a.ip1;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import java.util.Random;

/**
 * Unit test for App.
 */
public class AppTest 
{
    /**
     * Test rollDie returns values between 1 and 6
     */
    @Test
    public void testRollDie() {
        Random random = new Random(42); // Fixed seed for deterministic test
        for (int i = 0; i < 100; i++) {
            int result = App.rollDie(random);
            assertTrue("Die roll should be between 1 and 6", result >= 1 && result <= 6);
        }
    }
    
    /**
     * Test determineWinner when player1 wins
     */
    @Test
    public void testDetermineWinnerPlayer1Wins() {
        String result = App.determineWinner("Alice", "Bob", 50, 40);
        assertEquals("Alice wins!", result);
    }
    
    /**
     * Test determineWinner when player2 wins
     */
    @Test
    public void testDetermineWinnerPlayer2Wins() {
        String result = App.determineWinner("Alice", "Bob", 40, 50);
        assertEquals("Bob wins!", result);
    }
    
    /**
     * Test determineWinner when it's a tie
     */
    @Test
    public void testDetermineWinnerTie() {
        String result = App.determineWinner("Alice", "Bob", 50, 50);
        assertEquals("It's a tie!", result);
    }
}
