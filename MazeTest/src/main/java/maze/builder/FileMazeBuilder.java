package maze.builder;

import components.Coordinate;
import maze.Maze;
import maze.MazeFactory;

import java.io.File;

public class FileMazeBuilder implements MazeBuilder {

    private File file;

    @Override
    public Maze build() {
        try {
            Coordinate[][] map = MazeFactory.buildMazeMap(file);
            return new Maze(map);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public FileMazeBuilder(String filePath) {
        try {
            this.file = new File(filePath);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("We need a maze file");
        }
    }
}
