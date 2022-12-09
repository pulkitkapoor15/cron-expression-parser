package com.deliveroo.parser;

import java.util.List;

import com.deliveroo.exceptions.InvalidCronException;
import com.deliveroo.model.TimeToken;

public class IntervalToken extends ParserLogic {
    private final static String REGEX = "^\\*/\\d+$";
    private final static String FORMAT = "*/";

    @Override
    public String getRegexMatching() {
        return REGEX;
    }

    @Override
    public List<Integer> getTimingfromCron(TimeToken timeToken, String cron) throws InvalidCronException {
        String valueCron = cron.substring(FORMAT.length());
        Integer interval = Integer.valueOf(valueCron);
        if(interval < timeToken.getLowerLimit() || interval > timeToken.getUpperLimit()) {
            throw new InvalidCronException(timeToken, cron, "Please pass the values within the range of " + timeToken.getName());
        }

        return cronTimings(timeToken, interval);
    }
    
}
