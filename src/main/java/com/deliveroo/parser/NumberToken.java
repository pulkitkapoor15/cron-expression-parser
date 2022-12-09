package com.deliveroo.parser;

import java.util.Arrays;
import java.util.List;

import com.deliveroo.exceptions.InvalidCronException;
import com.deliveroo.model.TimeToken;

public class NumberToken extends ParserLogic {
    private final static String regex = "^\\d+$";

    public static String getRegex() {
        return regex;
    }

    @Override
    public String getRegexMatching() {
        return regex;
    }

    @Override
    public List<Integer> getTimingfromCron(TimeToken timeToken, String cron) throws InvalidCronException {
        // TODO Auto-generated method stub
        Integer number = Integer.parseInt(cron);
        if (number < timeToken.getLowerLimit() || number > timeToken.getUpperLimit()) {
            new InvalidCronException(timeToken, cron, "Please enter the correct range for number as per " + timeToken.getName());
        }
        return Arrays.asList(Integer.parseInt(cron));
    }
    
}
