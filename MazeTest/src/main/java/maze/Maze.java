package maze;

import components.Coordinate;
import components.CoordinateStatus;
import components.Path;

import java.util.ArrayList;
import java.util.List;

public class Maze extends MazeFactory {

    private Coordinate[][] maze;
    private boolean[][] explored;
    private Path path;
    private int height;
    private int width;

    public Maze(final Coordinate[][] maze) {
        if (maze == null){
            throw new IllegalArgumentException("Maze map cannot be null");
        }

        this.maze = maze;
        this.path = new Path();
        this.height = maze.length;
        this.width = maze[0].length;
        this.explored = new boolean[height][width];
    }

    public Path getPath() {
        return path;
    }

    public void markAsVisited(int x, int y, boolean visited) {
        if (!validateCoordinates(x, y)){
            throw new IllegalArgumentException("Coordinates x: " + x + ", y: " + y + " are outside the maze");
        }

        explored[x][y] = visited;
        path.addPath(maze[x][y]);
    }

    public boolean isVisited(final int x, final int y) {
        return explored[x][y];
    }

    public boolean[][] getExplored() {
        return explored;
    }

    public Coordinate[][] getCoordinate() {
        return this.maze;
    }

    public Coordinate getCoordinate(final int x, final int y) {
        if (!validateCoordinates(x, y)){
            return null;
        }

        return maze[x][y];
    }

    public Coordinate getStartPoint() {
        List<Coordinate> startPoints = findCoordinate(CoordinateStatus.START_POINT);
        return startPoints.isEmpty() ? null : startPoints.get(0);
    }

    public Coordinate getGoalCoordinate() {
        List<Coordinate> goals = findCoordinate(CoordinateStatus.GOAL);
        return goals.isEmpty() ? null : goals.get(0);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getGoalCount() {
        return findCoordinate(CoordinateStatus.GOAL).size();
    }

    public int getClearCoordsCount() {
        return findCoordinate(CoordinateStatus.CLEAR).size();
    }

    public int getBlockedCount() {
        return findCoordinate(CoordinateStatus.BLOCKED).size();
    }

    public int getStartCount() {
        return findCoordinate(CoordinateStatus.START_POINT).size();
    }

    private List<Coordinate> findCoordinate(final CoordinateStatus targetStatus) {
        List<Coordinate> coordinates = new ArrayList();
        for (int height = 0; height < getHeight(); height++) {
            for (int width = 0; width < getWidth(); width++) {
                Coordinate s = getCoordinate(height, width);
                if (s.getStatus() == targetStatus)
                    coordinates.add(s);
            }
        }
        return coordinates;
    }

    private boolean validateCoordinates(int row, int col) {
        return !(row < 0 || row >= getHeight() || col < 0 || col >= getWidth());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getWidth() * getHeight());
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (maze[row][col].isBlocked()) {
                    result.append('X');
                } else if (maze[row][col].isStart()) {
                    result.append('S');
                } else if (maze[row][col].isGoal()) {
                    result.append('F');
                } else if (explored[row][col]) {
                    result.append('.');
                } else {
                    result.append(' ');
                }
            }
            result.append('\n');
        }
        return result.toString();
    }
}
