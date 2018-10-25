import components.Coordinate;
import maze.MazeFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class MazeFactoryTest {

    private MazeFactory factory = new MazeFactory();

    @Test(expected = IllegalArgumentException.class)
    public void testFactoryNullInput() {
        factory.buildMazeMap(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactoryNonExistFileInput() {
        factory.buildMazeMap(new File("src/test"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactoryEmptyFileInput() {
        factory.buildMazeMap(new File("src/test/resources/empty.txt"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactoryBadFormattedFileInput() {
        factory.buildMazeMap(new File("src/test/resources/badcontent.txt"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactoryOneStart() {
        factory.buildMazeMap(new File("src/test/resources/multiple-starts.txt"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactoryOneExit() {
        factory.buildMazeMap(new File("src/test/resources/multiple-exits.txt"));
    }

    @Test
    public void testFactoryLoadMaze() {
        Coordinate[][] maze = factory.buildMazeMap(new File("src/test/resources/maze.txt"));

        Assert.assertNotNull(maze);
        Assert.assertEquals(15, maze.length);
        Assert.assertEquals(15, maze[0].length);
    }
}
