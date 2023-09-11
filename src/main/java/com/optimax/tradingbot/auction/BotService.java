package com.optimax.tradingbot.auction;

public class BotService {
    public static final int WIN_QU = 2;
    public static final int TIE_QU = 1;
    public static final int LOSS_QU = 0;

    public void doBids(int playerCash, int otherCash, Player player, Player another) {
        if (playerCash < 0 || otherCash < 0) {
            throw new IllegalArgumentException("Bids cannot be negative");
        }

        int playerReword = LOSS_QU;
        int otherReword = LOSS_QU;

        if (playerCash == otherCash) {
            playerReword = TIE_QU;
            otherReword = TIE_QU;
        }

        if (playerCash > otherCash) {
            playerReword = WIN_QU;
        } else {
            otherReword = WIN_QU;
        }

        player.update(playerReword, playerCash);
        another.update(otherReword, otherCash);
    }
}
