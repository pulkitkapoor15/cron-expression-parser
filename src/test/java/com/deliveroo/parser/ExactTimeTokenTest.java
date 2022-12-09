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

public class ExactTimeTokenTest {
    private final static String REGEX = "^\\d+(,\\d+)*$";
    ExactTimeToken exactTimeTokenTest = new ExactTimeToken();

    private static Stream<Arguments> exactTimeTokenData() {
        return Stream.of(
                Arguments.of(TimeToken.MINUTE, "1,2,3", Arrays.asList(1,2,3)),
                Arguments.of(TimeToken.HOUR,   "3,6,9", Arrays.asList(3,6,9)),
                Arguments.of(TimeToken.DAY_OF_MONTH, "1,2", Arrays.asList(1,2)),
                Arguments.of(TimeToken.MONTH,"5,12", Arrays.asList(5,12)),
                Arguments.of(TimeToken.DAY_OF_WEEK, "1,2", Arrays.asList(1,2))
        );
    }

    private static Stream<Arguments> exactTimeTokenDataWithException() {
        return Stream.of(
                Arguments.of(TimeToken.MINUTE, "1,70"),
                Arguments.of(TimeToken.HOUR,   "1,60"),
                Arguments.of(TimeToken.DAY_OF_MONTH, "2,35"),
                Arguments.of(TimeToken.MONTH,"5,20"),
                Arguments.of(TimeToken.DAY_OF_WEEK, "8,20")
        );
    }

    @ParameterizedTest
    @MethodSource("exactTimeTokenData")
    void testIfExactTimeTokenIsWorkingAsExpected(TimeToken TimeToken, String cronExpression, List<Integer> expectedList) {
        List<Integer> actualList = exactTimeTokenTest.getTimingfromCron(TimeToken, cronExpression);
        Assertions.assertTrue(actualList.equals(expectedList));
    }

    @ParameterizedTest
    @MethodSource("exactTimeTokenDataWithException")
    void testIExactTimeTokenThrowsExceptionForInvalidInput(TimeToken TimeToken, String cronExpression) {
        Assertions.assertThrows(InvalidCronException.class, () -> {
            exactTimeTokenTest.getTimingfromCron(TimeToken, cronExpression);
        });
    }

    @Test
    public void testGetRegexMatching() {
        Assertions.assertEquals(REGEX, exactTimeTokenTest.getRegexMatching());
    }
}
