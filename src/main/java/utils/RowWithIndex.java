package utils;

public record RowWithIndex(String row, int index) {
    public int getRowLength() {
        return this.row.length();
    }

    public char getChar(int i) {
        return this.row.charAt(i);
    }
}
