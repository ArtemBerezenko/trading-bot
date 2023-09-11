package com.optimax.tradingbot.auction;

import com.optimax.tradingbot.strategy.BidStrategy;

public class Bot implements Bidder {
    private Player own;
    private Player other;
    private BidStrategy strategy;
    private BotService botService;

    public Bot(BidStrategy strategy, BotService botService) {
        this.strategy = strategy;
        this.botService = botService;
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
    public void bids(int ownCash, int otherCash) {
        botService.doBids(ownCash, otherCash, own, other);
    }
}
