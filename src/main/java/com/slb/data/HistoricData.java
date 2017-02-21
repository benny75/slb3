package com.slb.data;

import com.slb.data.dao.DataLoadDao;
import com.slb.data.dao.Mt4CsvDataLoadDao;
import com.slb.data.model.Ticks;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@EqualsAndHashCode
public abstract class HistoricData {

    @Autowired
    private DataLoadDao dataLoadDao = new Mt4CsvDataLoadDao();
    @Getter
    protected List<Ticks> ticks;
    @Getter
    protected double pipUnit;

    protected HistoricData(String instrumentName, int timeframe) throws Throwable {
        ticks = dataLoadDao.load(instrumentName, timeframe);
    }

}
