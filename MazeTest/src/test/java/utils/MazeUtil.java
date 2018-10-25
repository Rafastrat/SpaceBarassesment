package utils;

import components.Coordinate;
import components.CoordinateStatus;

public class MazeUtil {

    public static Coordinate findSquareByState(Coordinate[][] squares, final CoordinateStatus status) {

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                Coordinate s = squares[i][j];
                if (s.getStatus() == status)
                    return s;
            }
        }
        return null;
    }
}
