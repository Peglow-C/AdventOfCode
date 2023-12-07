package year2021;

import utils.Utils;

import java.util.List;


public class Day2 {

    public static void main(String[] args) {

        final List<String> input = Utils.readFile("2021/day-2/input-1.txt");

        System.out.println("Part 1:");
        part1(input); // correct answer: 1690020
        System.out.println("\nPart 2:");
        part2(input); // correct answer: 1408487760
    }

    protected static ResultType part1(List<String> input) {
        final int horizontal = input.stream()
                .map(i -> i.split(" "))
                .filter(i -> i[0].equals("forward"))
                .mapToInt(i -> Integer.parseInt(i[1]))
                .sum();

        final int depth = input.stream()
                .map(i -> i.split(" "))
                .filter(i -> i[0].equals("up") || i[0].equals("down"))
                .reduce(0,
                        (cur, i) -> i[0].equals("up") ? cur - Integer.parseInt(i[1]) : cur + Integer.parseInt(i[1]),
                        (Integer::sum));

        System.out.println("horizontal: " + horizontal);
        System.out.println("depth: " + depth);
        System.out.println("product: " + horizontal * depth);
        return new ResultType(horizontal, depth, horizontal * depth, 0);
    }

    protected static ResultType part2(List<String> input) {
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        String[] s;

        for (String i: input) {

            s = i.split(" ");

            switch (s[0]) {
                case "down" -> aim += Integer.parseInt(s[1]);
                case "up" -> aim -= Integer.parseInt(s[1]);
                case "forward" -> {
                    horizontal += Integer.parseInt(s[1]);
                    depth += (aim * Integer.parseInt(s[1]));
                }
                default -> throw new IllegalArgumentException("invalid command");
            }
        }

        System.out.println("horizontal: " + horizontal);
        System.out.println("depth: " + depth);
        System.out.println("aim: " + aim);
        System.out.println("product: " + horizontal * depth);
        return new ResultType(horizontal, depth, horizontal * depth, aim);
    }

    record ResultType(int horizontal, int depth, int product, int aim) {
    }
}
