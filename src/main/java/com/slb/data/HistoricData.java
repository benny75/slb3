package com.slb.data;

import com.slb.data.dao.DataLoadDao;
import com.slb.data.model.Ticks;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class HistoricData {

    private DataLoadDao dataLoadDao;
    protected Ticks ticks;
    protected double pipUnit;

    public HistoricData(int timeframe) {
        dataLoadDao.load(timeframe);
    }

}
