import explorerbot.ExplorerBot;
import maze.Maze;
import maze.builder.FileMazeBuilder;
import maze.builder.MazeBuilder;

public class AppMain {
    public static void main(String[] args) {
        MazeBuilder builder = new FileMazeBuilder("MazeTest/src/main/resources/maze.txt");
        Maze maze = builder.build();
        System.out.println(maze);//initial maze
        ExplorerBot e = new ExplorerBot(maze);
        e.exploreMaze();
    }
}
