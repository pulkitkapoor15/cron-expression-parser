package com.deliveroo.parser;

import java.util.List;

import com.deliveroo.exceptions.InvalidCronException;
import com.deliveroo.model.TimeToken;

public class StarToken extends ParserLogic {
    private final static Integer INTERVAL = 1;
    private final static String regex = "^\\*$";

    @Override
    public String getRegexMatching() {
        return regex;
    }

    @Override
    public List<Integer> getTimingfromCron(TimeToken timeToken, String cron) throws InvalidCronException {
        return cronTimings(timeToken, INTERVAL);
    }
    
}
