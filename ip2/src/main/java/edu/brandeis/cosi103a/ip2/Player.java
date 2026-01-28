package edu.brandeis.cosi103a.ip2;

import java.util.*;

/**
 * Represents a player in the game
 */
public class Player {
    private final String name;
    private final List<Card> deck;
    private final List<Card> hand;
    private final List<Card> discard;
    private int coins;

    public Player(String name) {
        this.name = name;
        this.deck = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.discard = new ArrayList<>();
        this.coins = 0;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public void drawHand() {
        hand.clear();
        coins = 0;
        
        // Draw 5 cards
        for (int i = 0; i < 5; i++) {
            if (deck.isEmpty()) {
                // Reshuffle discard into deck
                deck.addAll(discard);
                discard.clear();
                if (deck.isEmpty()) break;
                Collections.shuffle(deck);
            }
            if (!deck.isEmpty()) {
                hand.add(deck.remove(0));
            }
        }
    }

    public void playCards() {
        // Play all cryptocurrency cards and gain coins
        coins = 0;
        for (Card card : hand) {
            if (card.getCoinValue() > 0) {
                coins += card.getCoinValue();
            }
        }
    }

    public int getCoins() {
        return coins;
    }

    public List<Card> getHand() {
        return new ArrayList<>(hand);
    }

    public void buyCard(Card card) {
        discard.add(card);
        coins -= card.getCost();
    }

    public void endTurn() {
        discard.addAll(hand);
        hand.clear();
        coins = 0;
    }

    public int getScore() {
        int score = 0;
        for (Card card : deck) {
            score += card.getValue();
        }
        for (Card card : hand) {
            score += card.getValue();
        }
        for (Card card : discard) {
            score += card.getValue();
        }
        return score;
    }

    public int getDeckSize() {
        return deck.size() + hand.size() + discard.size();
    }

    @Override
    public String toString() {
        return name + " (Score: " + getScore() + ")";
    }
}

