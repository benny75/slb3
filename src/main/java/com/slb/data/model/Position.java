package com.slb.data.model;

import java.util.HashMap;
import java.util.Map;

public enum Position{
    SELL(-1), NO_MOVE(0), BUY(1);

    int value;

    private static final Map<Integer, Position> POSITION_MAP = new HashMap<Integer, Position>();
    static {
        for (Position position : values()) {
            POSITION_MAP.put(position.getValue(), position);
        }
    }

    Position(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Position getByValue(int value) {
        return POSITION_MAP.get(value);
    }

}