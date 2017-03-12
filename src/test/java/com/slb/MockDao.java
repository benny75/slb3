package com.slb;

import com.slb.data.dao.DataLoadDao;
import com.slb.data.model.Ticks;

import java.util.ArrayList;
import java.util.List;

public class MockDao implements DataLoadDao {


    @Override
    public List<Ticks> load(String insturment, int timeframe) throws Throwable {
        List<Ticks> result = new ArrayList<>();
        for(int i=1; i<=50; i++){
            result.add(Ticks.builder().close(i).build());
        }
        return result;
    }
}
