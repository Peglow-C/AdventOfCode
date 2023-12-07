package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<String> readFile(final String filePath) {
        final Path path = Paths.get(String.format("src\\main\\resources\\" + filePath));
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Encountered an error while reading the file");
            return List.of();
        }
    }

    public static List<RowWithIndex> readFileWithLineIndex(final String filePath) {
        List<RowWithIndex> rowsWithIndex = new ArrayList<>();
        List<String> rows = readFile(filePath);
        for (int i = 0; i < rows.size(); i++) {
            rowsWithIndex.add(new RowWithIndex(rows.get(i), i));
        }
        return rowsWithIndex;
    }

    public static boolean isCharANumber(char c) {
        return Character.isDigit(c);
    }
}

