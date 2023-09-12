package com.optimax.tradingbot.strategy;

import com.optimax.tradingbot.auction.HistoryLog;
import com.optimax.tradingbot.auction.Player;

import java.util.List;

public class SmartBidStrategy implements BidStrategy {
    private final HistoryLog historyLog;
    private static final double BID_FACTOR = 1.2;
    
    public SmartBidStrategy() {
        this.historyLog = new HistoryLog();
    }

    @Override
    public int placeBid(Player own, Player other) {
        double rollingAverage = getAverage(getOtherCashHistory());
        double standardDeviation = getDeviation(getOtherCashHistory());

        int bid = (int) rollingAverage + (int) (standardDeviation * BID_FACTOR);

        logResults(own, other);

        if (bid > own.getCash()){
            return 0;
        }
        return bid;
    }

    private List<Integer> getOtherCashHistory() {
        return historyLog.getLogs().stream().filter(v -> v.player().equals("other")).map(HistoryLog.Log::cash).toList();
    }
    
    private double getAverage(List<Integer> list) {
        return list.stream().mapToDouble(Integer::doubleValue).sum() / list.size();
    }

    private double getDeviation(List<Integer> list) {
        double average = list.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);

        double difference = list.stream()
                .mapToDouble(v -> Math.pow(v - average, 2))
                .sum() / list.size();

        return Math.round(Math.sqrt(difference));
    }

    private void logResults(Player own, Player other) {
        historyLog.addLog(new HistoryLog.Log("own", own.getCash()));
        historyLog.addLog(new HistoryLog.Log("other", other.getCash()));
    }

}
