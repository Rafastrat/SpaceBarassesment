package explorerbot;

import components.Coordinate;
import maze.Maze;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ExplorerBot {

    private boolean done;
    private Maze maze;

    public ExplorerBot(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException("There is no maze to explore");
        }

        this.maze = maze;
    }

    public void exploreMaze() {
        Coordinate startSquare = maze.getStartPoint();
        if (move(startSquare.getRow(), startSquare.getColumn())) {
            System.out.println(maze);//print maze when is explored
        }
    }

    public String printVisitedCoordsByExplorerBot() {
        Coordinate[][] map = maze.getCoordinate();
        StringBuilder result = new StringBuilder(maze.getWidth() * maze.getHeight());
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {

                if (map[row][col].isBlocked()) {
                    result.append('X');
                } else if (map[row][col].isStart()) {
                    result.append('S');
                } else if (map[row][col].isGoal()) {
                    result.append('F');
                } else if (maze.getPath().isOnPath(maze.getCoordinate(row, col))) {
                    result.append('.');
                } else {
                    result.append(' ');
                }
            }
            result.append('\n');
        }
        System.out.println(result.toString());
        return result.toString();
    }


    public Coordinate getPreviousExploredStep() {
        return maze.getPath().getPreviousStep();
    }


    private boolean move(final int row, final int col) {

        Coordinate currentSquare = maze.getCoordinate(row, col);

        if (currentSquare.isGoal()) {//Goal found
            maze.markAsVisited(row, col, true);
            done = true;
            return true;
        } else {
            maze.markAsVisited(row, col, true);
            Coordinate nextSquare = turn(row, col);

            if (nextSquare == null) {//Blocked, going back til found another route
                Coordinate previousExplored = getPreviousExploredStep();
                if (previousExplored != null && move(previousExplored.getRow(), previousExplored.getColumn())) {
                    return !done;
                }
            } else {//Step forward
                if (move(nextSquare.getRow(), nextSquare.getColumn())) {
                    printVisitedCoordsByExplorerBot();
                    return !done;
                }
            }
        }
        return false;
    }

    private Coordinate turn(final int row, final int col) {
        Set<Coordinate> clearCoordinates = getOpenRoutes(maze, row, col);
        if (clearCoordinates.size() > 0) {
            Random generator = new Random();
            int index = generator.nextInt(clearCoordinates.size());
            return clearCoordinates.toArray(new Coordinate[clearCoordinates.size()])[index];
        }
        return null;
    }

    public Set<Coordinate> getOpenRoutes(Maze maze, final int currentRow, final int currentCol) {
        Set<Coordinate> clearCoordinates = new HashSet<Coordinate>();

        for (int i = 0; i <= 3; i++) {
            if (i == 0 && maze.getCoordinate(currentRow, currentCol - 1).isStepable() &&
                    !maze.isVisited(currentRow, currentCol - 1)) {
                clearCoordinates.add(maze.getCoordinate(currentRow, currentCol - 1));
            } else if (i == 1 && maze.getCoordinate(currentRow - 1, currentCol).isStepable() &&
                    !maze.isVisited(currentRow - 1, currentCol)) {
                clearCoordinates.add(maze.getCoordinate(currentRow - 1, currentCol));
            } else if (i == 2 && maze.getCoordinate(currentRow + 1, currentCol).isStepable() &&
                    !maze.isVisited(currentRow + 1, currentCol)) {
                clearCoordinates.add(maze.getCoordinate(currentRow + 1, currentCol));
            } else if (i == 3 && maze.getCoordinate(currentRow, currentCol + 1).isStepable() &&
                    !maze.isVisited(currentRow, currentCol + 1)) {
                clearCoordinates.add(maze.getCoordinate(currentRow, currentCol + 1));
            }
        }
        return clearCoordinates;
    }
}
