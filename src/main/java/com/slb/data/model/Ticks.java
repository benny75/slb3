package com.slb.data.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@Data
public class Ticks {

    private ArrayList<OffsetDateTime> datetime;
    private ArrayList<Double> open;
    private ArrayList<Double> high;
    private ArrayList<Double> low;
    private ArrayList<Double> close;
}
