package com.slb.data.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;

@Data
@Builder
public class Ticks {

    private LocalDateTime datetime;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;

    public double[] toDoubleArray(){
        double[] result = {open, high, low, close, volume};
        return result;
    }

}
