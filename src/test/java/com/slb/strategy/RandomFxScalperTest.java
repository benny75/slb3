package com.slb.strategy;

import com.slb.data.HistoricData;
import com.slb.data.model.Position;
import com.slb.data.model.Trade;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RandomFxScalperTest {

    private HistoricData data;
    private RandomFxScalper strategy;
    private List<Trade> tradeTable;

    @Before
    public void setup() throws  Exception{
        try {
            data = new HistoricData("USDJPY", 0.01, 60);
            tradeTable = new ArrayList<>();
            strategy = new RandomFxScalper();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testInit(){
        strategy.init(data);
    }

    @Test
    public void testCheckClose_shouldNotClose(){
        int index = 200;
        tradeTable.add(0, Trade.builder().id(0).openIndex(index - strategy.getMaxHoldPeriod()+1).position(Position.BUY).build());
        strategy.checkClose(data, tradeTable, index);
        assertEquals(tradeTable.size(), 1);
        assertEquals(tradeTable.get(0).getCloseIndex(),0);
    }

    @Test
    public void testCheckClose_shouldCloseOne(){
        int index = 200;
        tradeTable.add(0, Trade.builder().id(0).openIndex(index - strategy.getMaxHoldPeriod()).position(Position.BUY).build());
        strategy.checkClose(data, tradeTable, index);
        assertEquals(tradeTable.size(), 1);
        assertNotEquals(tradeTable.get(0).getCloseIndex(),0);
    }


}
