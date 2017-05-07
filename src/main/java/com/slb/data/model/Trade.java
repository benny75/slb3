package com.slb.data.model;

import com.slb.strategy.Strategy;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Trade {

    private Strategy strategy;

    private int openIndex;
    private LocalDateTime openDatetime;
    private double openPrice;

    private Position position;
    private int quantity;

    private int closeIndex;
    private LocalDateTime closeDatetime;
    private double closePrice;

    private double profit;

    public void calculateProfit(double commission){
        this.profit = (((closePrice - openPrice) * position.getValue()) - commission) * quantity * .01;
    }

    public boolean isOpen() {
        return closeIndex == 0;
    }

    /**
     * Close the trade if necessary
     */
    public boolean checkClose() {
        strategy.checkClose();

        return true;
    }

}
