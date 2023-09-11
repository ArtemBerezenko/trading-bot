package com.optimax.tradingbot;

import com.optimax.tradingbot.auction.Player;
import com.optimax.tradingbot.strategy.RandomBidStrategy;
import com.optimax.tradingbot.strategy.BidStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomBidStrategyTest {
    private BidStrategy strategy;
    private Player player;
    private Player other;

    @BeforeEach
    void setUp() {
        player = new Player(100, 1000);
        other = new Player(100, 1000);
        strategy = new RandomBidStrategy();
    }

    @Test
    void bidShouldBeBetweenZeroAndPlayerCashAmount() {
        int bid = strategy.placeBid(player, other);
        assertTrue(bid > 0 && bid <= player.getCash());
    }
}
