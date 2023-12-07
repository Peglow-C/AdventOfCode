package year2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day07Test {

    Day07 classUnderTest = new Day07();

    @Test
    void shouldReturnCorrectValueForTestDataPart1() {
        assertEquals(6440, classUnderTest.challenge1("2023/day-07/example-1.txt"));
    }

    @Test
    void shouldReturnCorrectValueForInputPart1() {
        assertEquals(250951660, classUnderTest.challenge1("2023/day-07/input-1.txt"));
    }

    @Test
    void shouldReturnCorrectValueForTestDataPart2() {
        assertEquals(5905, classUnderTest.challenge2("2023/day-07/example-2.txt"));
    }

    @Test
    void shouldReturnCorrectValueForInputPart2() {
        assertEquals(251779014, classUnderTest.challenge2("2023/day-07/input-2.txt"));
    }
}