package com.deliveroo.parser.controller;

import java.util.List;

import com.deliveroo.model.TimeToken;
import com.deliveroo.parser.ParserLogic;

public interface IParserController {
    void addParser(ParserLogic parser);
    List <Integer> getCronTiming(TimeToken timeToken, String cron);
}
