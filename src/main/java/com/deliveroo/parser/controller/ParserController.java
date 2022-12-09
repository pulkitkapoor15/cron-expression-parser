package com.deliveroo.parser.controller;

import com.deliveroo.exceptions.InvalidCronException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.deliveroo.model.TimeToken;
import com.deliveroo.parser.ParserLogic;

public class ParserController implements IParserController {

    private Set <ParserLogic> parsers = new HashSet<>();

    @Override
    public void addParser(ParserLogic parser) {
        parsers.add(parser);   
    }

    @Override
    public List<Integer> getCronTiming(TimeToken timeToken, String cron) throws InvalidCronException {
        ParserLogic parser;
        try {
            parser = getParserFromCron(cron);
        } catch (InvalidCronException invalidCronException) {
            throw new InvalidCronException(timeToken, cron, invalidCronException.getMessage());
        }
        return parser.getTimingfromCron(timeToken, cron);
    }

    private ParserLogic getParserFromCron(String cronExpression) {
        for(ParserLogic parser : parsers) {
            if(cronExpression.matches(parser.getRegexMatching())) {
                return parser;
            }
        }

        throw new InvalidCronException("Invalid expression passed. Not able to parse the given input.");
    }
    
}
