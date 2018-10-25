import components.Coordinate;
import maze.builder.FileMazeBuilder;
import maze.builder.MazeBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileMazeBuilderTest {

    private MazeBuilder builder;

    @Test
    public void testNonExistFileShouldReturnNullMaze() {
        this.builder = new FileMazeBuilder("NON_EXIST");
        assertNull(builder.build());
    }

    @Test
    public void testShouldProduceMazeMap() {
        this.builder = new FileMazeBuilder("src/test/resources/maze.txt");
        Coordinate[][] square = builder.build().getCoordinate();
        assertNotNull(square);
        assertEquals(15, square.length);
        assertEquals(15, square[0].length);
    }
}
