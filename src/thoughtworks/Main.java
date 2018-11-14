package thoughtworks;

import java.util.Scanner;

/**
 * Created by ZhengJilei on 2018/7/29.
 */
public class Main {
    public static void main(String[] args) {
//      String command = "3 3" + System.lineSeparator() + "0,1 0,2;0,0 1,0;0,1 1,1;0,2 1,2;1,0 1,1;1,1 1,2;1,1 2,1;1,2 2,2;2,0 2,1";

        Scanner sc = new Scanner(System.in);
        String firstLine = sc.nextLine();
        String secondLine = sc.nextLine();

        String command = new String(firstLine);
        command += System.lineSeparator();
        command += secondLine;

        Maze maze = MazeFactory.createMaze(command);
        String render = maze.render();
        System.out.print(render);
    }

}
