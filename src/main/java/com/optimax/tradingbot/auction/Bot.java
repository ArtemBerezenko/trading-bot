package com.optimax.tradingbot.auction;

import com.optimax.tradingbot.strategy.BidStrategy;

public class Bot implements Bidder {
    private Player own;
    private Player other;
    private final BidStrategy strategy;
    private final BotService botService;

    public Bot(BidStrategy strategy, BotService botService) {
        this.strategy = strategy;
        this.botService = botService;
    }

    public Player getOwn() {
        return own;
    }

    public Player getOther() {
        return other;
    }

    @Override
    public void init(int quantity, int cash) {
        this.own = new Player(quantity, cash);
        this.other = new Player(quantity, cash);
    }

    @Override
    public int placeBid() {
        return strategy.placeBid(own, other);
    }

    @Override
    public void bids(int own, int other) {
        botService.doBids(own, other, this.own, this.other);
    }
}
