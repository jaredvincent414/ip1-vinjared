package edu.brandeis.cosi103a.ip2;

//import java.util.*;

/**
 * Simple card game controller
 */
public class Game {
    private final Player player1;
    private final Player player2;
    private final Supply supply;
    private Player currentPlayer;
    private int turnCount;
    private final int maxTurns;  // Safety limit to prevent infinite loops

    public Game(int maxTurns) {
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        this.supply = new Supply();
        this.turnCount = 0;
        this.maxTurns = maxTurns;
    }

    public void initialize() {
        // Give each player starting cards: 7 Bitcoin + 3 Method
        for (int i = 0; i < 7; i++) {
            Card bitcoin = supply.buyCard("Bitcoin");
            if (bitcoin != null) player1.addCard(bitcoin);
            bitcoin = supply.buyCard("Bitcoin");
            if (bitcoin != null) player2.addCard(bitcoin);
        }
        for (int i = 0; i < 3; i++) {
            Card method = supply.buyCard("Method");
            if (method != null) player1.addCard(method);
            method = supply.buyCard("Method");
            if (method != null) player2.addCard(method);
        }

        // Draw initial hands
        player1.drawHand();
        player2.drawHand();

        // Random starting player
        currentPlayer = Math.random() < 0.5 ? player1 : player2;
        
        System.out.println(currentPlayer.getName() + " goes first\n");
    }

    public void play() {
        initialize();
        
        while (turnCount < maxTurns && !supply.areAllFrameworksGone()) {
            playTurn();
        }
        
        endGame();
    }

    private void playTurn() {
        turnCount++;
        System.out.println("Turn " + turnCount + ": " + currentPlayer.getName());

        // Draw hand and play cards
        currentPlayer.drawHand();
        System.out.println("  Hand: " + currentPlayer.getHand());

        // Play all cryptocurrency cards
        currentPlayer.playCards();
        System.out.println("  Coins available: " + currentPlayer.getCoins());

        // Buy a card
        buyCard();

        // End turn and cleanup
        currentPlayer.endTurn();

        // Switch player
        currentPlayer = currentPlayer == player1 ? player2 : player1;
        System.out.println();
    }

    /**
     * Buy the best affordable card based on available coins
     */
    private void buyCard() {
        int coins = currentPlayer.getCoins();
        
        if (coins >= 8 && supply.isAvailable("Framework")) {
            Card card = supply.buyCard("Framework");
            currentPlayer.buyCard(card);
            System.out.println("  Bought: Framework");
        } else if (coins >= 6 && supply.isAvailable("Dogecoin")) {
            Card card = supply.buyCard("Dogecoin");
            currentPlayer.buyCard(card);
            System.out.println("  Bought: Dogecoin");
        } else if (coins >= 5 && supply.isAvailable("Module")) {
            Card card = supply.buyCard("Module");
            currentPlayer.buyCard(card);
            System.out.println("  Bought: Module");
        } else if (coins >= 3 && supply.isAvailable("Ethereum")) {
            Card card = supply.buyCard("Ethereum");
            currentPlayer.buyCard(card);
            System.out.println("  Bought: Ethereum");
        } else if (coins >= 2 && supply.isAvailable("Method")) {
            Card card = supply.buyCard("Method");
            currentPlayer.buyCard(card);
            System.out.println("  Bought: Method");
        } else if (coins >= 0 && supply.isAvailable("Bitcoin")) {
            Card card = supply.buyCard("Bitcoin");
            currentPlayer.buyCard(card);
            System.out.println("  Bought: Bitcoin");
        } else {
            System.out.println("  No card bought");
        }
    }

    private void endGame() {
        int p1Score = player1.getScore();
        int p2Score = player2.getScore();

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║           GAME OVER                    ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ " + String.format("%-36s", "Player 1: " + p1Score + " points") + "║");
        System.out.println("║ " + String.format("%-36s", "Player 2: " + p2Score + " points") + "║");
        System.out.println("╠════════════════════════════════════════╣");

        if (p1Score > p2Score) {
            System.out.println("║ WINNER: Player 1                       ║");
        } else if (p2Score > p1Score) {
            System.out.println("║ WINNER: Player 2                       ║");
        } else {
            System.out.println("║ TIE GAME!                              ║");
        }

        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("\nTotal turns played: " + turnCount);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Supply getSupply() {
        return supply;
    }

    public int getTurnCount() {
        return turnCount;
    }
}

