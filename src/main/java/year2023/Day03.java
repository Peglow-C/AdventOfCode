package year2023;

import utils.RowWithIndex;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.isCharANumber;

public class Day03 {

    public int challenge1(String filePath) {
        List<RowWithIndex> rowWithIndices = Utils.readFileWithLineIndex(filePath);
        List<Part> parts = rowWithIndices.stream()
                .flatMap(row -> scanRowForParts(row).stream())
                .toList();
        List<Connector> connectors = rowWithIndices.stream()
                .flatMap(row -> scanRowForConnector(row).stream())
                .toList();
        return parts.stream()
                .filter(part -> checkIfPartIsConnected(part, connectors))
                .map(Part::getNumber)
                .reduce(0, Integer::sum);
    }

    public int challenge2(String filePath) {
        List<RowWithIndex> rowWithIndices = Utils.readFileWithLineIndex(filePath);
        List<Part> parts = rowWithIndices.stream()
                .flatMap(row -> scanRowForParts(row).stream())
                .toList();
        List<Connector> connectors = rowWithIndices.stream()
                .flatMap(row -> scanRowForConnector(row).stream())
                .toList();
        parts.forEach(part -> checkIfPartIsConnected(part, connectors));
        return connectors.stream()
                .filter(connector -> connector.adjacentParts.size() == 2)
                .map(Connector::getGearRatio)
                .reduce(0, Integer::sum);
    }

    private List<Part> scanRowForParts(RowWithIndex row) {
        List<Part> collector = new ArrayList<>();
        Part currentPart = new Part(row.index());
        for (int i = 0; i < row.getRowLength(); i++) {
            boolean isCurrentCharANumber = isCharANumber(row.getChar(i));
            boolean isNextCharNotANumber = true;
            if (i + 1 < row.getRowLength()) {
                isNextCharNotANumber = !isCharANumber(row.getChar(i + 1));
            }
            if (isCurrentCharANumber) {
                currentPart.addRowElement(row.row(), i);
                if (isNextCharNotANumber) {
                    collector.add(currentPart);
                    currentPart = new Part(row.index());
                }
            }
        }
        return collector;
    }

    private List<Connector> scanRowForConnector(RowWithIndex row) {
        List<Connector> collector = new ArrayList<>();
        for (int i = 0; i < row.getRowLength(); i++) {
            boolean isCurrentCharNotANumber = !isCharANumber(row.getChar(i));
            boolean isCurrentCharNotADot = !isCharADot(row.getChar(i));
            if (isCurrentCharNotANumber && isCurrentCharNotADot) {
                collector.add(new Connector(i, row.index(), row.getChar(i)));
            }
        }
        return collector;
    }

    private boolean isCharADot(char c) {
        return c == '.';
    }

    private boolean checkIfPartIsConnected(Part part, List<Connector> connectors) {
        return connectors.stream()
                .filter(connector -> connector.isConnectorInAdjacentRow(part))
                .anyMatch(connector -> connector.isConnectorAdjacentToPart(part));
    }

    private static class Connector {
        String value;
        int x;
        int y;
        List<Part> adjacentParts;

        public Connector(int x, int y, char value) {
            this.x = x;
            this.y = y;
            this.adjacentParts = new ArrayList<>();
            this.value = String.valueOf(value);
        }

        boolean isConnectorInAdjacentRow(Part part) {
            return this.y == part.yCoordinate - 1 || this.y == part.yCoordinate || this.y == part.yCoordinate + 1;
        }

        boolean isConnectorAdjacentToPart(Part part) {
            List<Coordinate> adjacentCoords = new ArrayList<>();
            for (int i = 0; i < part.xCoordinates.size(); i++) {
                adjacentCoords.add(new Coordinate(part.xCoordinates.get(i) - 1, part.yCoordinate - 1));
                adjacentCoords.add(new Coordinate(part.xCoordinates.get(i), part.yCoordinate - 1));
                adjacentCoords.add(new Coordinate(part.xCoordinates.get(i) + 1, part.yCoordinate - 1));
                adjacentCoords.add(new Coordinate(part.xCoordinates.get(i) + 1, part.yCoordinate));
                adjacentCoords.add(new Coordinate(part.xCoordinates.get(i) + 1, part.yCoordinate + 1));
                adjacentCoords.add(new Coordinate(part.xCoordinates.get(i), part.yCoordinate + 1));
                adjacentCoords.add(new Coordinate(part.xCoordinates.get(i) - 1, part.yCoordinate + 1));
                adjacentCoords.add(new Coordinate(part.xCoordinates.get(i) - 1, part.yCoordinate));
            }
            boolean isConnectorAdjacentToPart = adjacentCoords.stream()
                    .anyMatch(field -> field.x == this.x && field.y == this.y);
            if (isConnectorAdjacentToPart) {
                this.adjacentParts.add(part);
            }
            return isConnectorAdjacentToPart;
        }

        public int getGearRatio() {
            return adjacentParts.get(0).getNumber() * adjacentParts.get(1).getNumber();
        }
    }

    private record Coordinate(int x, int y) {
    }

    private static class Part {
        String number;
        List<Integer> xCoordinates;
        int yCoordinate;

        public Part(int rowNumber) {
            this.number = "";
            this.xCoordinates = new ArrayList<>();
            this.yCoordinate = rowNumber;
        }

        int getNumber() {
            return Integer.parseInt(this.number);
        }

        void addRowElement(String row, int x) {
            this.xCoordinates.add(x);
            this.number = this.number + row.charAt(x);
        }
    }
}
