package com.optimax.tradingbot.auction;

import java.util.ArrayList;
import java.util.List;

public class HistoryLog {
    private final List<Log> logs = new ArrayList<>();

    public List<Log> getLogs() {
        return logs;
    }
    
    public void addLog(Log log) {
        logs.add(log);
    }

    public record Log(String player, int cash){}
}
