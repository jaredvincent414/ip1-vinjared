package edu.brandeis.cosi103a.ip2;

/**
 * Card Game - Deck Building Game
 * Players compete to build the most valuable deck
 */
public class App {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║        DOMINO'S CARD GAME              ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();

        // Play a game with 200 turn safety limit (game should end when all Framework cards bought)
        Game game = new Game(200);
        game.play();
    }
}
