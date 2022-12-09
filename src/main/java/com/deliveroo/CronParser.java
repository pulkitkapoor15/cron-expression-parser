package com.deliveroo;

import com.deliveroo.model.CronResponse;
import com.deliveroo.model.TimeToken;
import com.deliveroo.parser.controller.*;

public class CronParser {

    private IParserController controller;

    public CronParser(IParserController controller) {
        this.controller = controller;
    }

    public CronResponse parseString(String cron) {
        CronResponse response = new CronResponse();
        String[] exp = cron.split(" ");

        response.setMinute(controller.getCronTiming(TimeToken.MINUTE, exp[0]));
        response.setHour(controller.getCronTiming(TimeToken.HOUR, exp[1]));
        response.setDaysOfMonth(controller.getCronTiming(TimeToken.DAY_OF_MONTH, exp[2]));
        response.setMonth(controller.getCronTiming(TimeToken.MONTH, exp[3]));
        response.setDaysOfWeek(controller.getCronTiming(TimeToken.DAY_OF_WEEK, exp[4]));
        response.setExpression(exp[5]);
        return response;
    }
}
