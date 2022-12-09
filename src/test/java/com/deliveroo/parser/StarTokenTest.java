package com.deliveroo.parser;

import com.deliveroo.model.TimeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StarTokenTest {

    StarToken starToken = new StarToken();

    private final static String REGEX = "^\\*$";

    private static List<Integer> getExpectedList(Integer start, Integer end) {
        List <Integer> expectedList = new ArrayList<Integer>();
        for (int i = start; i < end; i++) {
            expectedList.add(i);
        }
        return expectedList;
    }

    private static Stream<Arguments> dataForStarParser() {
        return Stream.of(
                Arguments.of(TimeToken.MINUTE, "*", getExpectedList(0, 60)),
                Arguments.of(TimeToken.HOUR, "*", getExpectedList(0, 24)),
                Arguments.of(TimeToken.DAY_OF_MONTH, "*", getExpectedList(1, 32)),
                Arguments.of(TimeToken.MONTH, "*", getExpectedList(1, 13)),
                Arguments.of(TimeToken.DAY_OF_WEEK, "*", getExpectedList(1, 8))
        );
    }

    @ParameterizedTest
    @MethodSource("dataForStarParser")
    void testIfStarParserThrowsExceptionForInvalidInput(TimeToken TimeToken, String cronExpression, List<Integer> expectedList) {
        List<Integer> actualList = starToken.getTimingfromCron(TimeToken, cronExpression);
        Assertions.assertTrue(actualList.equals(expectedList));
    }

    @Test
    public void testGetRegexMatching() {
        Assertions.assertEquals(REGEX, starToken.getRegexMatching());
    }
}
