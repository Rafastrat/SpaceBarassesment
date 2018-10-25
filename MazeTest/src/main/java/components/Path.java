package components;

import java.util.Stack;

public class Path {

    private int backwardsStepsCount = 0;
    private Stack<Coordinate> path;

    public Path() {
        path = new Stack<Coordinate>();
    }

    public int getBackwardsStepsCount() {
        return this.backwardsStepsCount;
    }

    public boolean isOnPath(Coordinate square) {
        return path.contains(square);
    }

    public void addPath(Coordinate square) {
        path.push(square);
    }

    public Stack<Coordinate> getPaths() {
        return path;
    }

    public Coordinate getPreviousStep() {
        backwardsStepsCount++;
        if (!path.isEmpty()){
            path.pop();
        }

        return path.isEmpty() ? null : path.pop();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Coordinate s : path) {
            builder.append("X " + s.getRow() + ":Y " + s.getColumn());
        }
        return builder.toString();
    }
}
