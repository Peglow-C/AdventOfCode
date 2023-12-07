package year2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {

    Day05 classUnderTest = new Day05();

    @Test
    void shouldReturnCorrectValueForTestDataPart1() {
        assertEquals(0, classUnderTest.challenge1("2023/day-05/example-1.txt"));
    }

    @Test
    void shouldReturnCorrectValueForInputPart1() {
        assertEquals(0, classUnderTest.challenge1("2023/day-05/input-1.txt"));
    }

    @Test
    void shouldReturnCorrectValueForTestDataPart2() {
        assertEquals(0, classUnderTest.challenge2("2023/day-05/example-2.txt"));
    }

    @Test
    void shouldReturnCorrectValueForInputPart2() {
        assertEquals(0, classUnderTest.challenge2("2023/day-05/input-2.txt"));
    }
}