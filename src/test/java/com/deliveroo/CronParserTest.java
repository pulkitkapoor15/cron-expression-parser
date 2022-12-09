package com.deliveroo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.deliveroo.model.CronResponse;
import com.deliveroo.parser.BoundedParser;
import com.deliveroo.parser.ExactTimeToken;
import com.deliveroo.parser.IntervalToken;
import com.deliveroo.parser.NumberToken;
import com.deliveroo.parser.StarToken;
import com.deliveroo.parser.controller.ParserController;

public class CronParserTest {
    private static CronParser parser;


    private static void init() {
        ParserController parseController = new ParserController();
        parseController.addParser(new BoundedParser());
        parseController.addParser(new ExactTimeToken());
        parseController.addParser(new IntervalToken());
        parseController.addParser(new NumberToken());
        parseController.addParser(new StarToken());
        parser = new CronParser(parseController);
    }


    @Test
    public void testParseString() {
        init();
        String expectedResponse = 
        "        minute       0 15 30 45 \n" + 
        "          hour                0 \n" + 
        "  day of month             1 15 \n" + 
        "         month 1 2 3 4 5 6 7 8 9 10 11 12 \n" + 
        "   day of week        1 2 3 4 5 \n" + 
        "       command     /usr/bin/find";
        String cron = "*/15 0 1,15 * 1-5 /usr/bin/find";
        List<Integer> listOfMinutes = new ArrayList<Integer>() {{
            add(0);
            add(15);
            add(30);
            add(45);
        } };
        List<Integer> listOfHour = new ArrayList<Integer>() {{
            add(0);
        } };
        List<Integer> listOfMonth = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(8);
            add(9);
            add(10);
            add(11);
            add(12);
            } };
        CronResponse response = parser.parseString(cron);
        // Assertions.assertTrue()
        System.out.println(response.getMinute());
        Assertions.assertTrue(response.getMinute().equals(listOfMinutes));
        Assertions.assertTrue(response.getHour().equals(listOfHour));
        Assertions.assertTrue(response.getMonth().equals(listOfMonth));
    }
    


}
