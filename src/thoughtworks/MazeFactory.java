package thoughtworks;

/**
 * Created by ZhengJilei on 2018/7/28.
 */
public class MazeFactory {

    public static Maze createMaze(String command) {
        Maze maze = new Maze();
        maze.setCommand(command);
        return maze;
    }


}

