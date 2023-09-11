package com.optimax.tradingbot.strategy;

import com.optimax.tradingbot.auction.Player;

public interface BidStrategy {
    int placeBid(Player own, Player other);
}