package com.slb.indicator;

import com.slb.data.HistoricData;
import org.apache.commons.lang3.ArrayUtils;
import org.jblas.DoubleMatrix;

import java.util.Arrays;

public class Indicators {

    public static double[] getSma(HistoricData data, int period) {
        DoubleMatrix close = data.getOhlcv().getColumn(3);
        int[] indices = new int[data.size()-period+1];
        for(int i=0; i<data.size()-period+1; i++) {
            indices[i] = i;
        }
        double[] result = Arrays.stream(indices).parallel().mapToDouble(index ->
                close.getRange(index, index+period).mean()
        ).toArray();
        return ArrayUtils.addAll(new double[period-1], result);

    }
}
