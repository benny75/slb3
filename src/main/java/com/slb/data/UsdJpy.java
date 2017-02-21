package com.slb.data;

import lombok.Data;

@Data
public class UsdJpy extends HistoricData {

    private static final String INSTRUMENT_NAME = "USDJPY";

    public UsdJpy(int timeframe) throws Throwable {
        super(INSTRUMENT_NAME, timeframe);
        pipUnit = 0.01;
    }

}
