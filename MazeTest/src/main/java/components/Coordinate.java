package components;

import java.util.Objects;

public class Coordinate {

    protected int column;
    protected int row;
    protected CoordinateStatus status;

    public Coordinate(int row, int column, char sign) {
        this.column = column;
        this.row = row;
        this.status = CoordinateStatus.getSquareState(sign);
    }

    public Coordinate(int row, int column, CoordinateStatus status) {
        this.column = column;
        this.row = row;
        this.status = status;
    }

    public boolean isStepable() {
        return status == CoordinateStatus.START_POINT ||
                status == CoordinateStatus.CLEAR ||
                status == CoordinateStatus.GOAL;
    }

    public boolean isStart() {
        return status == CoordinateStatus.START_POINT;
    }

    public boolean isGoal() {
        return status == CoordinateStatus.GOAL;
    }

    public boolean isBlocked() {
        return status == CoordinateStatus.BLOCKED;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public CoordinateStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ", " + status + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return row == that.row &&
                column == that.column &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, status);
    }
}
