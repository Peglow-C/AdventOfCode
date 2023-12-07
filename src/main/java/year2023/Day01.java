package year2023;

import utils.Utils;

import java.util.Comparator;
import java.util.List;

import static utils.Utils.isCharANumber;

public class Day01 {
    List<Mapping> map = List.of(
            new Mapping("0", 0),
            new Mapping("1", 1),
            new Mapping("2", 2),
            new Mapping("3", 3),
            new Mapping("4", 4),
            new Mapping("5", 5),
            new Mapping("6", 6),
            new Mapping("7", 7),
            new Mapping("8", 8),
            new Mapping("9", 9),
            new Mapping("zero", 0),
            new Mapping("one", 1),
            new Mapping("two", 2),
            new Mapping("three", 3),
            new Mapping("four", 4),
            new Mapping("five", 5),
            new Mapping("six", 6),
            new Mapping("seven", 7),
            new Mapping("eight", 8),
            new Mapping("nine", 9));

    public int challenge1(String path) {
        return Utils.readFile(path).stream()
                .map(this::determineRowNumber)
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

    public int challenge2(String path) {
        return Utils.readFile(path).stream()
                .map(this::determineRowNumberWithStrings)
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

    private String determineRowNumber(String row) {
        String firstNumber = "";
        String lastNumber = "";
        for (int i = 0; i < row.length(); i++) {
            if (isCharANumber(row.charAt(i))) {
                firstNumber = String.valueOf(row.charAt(i));
                break;
            }
        }
        for (int i = row.length() - 1; i >= 0; i--) {
            if (isCharANumber(row.charAt(i))) {
                lastNumber = String.valueOf(row.charAt(i));
                break;
            }
        }
        return firstNumber + lastNumber;
    }


    private String determineRowNumberWithStrings(String row) {
        List<ResultMapping> mappedRow = map.stream()
                .map(mapping -> mapRow(mapping, row))
                .toList();
        Integer firstNumber = mappedRow.stream()
                .sorted(Comparator.comparingInt(ResultMapping::firstIndex))
                .filter(resultMapping -> resultMapping.firstIndex >= 0 || resultMapping.lastIndex >= 0)
                .findFirst()
                .map(resultMapping -> resultMapping.value)
                .orElseThrow(() -> new IllegalArgumentException("No Number in line"));
        Integer lastNumber = mappedRow.stream()
                .sorted(Comparator.comparingInt(ResultMapping::lastIndex).reversed())
                .filter(resultMapping -> resultMapping.firstIndex >= 0 || resultMapping.lastIndex >= 0)
                .findFirst()
                .map(resultMapping -> resultMapping.value)
                .orElseThrow(() -> new IllegalArgumentException("No Number in line"));
        return firstNumber.toString() + lastNumber.toString();
    }

    private ResultMapping mapRow(Mapping mapping, String row) {
        return new ResultMapping(mapping.value, row.indexOf(mapping.string), row.lastIndexOf(mapping.string));
    }

    private record Mapping(String string, int value) {
    }

    private record ResultMapping(int value, int firstIndex, int lastIndex) {
    }
}
