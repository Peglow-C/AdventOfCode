package year2023;

import utils.Utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 {

    public int challenge1(String filePath) {
        return Utils.readFile(filePath).stream()
                .map(Scratchcard::new)
                .map(Scratchcard::calculatePoints)
                .reduce(0, Integer::sum);
    }

    public int challenge2(String filePath) {
        List<String> fileRows = Utils.readFile(filePath);
        int highestCardIndex = fileRows.size();
        List<Scratchcard> initialScratchcards = fileRows.stream()
                .map(Scratchcard::new)
                .toList();
        Queue<Scratchcard> queue = new ArrayDeque<>(initialScratchcards);
        int counter = highestCardIndex;
        while (!queue.isEmpty()) {
            Scratchcard card = queue.poll();
            int numberOfWinningNumbers = card.getNumberOfWinningNumbers();
            for (int i = 1; i <= numberOfWinningNumbers; i++) {
                if (card.index + i <= highestCardIndex) {
                    queue.add(initialScratchcards.get(card.index + i - 1));
                }
            }
            counter += numberOfWinningNumbers;
        }
        return counter;
    }


    private static class Scratchcard {
        int index;
        Set<Integer> winningNumbers = new HashSet<>();
        List<Integer> actualNumbers = new ArrayList<>();

        public Scratchcard(String row) {
            Matcher matcher = Pattern.compile("Card (.*): (.*) \\| (.*)")
                    .matcher(row);
            if (matcher.find()) {
                index = Integer.parseInt(matcher.group(1).trim());
                initializeWinningNumbers(matcher.group(2));
                initializeActualNumbers(matcher.group(3));
            }
        }

        private void initializeWinningNumbers(String numbersString) {
            Scanner scanner = new Scanner(numbersString);
            while (scanner.hasNextInt()) {
                this.winningNumbers.add(scanner.nextInt());
            }
        }

        private void initializeActualNumbers(String numbersString) {
            Scanner scanner = new Scanner(numbersString);
            while (scanner.hasNextInt()) {
                this.actualNumbers.add(scanner.nextInt());
            }
        }

        public int calculatePoints() {
            int counter = 0;
            for (Integer actualNumber : actualNumbers) {
                if (winningNumbers.contains(actualNumber) && counter == 0) {
                    counter = 1;
                    continue;
                }
                if (winningNumbers.contains(actualNumber) && counter > 0) {
                    counter = counter * 2;
                }
            }
            return counter;
        }

        public int getNumberOfWinningNumbers() {
            int counter = 0;
            for (Integer actualNumber : actualNumbers) {
                if (winningNumbers.contains(actualNumber)) {
                    counter++;
                }
            }
            return counter;
        }
    }
}
