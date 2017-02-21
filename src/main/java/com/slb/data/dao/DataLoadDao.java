package com.slb.data.dao;

import com.slb.data.model.Ticks;

import java.util.List;

public interface DataLoadDao {

    List<Ticks> load(String insturment, int timeframe) throws Throwable;


}
