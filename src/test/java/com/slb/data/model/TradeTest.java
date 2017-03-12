package com.slb.data.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.apache.commons.math3.util.FastMath.round;

public class TradeTest {

    @Test
    public void testCalculateProfit_buy_noCommission(){
        Trade trade =  Trade.builder()
                .id(0)
                .openPrice(100)
                .closePrice(100.1)
                .quantity(10000)
                .position(Position.BUY)
                .build();
        trade.calculateProfit(0);
        assertEquals(round(trade.getProfit()), 10);
    }

    @Test
    public void testCalculateProfit_buy_withCommission(){
        Trade trade =  Trade.builder()
                .id(0)
                .openPrice(100)
                .closePrice(100.1)
                .quantity(10000)
                .position(Position.BUY)
                .build();
        trade.calculateProfit(.02);
        assertEquals(round(trade.getProfit()), 8);
    }

    @Test
    public void testCalculateProfit_sell_noCommission(){
        Trade trade =  Trade.builder()
                .id(0)
                .openPrice(100)
                .closePrice(100.1)
                .quantity(10000)
                .position(Position.SELL)
                .build();
        trade.calculateProfit(0);
        assertEquals(round(trade.getProfit()), -10);
    }

    @Test
    public void testCalculateProfit_sell_withCommission(){
        Trade trade =  Trade.builder()
                .id(0)
                .openPrice(100)
                .closePrice(100.1)
                .quantity(10000)
                .position(Position.SELL)
                .build();
        trade.calculateProfit(.02);
        assertEquals(round(trade.getProfit()), -12);
    }

    @Test
    public void testCalculateProfit_sell_withCommission2(){
        Trade trade =  Trade.builder()
                .id(0)
                .openPrice(100.5)
                .closePrice(100.1)
                .quantity(10000)
                .position(Position.SELL)
                .build();
        trade.calculateProfit(.02);
        assertEquals(round(trade.getProfit()), 38);
    }
}
