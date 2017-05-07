package com.slb.strategy;

import com.google.common.annotations.VisibleForTesting;
import com.slb.data.HistoricData;
import com.slb.data.model.Position;
import com.slb.data.model.Trade;
import com.slb.indicator.Indicators;
import com.slb.trade.TradeTable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.jblas.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.slb.data.model.Position.NO_MOVE;

public class RandomFxScalper implements Strategy {

    private static final Logger logger = LoggerFactory.getLogger(RandomFxScalper.class);

    @Getter
    @Setter
    public final int maxHoldPeriod = 5;
    @Getter
    @Setter
    public final int tpPip = 10;

    private int[] openIndex;

    private Random generator = new Random();

    @Override
    public void checkClose(final HistoricData data, final List<Trade> tradeTable, final int index) {
        if(tradeTable.isEmpty()) {
            return;
        }
        double currentPrice = data.getClose(index);

        tradeTable.parallelStream()
                .filter(trade -> trade.getCloseIndex() == 0)
                .filter(trade ->
                        index - trade.getOpenIndex() >= maxHoldPeriod ||
                        (data.getClose(trade.getOpenIndex()) - currentPrice)
                                * trade.getPosition().getValue() > tpPip * data.getPipUnit())
                .forEach(trade -> trade.setCloseIndex(index));
    }

    @Override
    public Position checkOpen(final HistoricData data, final List<Trade> tradeTable, final int index) {
        if(index > data.size() - maxHoldPeriod) {
            return NO_MOVE;
        }

        if(openIndex[index] != 0){
            TradeTable.addTrade(index, Position.getByValue(this.openIndex[index]), 10000);
        }
        return Position.getByValue(openIndex[index]);
    }

    @Override
    public void init(HistoricData data) {
        double[] sma = Indicators.getSma(data, 25);


        openIndex = new int[data.size()];
        openIndex = Arrays.stream(openIndex).parallel().map(element ->
                generator.nextInt(3) - 1)
                .toArray();
    }

}
