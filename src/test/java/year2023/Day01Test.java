package year2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day01Test {

    Day01 classUnderTest = new Day01();

    @Test
    void shouldReturnCorrectValueForTestDataPart1() {
        assertEquals(142, classUnderTest.challenge1("2023/day-01/example-1.txt"));
    }

    @Test
    void shouldReturnCorrectValueForInputPart1() {
        assertEquals(55029, classUnderTest.challenge1("2023/day-01/input-1.txt"));
    }

    @Test
    void shouldReturnCorrectValueForTestDataPart2() {
        assertEquals(281, classUnderTest.challenge2("2023/day-01/example-2.txt"));
    }

    @Test
    void shouldReturnCorrectValueForInputPart2() {
        assertEquals(55686, classUnderTest.challenge2("2023/day-01/input-2.txt"));
    }
}