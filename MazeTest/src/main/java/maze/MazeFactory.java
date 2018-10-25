package maze;

import components.Coordinate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MazeFactory {

    public static Coordinate[][] buildMazeWithString(String inputString) {
        if (inputString == null)
            return null;

        String[] rows = inputString.split("[\r]?\n");
        int height = rows.length;
        int width = rows[0].length();
        int startCount = 0;
        int goalCount = 0;
        Coordinate[][] coordinates = new Coordinate[height][width];

        for (int row = 0; row < height; row++) {
            if (rows[row].length() != width) {
                throw new IllegalArgumentException("Row: " + (row + 1) + " has wrong length ("
                        + rows[row].length() + "), the length should be: " + width);
            }

            for (int col = 0; col < width; col++) {
                Coordinate coordinate = new Coordinate(row, col, rows[row].charAt(col));
                coordinates[row][col] = coordinate;
                if (coordinate.isGoal()) {
                    goalCount++;
                }
                if (coordinate.isStart()) {
                    startCount++;
                }

            }
        }

        if (startCount != 1 || goalCount != 1) {
            throw new IllegalArgumentException("Map not valid - Just one start point and goal is allowed");
        }

        return coordinates;
    }

    public static Coordinate[][] buildMazeMap(File inputFile) {
        if (inputFile == null || !inputFile.exists()){
            throw new IllegalArgumentException("Maze File cannot be null");
        }

        System.out.println(inputFile.getAbsoluteFile());
        return buildMazeWithString(readLine(inputFile));
    }

    private static String readLine(File inputFile) {
        String line;
        String mazeText = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            while ((line = br.readLine()) != null) {
                mazeText += line + "\n";
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mazeText == null || mazeText.trim().length() == 0) {
            throw new IllegalArgumentException("The file cannot be empty");
        }
        return mazeText;
    }
}
