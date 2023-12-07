package year2021;

import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    @Test
    void shouldReturnCorrectValueForTestDataPart1() {
        final Day3.PowerConsumption powerConsumption = new Day3.PowerConsumption(22, 9, 198);
        assertEquals(powerConsumption, Day3.part1(List.of("00100", "11110", "10110", "10111", "10101", "01111", "00111",
                "11100", "10000", "11001", "00010", "01010")));
    }

    @Test
    void shouldReturnCorrectValueForInput() {
        final Day3.PowerConsumption powerConsumption = new Day3.PowerConsumption(1916, 2179, 4174964);
        assertEquals(powerConsumption, Day3.part1(Utils.readFile("2021/day-03/input-1.txt")));
    }

    @Test
    void shouldReturnCorrectValueForTestDataPart2() {
        final Day3.LifeSupportRating powerConsumption = new Day3.LifeSupportRating(23, 10, 230);
        assertEquals(powerConsumption, Day3.part2(List.of("00100", "11110", "10110", "10111", "10101", "01111", "00111",
                "11100", "10000", "11001", "00010", "01010")));
    }

    @Test
    void shouldReturnCorrectValueForInputPart2() {
        final Day3.LifeSupportRating powerConsumption = new Day3.LifeSupportRating(1371, 3264, 4474944);
        assertEquals(powerConsumption, Day3.part2(Utils.readFile("2021/day-03/input-1.txt")));
    }
}