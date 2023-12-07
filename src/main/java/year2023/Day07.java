package year2023;

import utils.Utils;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 {

    public int challenge1(String filePath) {
        List<Hand> hands = Utils.readFile(filePath).stream()
                .map(Hand::new)
                .map(Hand::determineType)
                .sorted(Comparator.comparing(Function.identity(), Hand::compare).reversed())
                .toList();
        for (int i = 0; i < hands.size(); i++) {
            hands.get(i).setRank(i + 1);
            // hands.get(i).print();
        }
        return hands.stream()
                .map(Hand::calculatePoints)
                .reduce(0, Integer::sum);
    }

    public int challenge2(String filePath) {
        List<Hand2> hands = Utils.readFile(filePath).stream()
                .map(Hand2::new)
                .map(Hand2::determineType)
                .sorted(Comparator.comparing(Function.identity(), Hand2::compare).reversed())
                .toList();
        for (int i = 0; i < hands.size(); i++) {
            hands.get(i).setRank(i + 1);
            // hands.get(i).print();
        }
        return hands.stream()
                .map(Hand2::calculatePoints)
                .reduce(0, Integer::sum);
    }

    private static class Hand {
        List<Card> cards = new ArrayList<>();
        int bid;

        public int getType() {
            return type;
        }

        int type = 0;
        private int rank;

        public Hand(String row) {
            Matcher matcher = Pattern.compile("(.*) (.*)")
                    .matcher(row);
            if (matcher.find()) {
                bid = Integer.parseInt(matcher.group(2));
                String cardsString = matcher.group(1);
                for (int i = 0; i < cardsString.length(); i++) {
                    Card card = new Card(String.valueOf(cardsString.charAt(i)));
                    cards.add(card);
                }
            }
        }

        public Hand determineType() {
            Map<String, Integer> map = new HashMap<>();
            for (Card card : cards) {
                map.computeIfPresent(card.getValue(), (key, value) -> value + 1);
                map.computeIfAbsent(card.getValue(), key -> 1);
            }

            boolean fiveOfAKind = determineFiveOfAKind(map);
            boolean fourOfAKind = determineFourOfAKind(map);
            boolean fullHouse = determineFullHouse(map);
            boolean threeOfAKind = determineThreeOfAKind(map);
            boolean twoPair = determineTwoPair(map);
            boolean onePair = determinePair(map);

            if (fiveOfAKind) {
                type = 6;
                return this;
            }
            if (fourOfAKind) {
                type = 5;
                return this;
            }
            if (fullHouse) {
                type = 4;
                return this;
            }
            if (threeOfAKind) {
                type = 3;
                return this;
            }
            if (twoPair) {
                type = 2;
                return this;
            }
            if (onePair) {
                type = 1;
                return this;
            }
            return this;
        }

        private boolean determineFiveOfAKind(Map<String, Integer> map) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 5) {
                    return true;
                }
            }
            return false;
        }

        private boolean determineFourOfAKind(Map<String, Integer> map) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 4) {
                    return true;
                }
            }
            return false;
        }

        private boolean determineFullHouse(Map<String, Integer> map) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 3) {
                    for (Map.Entry<String, Integer> entry2 : map.entrySet()) {
                        if (entry2.getValue() == 2) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        private boolean determineThreeOfAKind(Map<String, Integer> map) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 3) {
                    return true;
                }
            }
            return false;
        }

        private boolean determineTwoPair(Map<String, Integer> map) {
            boolean onePair = false;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 2) {
                    if (onePair) {
                        return true;
                    }
                    onePair = true;
                }
            }
            return false;
        }

        private boolean determinePair(Map<String, Integer> map) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 2) {
                    return true;
                }
            }
            return false;
        }

        public int compare(Hand hand) {
            if (type == hand.getType()) {
                return withEqualType(hand);
            }
            return hand.getType() - type;
        }

        private int withEqualType(Hand hand) {
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).compareTo(hand.cards.get(i)) != 0) {
                    return cards.get(i).compareTo(hand.cards.get(i));
                }
            }
            return 0;
        }

        public void setRank(int i) {
            rank = i;
        }

        public int calculatePoints() {
            return rank * bid;
        }

        public void print() {
            System.out.print("rank: " + rank + " ");
            for (Card card : cards) {
                System.out.print(card.value);
            }
            System.out.println();
        }
    }

    private static class Card {
        String value;
        private final Map<String, Integer> map = new HashMap<>();

        public Card(String value) {
            this.value = value;
            map.put("A", 14);
            map.put("K", 13);
            map.put("Q", 12);
            map.put("J", 11);
            map.put("T", 10);
            map.put("9", 9);
            map.put("8", 8);
            map.put("7", 7);
            map.put("6", 6);
            map.put("5", 5);
            map.put("4", 4);
            map.put("3", 3);
            map.put("2", 2);
        }

        public String getValue() {
            return value;
        }

        public int compareTo(Card card) {
            return this.map.get(card.getValue()) - this.map.get(value);
        }
    }

    private static class Hand2 {
        List<Card2> cards = new ArrayList<>();
        int bid;

        public int getType() {
            return type;
        }

        int type = 0;
        private int rank;
        private int joker = 0;

        public Hand2(String row) {
            Matcher matcher = Pattern.compile("(.*) (.*)")
                    .matcher(row);
            if (matcher.find()) {
                bid = Integer.parseInt(matcher.group(2));
                String cardsString = matcher.group(1);
                for (int i = 0; i < cardsString.length(); i++) {
                    Card2 card = new Card2(String.valueOf(cardsString.charAt(i)));
                    cards.add(card);
                    if (card.value.equals("J")) {
                        joker++;
                    }
                }
            }
        }

        public Hand2 determineType() {
            Map<String, Integer> map = new HashMap<>();
            for (Card2 card : cards) {
                map.computeIfPresent(card.getValue(), (key, value) -> value + 1);
                map.computeIfAbsent(card.getValue(), key -> 1);
            }

            boolean fiveOfAKind = determineFiveOfAKind(map);
            boolean fourOfAKind = determineFourOfAKind(map);
            boolean fullHouse = determineFullHouse(map);
            boolean threeOfAKind = determineThreeOfAKind(map);
            boolean twoPair = determineTwoPair(map);
            boolean onePair = determinePair(map);

            if (fiveOfAKind) {
                type = 6;
                return this;
            }
            if (fourOfAKind) {
                type = 5;
                return this;
            }
            if (fullHouse) {
                type = 4;
                return this;
            }
            if (threeOfAKind) {
                type = 3;
                return this;
            }
            if (twoPair) {
                type = 2;
                return this;
            }
            if (onePair) {
                type = 1;
                return this;
            }
            return this;
        }

        private boolean determineFiveOfAKind(Map<String, Integer> map) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 5 || entry.getValue() + joker == 5) {
                    return true;
                }
            }
            return false;
        }

        private boolean determineFourOfAKind(Map<String, Integer> map) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() >= 4 || entry.getValue() + joker >= 4) {
                    return true;
                }
            }
            return false;
        }

        private boolean determineFullHouse(Map<String, Integer> map) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 3) {
                    for (Map.Entry<String, Integer> entry2 : map.entrySet()) {
                        if (entry2.getValue() == 2) {
                            return true;
                        }
                    }
                }
            }
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() + joker == 3) {
                    for (Map.Entry<String, Integer> entry2 : map.entrySet()) {
                        if (entry2.getValue() == 2 && !entry2.getKey().equals("J")) {
                            return true;
                        }
                    }
                }
            }
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 3 && !entry.getKey().equals("J")) {
                    for (Map.Entry<String, Integer> entry2 : map.entrySet()) {
                        if (entry2.getValue() + joker == 2) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        private boolean determineThreeOfAKind(Map<String, Integer> map) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() >= 3 || entry.getValue() + joker >= 3) {
                    return true;
                }
            }
            return false;
        }

        private boolean determineTwoPair(Map<String, Integer> map) {
            boolean onePair = false;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() >= 2 || entry.getValue() + joker >= 2) {
                    if (onePair) {
                        return true;
                    }
                    onePair = true;
                }
            }
            return false;
        }

        private boolean determinePair(Map<String, Integer> map) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() >= 2 || entry.getValue() + joker >= 2) {
                    return true;
                }
            }
            return false;
        }

        public int compare(Hand2 hand) {
            if (type == hand.getType()) {
                return withEqualType(hand);
            }
            return hand.getType() - type;
        }

        private int withEqualType(Hand2 hand) {
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).compareTo(hand.cards.get(i)) != 0) {
                    return cards.get(i).compareTo(hand.cards.get(i));
                }
            }
            return 0;
        }

        public void setRank(int i) {
            rank = i;
        }

        public int calculatePoints() {
            return rank * bid;
        }

        public void print() {
            System.out.print("rank: " + rank + " ");
            for (Card2 card : cards) {
                System.out.print(card.value);
            }
            System.out.println();
        }
    }

    private static class Card2 {
        String value;
        private final Map<String, Integer> map = new HashMap<>();

        public Card2(String value) {
            this.value = value;
            map.put("A", 14);
            map.put("K", 13);
            map.put("Q", 12);
            map.put("J", 1);
            map.put("T", 10);
            map.put("9", 9);
            map.put("8", 8);
            map.put("7", 7);
            map.put("6", 6);
            map.put("5", 5);
            map.put("4", 4);
            map.put("3", 3);
            map.put("2", 2);
        }

        public String getValue() {
            return value;
        }

        public int compareTo(Card2 card) {
            return this.map.get(card.getValue()) - this.map.get(value);
        }
    }
}
