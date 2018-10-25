package components;

public enum CoordinateStatus {
    START_POINT,
    CLEAR,
    VISITED,
    BLOCKED,
    GOAL,
    UNKNOWN,
    CoordinateStatus() {};

    public static CoordinateStatus getSquareState(final char option) {
        if (' ' == option)
            return CoordinateStatus.CLEAR;
        else if ('X' == option)
            return CoordinateStatus.BLOCKED;
        else if ('V' == option)
            return CoordinateStatus.VISITED;
        else if ('S' == option)
            return CoordinateStatus.START_POINT;
        else if ('F' == option)
            return CoordinateStatus.GOAL;
        else
            return CoordinateStatus.UNKNOWN;
    }
}
