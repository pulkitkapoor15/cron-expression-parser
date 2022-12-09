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

public class BoundedParserTest {
    private final static String REGEX = "^\\d+-\\d+(,\\d+-\\d+)*$";
    BoundedParser boundedParser = new BoundedParser();

    private static Stream<Arguments> boundedParserData() {
        return Stream.of(
                Arguments.of(TimeToken.MINUTE, "1-10", Arrays.asList(1,2,3,4,5,6,7,8,9,10)),
                Arguments.of(TimeToken.HOUR,   "1-2", Arrays.asList(1,2)),
                Arguments.of(TimeToken.DAY_OF_MONTH, "1-4", Arrays.asList(1,2,3,4)),
                Arguments.of(TimeToken.MONTH,"1-3", Arrays.asList(1,2,3)),
                Arguments.of(TimeToken.DAY_OF_WEEK, "1-7", Arrays.asList(1,2,3,4,5,6,7))
        );
    }

    private static Stream<Arguments> boundedParserDataWithException() {
        return Stream.of(
                Arguments.of(TimeToken.MINUTE, "50-65"),
                Arguments.of(TimeToken.HOUR,   "1-45"),
                Arguments.of(TimeToken.DAY_OF_MONTH, "56-58"),
                Arguments.of(TimeToken.MONTH,"12-17"),
                Arguments.of(TimeToken.DAY_OF_WEEK, "1-8")
        );
    }

    @ParameterizedTest
    @MethodSource("boundedParserData")
    void testIfNthIntervalParserIsWorkingAsExpected(TimeToken TimeToken, String cron, List<Integer> expectedList) {
        List<Integer> actualList = boundedParser.getTimingfromCron(TimeToken, cron);
        Assertions.assertTrue(actualList.equals(expectedList));
    }

    @ParameterizedTest
    @MethodSource("boundedParserDataWithException")
    void testIfNthIntervalParserThrowsExceptionForInvalidInput(TimeToken TimeToken, String cron) {
        Assertions.assertThrows(InvalidCronException.class, () -> {
            boundedParser.getTimingfromCron(TimeToken, cron);
        });
    }

    @Test
    public void testGetRegexMatching() {
        Assertions.assertEquals(REGEX, boundedParser.getRegexMatching());
    }
}
