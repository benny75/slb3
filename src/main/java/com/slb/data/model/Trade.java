package com.slb.data.model;

import lombok.Data;
import org.apache.commons.lang3.time.FastDateFormat;

@Data
public class Trade {

    private FastDateFormat openDatetime;
    private double openPrice;
    private int quantity;
    private FastDateFormat closeDatetime;
    private double closePrice;

}
