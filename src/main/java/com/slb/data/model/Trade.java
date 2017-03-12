package com.slb.data.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Trade {

    private int id;

    private int openIndex;
    private LocalDateTime openDatetime;
    private double openPrice;

    private Position position;
    private int quantity;

    private int closeIndex;
    private LocalDateTime closeDatetime;
    private double closePrice;

    private double profit;

    public void calculateProfit(double commision){
        this.profit = (((closePrice - openPrice) * position.getValue()) - commision) * quantity * .01;
    }

}
