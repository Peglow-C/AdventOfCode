package year2021;

import utils.Utils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day3 {

    public static void main(String[] args) {

        final List<String> input = Utils.readFile("2021/day-03/input-1.txt");

        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    protected static PowerConsumption part1(List<String> input) {
        int[] counter = new int[input.get(0).length()];
        for (String i : input) {
            for (int j = 0; j < i.length(); j++) {
                if (i.charAt(j) == '1') {
                    counter[j]++;
                } else if (i.charAt(j) == '0') {
                    counter[j]--;
                } else {
                    throw new IllegalArgumentException("This string contains something other than 0 and 1");
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i : counter) {
            if (i > 0) {
                sb.append(1);
            } else if (i < 0) {
                sb.append(0);
            } else {
                throw new UnsupportedOperationException("Both 0 and 1 are of the same frequency");
            }
        }
        int epsilon = Integer.valueOf(sb.toString(), 2);
        int gamma = (int) (Math.pow(2, counter.length) - 1) - epsilon;
        return new PowerConsumption(epsilon, gamma, epsilon * gamma);
    }

    protected static LifeSupportRating part2(List<String> input) {
        List<String> oxygenValue = List.copyOf(input);
        int[] counter = new int[input.get(0).length()];
        oxygenValue = getValue(oxygenValue, counter, false);

        List<String> carbonDioxideValue = List.copyOf(input);
        counter = new int[input.get(0).length()];
        carbonDioxideValue = getValue(carbonDioxideValue, counter, true);

        Integer oxygen = Integer.valueOf(oxygenValue.get(0), 2);
        Integer carbonDioxide = Integer.valueOf(carbonDioxideValue.get(0), 2);
        return new LifeSupportRating(oxygen, carbonDioxide, oxygen * carbonDioxide);
    }

    private static List<String> getValue(List<String> values, int[] counter, boolean inverted) {
            for (int i = 0; i < counter.length; i++) {
                for (String s: values) {
                    if (s.charAt(i) == '1') {
                        counter[i]++;
                    } else if (s.charAt(i) == '0') {
                        counter[i]--;
                    } else {
                        throw new IllegalArgumentException("This string contains something other than 0 and 1");
                    }
                }
                values = filterValues(values, i,counter[i], inverted);
                if (values.size() == 1) break;
            }
        return values;
    }

    private static List<String> filterValues(List<String> values, final int index, final int i, boolean inverted) {
        return values.stream().filter(onlyReturnElementsWithAt(i, index, inverted)).collect(Collectors.toList());
    }

    private static Predicate<? super String> onlyReturnElementsWithAt(int i, int index, boolean inverted) {
        if (inverted) {
            if (i >= 0) {
                return s -> s.charAt(index) == '0';
            } else {
                return s -> s.charAt(index) == '1';
            }
        } else {
            if (i >= 0) {
                return s -> s.charAt(index) == '1';
            } else {
                return s -> s.charAt(index) == '0';
            }
        }
    }

    protected record PowerConsumption(int gamma, int epsilon, int product) {
    }

    protected record LifeSupportRating(int oxygen, int carbonDioxide, int product) {
    }
}
