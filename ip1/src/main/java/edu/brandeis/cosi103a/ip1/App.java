package edu.brandeis.cosi103a.ip1;

import java.util.Scanner;
import java.util.Random;

/**
 * Dice Game
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Welcome to the Dice Game!");
        String player1 = getPlayerName(scanner, 1);
        String player2 = getPlayerName(scanner, 2);
        
        int score1 = 0;
        int score2 = 0;
        
        for (int turn = 1; turn <= 10; turn++) {
            System.out.println("\n--- Turn " + turn + " ---");
            
            // Player 1's turn
            score1 += playTurn(scanner, random, player1);
            System.out.println(player1 + "'s total score: " + score1);
            
            // Player 2's turn
            score2 += playTurn(scanner, random, player2);
            System.out.println(player2 + "'s total score: " + score2);
        }
        
        printFinalScores(player1, player2, score1, score2);
        String winner = determineWinner(player1, player2, score1, score2);
        System.out.println(winner);
        
        scanner.close();
    }
    
    public static String getPlayerName(Scanner scanner, int num) {
        System.out.print("Enter name for Player " + num + ": ");
        return scanner.nextLine();
    }
    
    public static int playTurn(Scanner scanner, Random random, String playerName) {
        System.out.println(playerName + "'s turn:");
        int die = rollDie(random);
        System.out.println("Rolled: " + die);
        for (int reroll = 0; reroll < 2; reroll++) {
            System.out.print("Do you want to re-roll? (y/n): ");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("y") || choice.equals("yes")) {
                die = rollDie(random);
                System.out.println("Rolled: " + die);
            } else {
                break;
            }
        }
        return die;
    }
    
    public static void printFinalScores(String p1, String p2, int s1, int s2) {
        System.out.println("\n--- Final Scores ---");
        System.out.println(p1 + ": " + s1);
        System.out.println(p2 + ": " + s2);
    }
    
    public static String determineWinner(String p1, String p2, int s1, int s2) {
        if (s1 > s2) {
            return p1 + " wins!";
        } else if (s2 > s1) {
            return p2 + " wins!";
        } else {
            return "It's a tie!";
        }
    }
    
    public static int rollDie(Random random) {
        return random.nextInt(6) + 1;
    }
}
