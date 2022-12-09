package com.deliveroo;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.deliveroo.exceptions.InvalidCronException;
import com.deliveroo.model.CronResponse;
import com.deliveroo.parser.BoundedParser;
import com.deliveroo.parser.ExactTimeToken;
import com.deliveroo.parser.IntervalToken;
import com.deliveroo.parser.NumberToken;
import com.deliveroo.parser.ParserLogic;
import com.deliveroo.parser.StarToken;
import com.deliveroo.parser.controller.IParserController;
import com.deliveroo.parser.controller.ParserController;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main(String[] args) {
        if (args.length != 1 || args[0].split(" ").length != 6) {
            printGuidelines();
            return;
        }

        IParserController parserController = new ParserController();
        ParserLogic starToken = new StarToken();
        parserController.addParser(starToken);

        ParserLogic numberToken = new NumberToken();
        parserController.addParser(numberToken);

        ParserLogic exactTimeToken = new ExactTimeToken();
        parserController.addParser(exactTimeToken);

        ParserLogic intervalToken = new IntervalToken();
        parserController.addParser(intervalToken);

        ParserLogic boundedParser = new BoundedParser();
        parserController.addParser(boundedParser);

        CronParser cronExpressionParser = new CronParser(parserController);

        CronResponse response;
        try{
            response = cronExpressionParser.parseString(args[0]);
            System.out.println(response.printResponse());
        } catch (InvalidCronException ex) {
            System.err.println(ex.getMessage());
            System.out.println("\n");
            printGuidelines();
        }
    }

    private static void printGuidelines() {
        System.out.println("Guidelines: ");
        System.out.println("Pass the cron expression arguments in the same order");
        System.out.println("[minute] [hour] [day of month] [month] [day of week] [command]");
        System.out.println("Example: */15 0 1,15 * 1-5 /usr/bin/find");
    }


}
