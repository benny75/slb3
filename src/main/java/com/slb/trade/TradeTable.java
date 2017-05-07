package com.slb.trade;

import com.slb.data.model.Position;
import com.slb.data.model.Trade;

import java.util.List;

public class TradeTable {

    private static List<Trade> trades;



    public static void addTrade(int openIndex, Position position, int quantity) {
        trades.add(Trade.builder()
                .openIndex(openIndex)
                .position(position)
                .quantity(quantity)
                .build());
    }


}
