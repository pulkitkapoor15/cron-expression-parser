package com.deliveroo.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.deliveroo.exceptions.InvalidCronException;
import com.deliveroo.model.TimeToken;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class NumberTokenTest {
    NumberToken numberToken = new NumberToken();
    private static String regex = "^\\d+$";

    private static Stream<Arguments> numberParserData() {
        return Stream.of(
                Arguments.of(TimeToken.MINUTE, "1", Arrays.asList(1)),
                Arguments.of(TimeToken.HOUR, "5", Arrays.asList(5)),
                Arguments.of(TimeToken.DAY_OF_MONTH, "22", Arrays.asList(22)),
                Arguments.of(TimeToken.MONTH, "12", Arrays.asList(12)),
                Arguments.of(TimeToken.DAY_OF_WEEK,"1", Arrays.asList(1))
        );
    }

    private static Stream<Arguments> numberParserDataWithException() {
        return Stream.of(
                Arguments.of(TimeToken.MINUTE, "70"),
                Arguments.of(TimeToken.HOUR, "70"),
                Arguments.of(TimeToken.DAY_OF_MONTH, "70"),
                Arguments.of(TimeToken.MONTH, "70"),
                Arguments.of(TimeToken.DAY_OF_WEEK,"70")
        );
    }


    @Test
    public void testGetRegexMatching() {
        Assertions.assertEquals(regex, numberToken.getRegexMatching());
    }

    @ParameterizedTest
    @MethodSource("numberParserData")
    public void testGetTimingfromCronWorkingFine(TimeToken timeToken, String cron, List<Integer> expectedIntervals) {
        List<Integer> actualIntervals = numberToken.getTimingfromCron(timeToken, cron);
        Assertions.assertTrue(actualIntervals.equals(expectedIntervals));
    }

    @ParameterizedTest
    @MethodSource("numberParserDataWithException")
    public void testGetTimingfromCronThrowsException(TimeToken timeToken, String cron) {
        Assertions.assertThrows(InvalidCronException.class, () -> {
            numberToken.getTimingfromCron(timeToken, cron);
        });
    }
}
