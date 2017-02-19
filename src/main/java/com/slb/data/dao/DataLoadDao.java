package com.slb.data.dao;

import com.slb.data.model.Ticks;

public interface DataLoadDao {

    Ticks load(int timeframe);


}
