package com.slb.backtest;

import com.slb.data.HistoricData;
import com.slb.data.model.Trade;
import com.slb.strategy.Strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Backtest {

    private static final Logger logger = LoggerFactory.getLogger(Backtest.class);
    private static final double COMMISSION = 0.02;

    public Backtest(HistoricData data, Strategy strategy){
        List<Trade> tradeTable = new ArrayList<>();
        strategy.init(data);

        for(int i=50; i<data.size(); i++) {
            strategy.checkClose(data, tradeTable, i);
            strategy.checkOpen(data, tradeTable, i);
        }

        closeAllTrades(tradeTable, data.size()-1);
        tradeTable = fillTradeTable(data, tradeTable);
        printReport(tradeTable);

    }

    private List<Trade> fillTradeTable(final HistoricData data, List<Trade> tradeTable){
        return tradeTable.parallelStream().map(trade -> fillTradeDetails(data, trade)).collect(Collectors.toList());
    }

    private Trade fillTradeDetails(final HistoricData data, Trade trade){
        trade.setOpenDatetime(data.getDatetime(trade.getOpenIndex()));
        trade.setOpenPrice(data.getClose(trade.getOpenIndex()));
        trade.setCloseDatetime(data.getDatetime(trade.getCloseIndex()));
        trade.setClosePrice(data.getClose(trade.getCloseIndex()));
        trade.calculateProfit(COMMISSION);
        return trade;
    }

    private void closeAllTrades(List<Trade> tradeTable, int lastIndex) {
        tradeTable.parallelStream()
                .filter(trade -> trade.getCloseIndex() == 0)
                .forEach(trade -> trade.setCloseIndex(lastIndex));
    }

    private void printReport(List<Trade> tradeTable) {
        double totalProfit=0;
        List<Double> profiltHistory = new ArrayList<>();
        for(Trade trade : tradeTable){
            totalProfit += trade.getProfit();
            logger.info("profit: {}", totalProfit);
            profiltHistory.add(totalProfit);

        }

        double profit = tradeTable.parallelStream().mapToDouble(trade -> trade.getProfit()).sum();
        logger.info("Total trade : {}, total profit: {}", tradeTable.size(), profit);
    }
}
