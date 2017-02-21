package com.slb.backtest;

import com.slb.data.HistoricData;
import com.slb.strategy.Strategy;

public class Backtest {

    public Backtest(HistoricData data, Strategy strategy){
        data.getTicks();
    }


}
