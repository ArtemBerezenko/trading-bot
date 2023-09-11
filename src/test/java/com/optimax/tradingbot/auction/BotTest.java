package com.optimax.tradingbot.auction;

import com.optimax.tradingbot.strategy.BidStrategy;
import com.optimax.tradingbot.strategy.RandomBidStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

class BotTest {
    private BotService botService;
    private int quantity;
    private int cash;
    
    @BeforeEach
    void setUp() {
        botService = new BotService();
        quantity = 100;
        cash = 1000;
    }

    @ParameterizedTest
    @MethodSource("getStrategies")
    void testStrategies(BidStrategy strategy) {
        Bot playerBot = new Bot(strategy, botService);
        Bot anotherBot = new Bot(new DummyStrategy(), botService);
        
        playerBot.init(quantity, cash);
        anotherBot.init(quantity, cash);
        
        for (int i = 0; i < quantity / 2; i++) {
            int playerBid = playerBot.placeBid();
            int anotherBid = anotherBot.placeBid();
            
            playerBot.bids(playerBid, anotherBid);
            anotherBot.bids(anotherBid, playerBid);
        }

        assertThat(playerBot.getOwn().getQuantity(), is(greaterThan(anotherBot.getOwn().getQuantity())));        
        
    }
    
    private static Stream<Arguments> getStrategies() {
        return Stream.of(
                Arguments.of(new RandomBidStrategy())
        );
    }

    
    public static class DummyStrategy implements BidStrategy {
        @Override
        public int placeBid(Player own, Player other) {
            return 0;
        }
    }
}
