package year2021;

import utils.Utils;

import java.util.List;
import java.util.stream.Collectors;

public class Day1 {

    public static void main(String[] args) {
        final List<Integer> integerList = Utils.readFile("2021/day-1/input-1.txt").stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        part1(integerList); // result: 1602
        part2(integerList); // result: 1633
    }

    private static void part1(List<Integer> integerList) {
        int result = 0;

        for (int i = 0; i < integerList.size() - 1; i++) {
            if (integerList.get(i) < integerList.get(i + 1)) result++;
        }

        System.out.println(result);
    }

    private static void part2(List<Integer> integerList) {
        int result = 0;

        for (int i = 0; i < integerList.size() - 3; i++) {
            int window1Value = integerList.get(i) + integerList.get(i + 1) + integerList.get(i + 2);
            int window2Value = integerList.get(i + 1) + integerList.get(i + 2) + integerList.get(i + 3);
            if (window1Value < window2Value) result++;
        }

        System.out.println(result);
    }
}
