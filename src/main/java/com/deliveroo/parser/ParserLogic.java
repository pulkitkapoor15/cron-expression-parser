package com.deliveroo.parser;

import java.util.ArrayList;
import java.util.List;

import com.deliveroo.exceptions.InvalidCronException;
import com.deliveroo.model.TimeToken;

public abstract class ParserLogic {

    protected List<Integer> cronTimings(TimeToken timeToken, Integer interval) {
        return getTimings(timeToken.getLowerLimit(), timeToken.getUpperLimit(), interval);    
    }

    protected List<Integer> getTimings(Integer lowerLimit, Integer upperLimit, Integer interval) {
        List<Integer> timestamp = new ArrayList<>();
        for (int value = lowerLimit; value <= upperLimit; value += interval) {
            timestamp.add(value);
        }
        return timestamp;
    }

    public abstract String getRegexMatching();
    public abstract List<Integer> getTimingfromCron(TimeToken timeToken, String cron) throws InvalidCronException;
}
