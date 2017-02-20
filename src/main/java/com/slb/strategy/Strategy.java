package com.slb.strategy;

import com.slb.data.HistoricData;
import com.slb.data.model.Trade;

import java.util.List;

public interface Strategy {

    List<Integer> generateOpenIndex(HistoricData data);
    List<Integer> generateCloseIndex(HistoricData data, List<Integer> openIndex);
    List<Trade> generateTrades(HistoricData data);

}
