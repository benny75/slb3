package com.slb.data;

import lombok.Data;

@Data
public class UsdJpy extends HistoricData {

    public UsdJpy(int timeframe){
        super(timeframe);
        pipUnit = 0.01;
    }

}
