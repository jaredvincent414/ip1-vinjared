package edu.brandeis.cosi103a.ip2;

import java.util.*;

/**
 * Manages the shared supply of cards available for purchase
 */
public class Supply {
    private final Map<Card, Integer> cards;

    public Supply() {
        this.cards = new LinkedHashMap<>();
        initializeSupply();
    }

    private void initializeSupply() {
        // Cryptocurrency cards - give money when played
        cards.put(new Card("Bitcoin", 0, 0, 1), 60);         // cost 0, value 1 coin
        cards.put(new Card("Ethereum", 3, 0, 2), 40);        // cost 3, value 2 coins
        cards.put(new Card("Dogecoin", 6, 0, 3), 30);        // cost 6, value 3 coins
        
        // Automation cards - give points at end of game
        cards.put(new Card("Method", 2, 1, 0), 14);          // cost 2, value 1 point
        cards.put(new Card("Module", 5, 3, 0), 8);           // cost 5, value 3 points
        cards.put(new Card("Framework", 8, 6, 0), 8);        // cost 8, value 6 points
    }

    public Card getCard(String name) {
        for (Card card : cards.keySet()) {
            if (card.getName().equals(name)) {
                return card;
            }
        }
        return null;
    }

    public Card buyCard(String cardName) {
        Card cardToBuy = getCard(cardName);
        if (cardToBuy != null && cards.get(cardToBuy) > 0) {
            cards.put(cardToBuy, cards.get(cardToBuy) - 1);
            return cardToBuy;
        }
        return null;
    }

    public boolean isAvailable(String cardName) {
        Card card = getCard(cardName);
        return card != null && cards.get(card) > 0;
    }

    public int getCount(String cardName) {
        Card card = getCard(cardName);
        return card != null ? cards.get(card) : 0;
    }

    public List<Card> getAvailableCards() {
        List<Card> available = new ArrayList<>();
        for (Map.Entry<Card, Integer> entry : cards.entrySet()) {
            if (entry.getValue() > 0) {
                available.add(entry.getKey());
            }
        }
        return available;
    }

    public boolean areAllFrameworksGone() {
        Card framework = getCard("Framework");
        return framework == null || cards.get(framework) == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Card, Integer> entry : cards.entrySet()) {
            if (entry.getValue() > 0) {
                sb.append(entry.getKey().getName()).append("(").append(entry.getValue()).append(") ");
            }
        }
        return sb.toString();
    }
}

