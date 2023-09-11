package com.optimax.tradingbot.strategy;

import com.optimax.tradingbot.auction.Player;

public class RandomBidStrategy implements BidStrategy {

    @Override
    public int placeBid(Player own, Player other) {
        return getRandomNumber(0, own.getCash());
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
