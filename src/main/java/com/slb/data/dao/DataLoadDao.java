package com.slb.data.dao;

import com.slb.data.model.Ticks;

public interface DataLoadDao {

    Ticks load(String insturment, int timeframe) throws Throwable;


}
