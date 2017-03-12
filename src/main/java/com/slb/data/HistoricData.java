package com.slb.data;

import com.slb.data.dao.DataLoadDao;
import com.slb.data.dao.Mt4CsvDataLoadDao;
import com.slb.data.model.Ticks;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jblas.DoubleMatrix;
import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EqualsAndHashCode
public class HistoricData {

//    @Autowired
    private DataLoadDao dataLoadDao = new Mt4CsvDataLoadDao();
    @Getter
    private List<Ticks> ticksList = new ArrayList<>();
    @Getter
    private double pipUnit;
    @Getter
    private int timeFrame;
    @Getter
    private DoubleMatrix ohlcv;
    private int size;

    public HistoricData(String instrumentName, double pipUnit, int timeframe) throws Throwable {
        this.pipUnit = pipUnit;
        this.timeFrame = timeframe;
        loadData(instrumentName, timeframe);
    }

    public HistoricData(String instrumentName, double pipUnit, int timeframe, DataLoadDao dao) throws Throwable {
        this.dataLoadDao = dao;
        this.pipUnit = pipUnit;
        this.timeFrame = timeframe;
        loadData(instrumentName, timeframe);
    }

    private void loadData(String instrumentName, int timeframe) throws Throwable {
        ticksList = dataLoadDao.load(instrumentName, timeframe);
        this.size = ticksList.size();
        double[][] ticksArray = ticksList.parallelStream().map(ticks -> ticks.toDoubleArray()).toArray(size -> new double[size][5]);
        ohlcv = new DoubleMatrix(ticksArray);
    }

    public int size(){
        return this.size;
    }

    public LocalDateTime getDatetime(int index) {
        return ticksList.get(index).getDatetime();
    }

    public double getClose(int index) {
        return ticksList.get(index).getClose();
    }

}
