package com.deliveroo.model;

public enum TimeToken {
    MINUTE("minute", 59, 0),
    HOUR("hour", 23, 0),
    DAY_OF_MONTH("day of month", 31, 1),
    MONTH("month", 12, 1),
    DAY_OF_WEEK("day of week", 7, 1);

    private String name;
    private Integer upperLimit;
    private Integer lowerLimit;
    
    

    TimeToken(String name, Integer upperLimit, Integer lowerLimit) {
        this.name = name;
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
    }
    
    public String getName() {
        return name;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public Integer getLowerLimit() {
        return lowerLimit;
    }
}

