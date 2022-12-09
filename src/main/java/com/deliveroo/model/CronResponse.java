package com.deliveroo.model;

import java.util.Formatter;
import java.util.List;

public class CronResponse {
    private List<Integer> minute; 
    private List<Integer> hour; 
    private List<Integer> daysOfMonth;
    private List<Integer> month;
    private List<Integer> daysOfWeek;
    private String expression;

    public String getExpression() {
        return expression;
    }
    public void setExpression(String expression) {
        this.expression = expression;
    }
    public List<Integer> getMinute() {
        return minute;
    }
    public void setMinute(List<Integer> minute) {
        this.minute = minute;
    }
    public List<Integer> getHour() {
        return hour;
    }
    public void setHour(List<Integer> hour) {
        this.hour = hour;
    }
    public List<Integer> getDaysOfMonth() {
        return daysOfMonth;
    }
    public void setDaysOfMonth(List<Integer> daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }
    public List<Integer> getMonth() {
        return month;
    }
    public void setMonth(List<Integer> month) {
        this.month = month;
    }
    public List<Integer> getDaysOfWeek() {
        return daysOfWeek;
    }
    public void setDaysOfWeek(List<Integer> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
    
    public String printResponse() {
        Formatter fmt = new Formatter();  
        fmt.format("%14s %17s \n", TimeToken.MINUTE.getName() , printSubValues(minute));
        fmt.format("%14s %17s \n", TimeToken.HOUR.getName(), printSubValues(hour));
        fmt.format("%14s %17s \n", TimeToken.DAY_OF_MONTH.getName(), printSubValues(daysOfMonth));
        fmt.format("%14s %17s \n", TimeToken.MONTH.getName(), printSubValues(month));
        fmt.format("%14s %17s \n", TimeToken.DAY_OF_WEEK.getName(), printSubValues(daysOfWeek));
        fmt.format("%14s %17s", "command", expression);   
        return fmt.toString();
    }

    private String printSubValues(List<Integer> values) {
        String subValues = new String();
        for (Integer value: values) {
            subValues = subValues.concat(Integer.toString(value) + " " );
        }
        return subValues;
    }
}
