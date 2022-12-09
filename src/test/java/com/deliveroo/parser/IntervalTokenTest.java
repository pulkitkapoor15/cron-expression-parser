package com.deliveroo.parser;

import com.deliveroo.exceptions.InvalidCronException;
import com.deliveroo.model.TimeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class IntervalTokenTest {
    private final static String REGEX = "^\\*/\\d+$";
    IntervalToken intervalToken = new IntervalToken();

    private static Stream<Arguments> intervalTokenData() {
        return Stream.of(
                Arguments.of(TimeToken.MINUTE, "*/10", Arrays.asList(0, 10, 20, 30, 40, 50)),
                Arguments.of(TimeToken.HOUR,   "*/12", Arrays.asList(0,12)),
                Arguments.of(TimeToken.DAY_OF_MONTH, "*/10", Arrays.asList(1,11,21,31)),
                Arguments.of(TimeToken.MONTH,"*/2", Arrays.asList(1,3,5,7,9,11)),
                Arguments.of(TimeToken.DAY_OF_WEEK, "*/1", Arrays.asList(1,2,3,4,5,6,7))
        );
    }

    private static Stream<Arguments> intervalTokenDataWithException() {
        return Stream.of(
                Arguments.of(TimeToken.MINUTE, "*/70"),
                Arguments.of(TimeToken.HOUR,   "*/70"),
                Arguments.of(TimeToken.DAY_OF_MONTH, "*/70"),
                Arguments.of(TimeToken.MONTH,"*/70"),
                Arguments.of(TimeToken.DAY_OF_WEEK, "*/70")
        );
    }

    @ParameterizedTest
    @MethodSource("intervalTokenData")
    void testIfNthIntervalParserIsWorkingAsExpected(TimeToken TimeToken, String cronExpression, List<Integer> expectedList) {
        List<Integer> actualList = intervalToken.getTimingfromCron(TimeToken, cronExpression);
        Assertions.assertTrue(actualList.equals(expectedList));
    }

    @ParameterizedTest
    @MethodSource("intervalTokenDataWithException")
    void testIfNthIntervalParserThrowsExceptionForInvalidInput(TimeToken TimeToken, String cronExpression) {
        Assertions.assertThrows(InvalidCronException.class, () -> {
            intervalToken.getTimingfromCron(TimeToken, cronExpression);
        });
    }

    @Test
    public void testGetRegexMatching() {
        Assertions.assertEquals(REGEX, intervalToken.getRegexMatching());
    }
}
