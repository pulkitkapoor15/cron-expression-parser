package com.deliveroo.parser;

import java.util.ArrayList;
import java.util.List;

import com.deliveroo.exceptions.InvalidCronException;
import com.deliveroo.model.TimeToken;

public class ExactTimeToken extends ParserLogic {
    final static String regex = "^\\d+(,\\d+)*$";

    @Override
    public String getRegexMatching() {
        return regex;
    }

    @Override
    public List<Integer> getTimingfromCron(TimeToken timeToken, String cron) throws InvalidCronException {
        // TODO Auto-generated method stub
        String[] timings = cron.split(",");
        List<Integer> interval = new ArrayList<>();

        for (String timing: timings) {
            Integer time = Integer.parseInt(timing);
            if (isValid(time, timeToken)) {
                interval.add(time);
            } else {
                new InvalidCronException(timeToken, cron, "Please enter correct range for " + timeToken.getName());
            }
        }
        return interval;
    }

    private boolean isValid (Integer time, TimeToken timeToken) {
        return !(time < timeToken.getLowerLimit() || time > timeToken.getUpperLimit());
    }
    
}
