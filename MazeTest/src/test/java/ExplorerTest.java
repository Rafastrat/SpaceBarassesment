import components.Coordinate;
import components.CoordinateStatus;
import explorerbot.ExplorerBot;
import maze.Maze;
import maze.MazeFactory;
import maze.builder.FileMazeBuilder;
import maze.builder.MazeBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.MazeUtil;
import utils.TestUtil;

import java.util.Set;

public class ExplorerTest {

    private Maze maze;
    private ExplorerBot ex;

    @Before
    public void before() {
        resetMaze();
    }

    @Test
    public void testExplorerHasMaze() {
        Object m = TestUtil.getField(ex, "maze", Maze.class);

        Assert.assertNotNull(m);
        Assert.assertTrue(m instanceof Maze);
    }

    @Test
    public void testExplorerCanBeDroppedAtStartPoint() {
        Coordinate startSquare = maze.getStartPoint();
        Assert.assertEquals(3, startSquare.getRow());
        Assert.assertEquals(3, startSquare.getColumn());

        ex.exploreMaze();

        String finalPathString = ex.printVisitedCoordsByExplorerBot();
        Coordinate[][] outputPath = MazeFactory.buildMazeWithString(finalPathString);

        Coordinate outputSquare = MazeUtil.findSquareByState(outputPath, CoordinateStatus.START_POINT);
        Assert.assertEquals(3, outputSquare.getRow());
        Assert.assertEquals(3, outputSquare.getColumn());

        Assert.assertEquals(outputSquare, startSquare);
    }

    @Test
    public void testExplorerCanTurn() {
        TestUtil.setExitSquare(maze.getCoordinate(), maze.getGoalCoordinate(), 5, 11, CoordinateStatus.GOAL);
        ex.exploreMaze();

        Assert.assertEquals(11, maze.getPath().getPaths().size());
        Assert.assertTrue(maze.getPath().getPaths().contains(new Coordinate(3, 11, CoordinateStatus.CLEAR))); // last straight square
        Assert.assertTrue(maze.getPath().getPaths().contains(new Coordinate(4, 11, CoordinateStatus.CLEAR))); // first square after turned.
    }


    @Test
    public void testMovementOptionsForChosenLocation() {
        Set<Coordinate> options = ex.getOpenRoutes(maze, 6, 11);
        Assert.assertNotNull(options);
        Assert.assertEquals(3, options.size());
        Assert.assertTrue(options.contains(new Coordinate(5, 11, CoordinateStatus.CLEAR)));// left open square
        Assert.assertTrue(options.contains(new Coordinate(6, 10, CoordinateStatus.CLEAR)));// left open square
        Assert.assertTrue(options.contains(new Coordinate(7, 11, CoordinateStatus.CLEAR)));// down open square
    }

    @Test
    public void testExplorerFaceDeadAndHappyPaths() {
        TestUtil.setExitSquare(maze.getCoordinate(), maze.getGoalCoordinate(), 6, 8, CoordinateStatus.GOAL);
        ex.exploreMaze();
        Assert.assertFalse(maze.getPath().getPaths().contains(new Coordinate(6, 12, CoordinateStatus.CLEAR))); // final path doesn't contain the squares lead to the dead end.
    }


    @Test
    public void testExplorerKeepsRecordOfExploredPath() {
        ex.exploreMaze();
        Assert.assertNotNull(maze.getExplored());
        Assert.assertTrue(maze.getExplored().length > 0);
    }

    @Test
    public void testWallBetweenStartAndExitShouldHaveNoSolution() {
        TestUtil.setSquare(maze.getCoordinate(), 3, 4, CoordinateStatus.BLOCKED);
        TestUtil.setSquare(maze.getCoordinate(), 3, 5, CoordinateStatus.GOAL);
        TestUtil.setSquare(maze.getCoordinate(), 14, 1, CoordinateStatus.BLOCKED);

        ex.exploreMaze();
        Assert.assertNotNull(maze.getPath().getPaths());
        Assert.assertEquals(0, maze.getPath().getPaths().size());
    }

    private void resetMaze() {
        MazeBuilder builder = new FileMazeBuilder("src/test/resources/maze.txt");
        maze = builder.build();
        ex = new ExplorerBot(maze);
    }
}
