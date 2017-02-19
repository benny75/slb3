package com.slb.data.dao;

import com.slb.data.model.Ticks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mt4CsvDataLoadDao implements DataLoadDao {

    public Ticks load(int timeframe) {
        return null;
    }
}
