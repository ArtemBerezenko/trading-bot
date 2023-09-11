package com.optimax.tradingbot.auction;

public class Player {
    private int quantity;
    private int cash;

    public Player(int quantity, int cash) {
        this.quantity = quantity;
        this.cash = cash;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCash() {
        return cash;
    }
    
    public void update(int quantity, int cash) {
        this.quantity += quantity;
        this.cash -= cash;
    }
}
