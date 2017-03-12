package com.slb.strategy;

import com.slb.data.HistoricData;
import com.slb.data.model.Position;
import com.slb.data.model.Trade;

import java.util.List;

public interface Strategy {

    void checkClose(final HistoricData data, final List<Trade> tradeTable, final int index);
    Position checkOpen(final HistoricData data, final List<Trade> tradeTable, final int index);

    void init(HistoricData data);
}
