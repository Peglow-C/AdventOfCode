package year2021;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Day2Test {

    @Test
    void shouldReturnCorrectValueForTestDataPart1() {
        assertEquals(new Day2.ResultType(15, 10, 150, 0),
                Day2.part1(Utils.readFile("2021/day-02/example-1.txt")));
    }

    @Test
    void shouldReturnCorrectValueForInputPart1() {
        assertEquals(new Day2.ResultType(1845, 916, 1690020, 0),
                Day2.part1(Utils.readFile("2021/day-02/input-1.txt")));
    }

    @Test
    void shouldReturnCorrectValuePart2() {
        assertEquals(new Day2.ResultType(15, 60, 900, 0),
                Day2.part2(Utils.readFile("2021/day-02/example-1.txt")));
    }

    @Test
    void shouldReturnCorrectValueForInputPart2() {
        assertEquals(new Day2.ResultType(1845, 763408, 1408487760, 0),
                Day2.part1(Utils.readFile("2021/day-02/input-1.txt")));
    }
}
