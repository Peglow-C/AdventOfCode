package year2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Test {

    Day03 classUnderTest = new Day03();

    @Test
    void shouldReturnCorrectValueForTestDataPart1() {
        assertEquals(4361, classUnderTest.challenge1("2023/day-03/example-1.txt"));
    }

    @Test
    void shouldReturnCorrectValueForInputPart1() {
        assertEquals(540131, classUnderTest.challenge1("2023/day-03/input-1.txt"));
    }

    @Test
    void shouldReturnCorrectValueForTestDataPart2() {
        assertEquals(467835, classUnderTest.challenge2("2023/day-03/example-2.txt"));
    }

    @Test
    void shouldReturnCorrectValueForInputPart2() {
        assertEquals(86879020, classUnderTest.challenge2("2023/day-03/input-2.txt"));
    }
}