package com.deliveroo.parser;

import java.util.ArrayList;
import java.util.List;

import com.deliveroo.exceptions.InvalidCronException;
import com.deliveroo.model.TimeToken;

public class BoundedParser extends ParserLogic {
    private final static String REGEX = "^\\d+-\\d+(,\\d+-\\d+)*$";

    @Override
    public String getRegexMatching() {
        return REGEX;
    }

    @Override
    public List<Integer> getTimingfromCron(TimeToken timeToken, String cron) throws InvalidCronException {
        String[] intervals = cron.split(",");
        List<Integer> timings = new ArrayList<>();

        for(String interval: intervals) {
            String[] lowerUpper = interval.split("-");
            Integer fromTime = Integer.parseInt(lowerUpper[0]);
            Integer toTime = Integer.parseInt(lowerUpper[1]);

            if(validate(fromTime, toTime, timeToken)) {
                timings.addAll(getTimings(fromTime, toTime, 1));
            } else {
                throw new InvalidCronException(timeToken, cron, "Please pass the values within range of " + timeToken.getName());
            }
        }
        return timings;
    } 

    private boolean validate(Integer fromTime, Integer toTime, TimeToken timeToken) {
        return fromTime >= timeToken.getLowerLimit() && fromTime <= timeToken.getUpperLimit()
        && toTime >= timeToken.getLowerLimit()
        && toTime <= timeToken.getUpperLimit()
        && fromTime <= toTime;

    }
    
}
