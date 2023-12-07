package year2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04Test {

    Day04 classUnderTest = new Day04();

    @Test
    void shouldReturnCorrectValueForTestDataPart1() {
        assertEquals(13, classUnderTest.challenge1("2023/day-04/example-1.txt"));
    }

    @Test
    void shouldReturnCorrectValueForInputPart1() {
        assertEquals(22674, classUnderTest.challenge1("2023/day-04/input-1.txt"));
    }

    @Test
    void shouldReturnCorrectValueForTestDataPart2() {
        assertEquals(30, classUnderTest.challenge2("2023/day-04/example-2.txt"));
    }

    @Test
    void shouldReturnCorrectValueForInputPart2() {
        assertEquals(5747443, classUnderTest.challenge2("2023/day-04/input-2.txt"));
    }
}