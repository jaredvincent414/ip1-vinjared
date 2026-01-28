package edu.brandeis.cosi103a.ip2;

/**
 * Represents a single card in the game
 */
public class Card {
    private final String name;
    private final int cost;
    private final int value;        // Automation points (for most cards)
    private final int coinValue;    // Coin value when played (0 if not a coin card)

    public Card(String name, int cost, int value, int coinValue) {
        this.name = name;
        this.cost = cost;
        this.value = value;
        this.coinValue = coinValue;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getValue() {
        return value;
    }

    public int getCoinValue() {
        return coinValue;
    }

    public boolean isCoinCard() {
        return coinValue > 0;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return name.equals(card.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

