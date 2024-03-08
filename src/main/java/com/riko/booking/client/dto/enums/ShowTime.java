package com.riko.booking.client.dto.enums;

public enum ShowTime {
    MORNING(9),
    NOON(12),
    AFTERNOON(15),
    EVENING(18),
    NIGHT(21);

    private final int startTime;

    ShowTime(int startTime) {
        this.startTime = startTime;
    }

    public int getStartTime() {
        return startTime;
    }
}