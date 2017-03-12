package com.slb.indicator;

import com.slb.MockDao;
import com.slb.data.HistoricData;
import org.junit.Test;

import java.util.stream.DoubleStream;

import static junit.framework.TestCase.assertEquals;

public class IndicatorTest {
    @Test
    public void testGetSma() throws Throwable {
        HistoricData data = new HistoricData("MockData", 1, 1, new MockDao());
        double[] sma = Indicators.getSma(data, 5);
        assertEquals(50, sma.length);
        for(int i=0; i<50; i++){
            if(i<4){
                assertEquals((double)0, sma[i]);
            } else {
                assertEquals((double)i-1, sma[i]);
            }
        }
    }
}
