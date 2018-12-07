package maze;

import java.util.ArrayDeque;
import java.util.Random;


/**
 * Created by Ethan-Walker on 2018/7/22.
 */
public class Maze {

    private int[][] maze;
    private int[][] mark;
    private Move[] move;

    private class Move {
        int x;
        int y;
        String dir;

        Move(int x, int y, String dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    private class Item {
        int x, y, dir;
    }

    public void initMaze(int x, int y) {
        maze = new int[x + 1][y + 1];
        mark = new int[x + 1][y + 1];

        Random random = new Random();
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                maze[i][j] = Math.abs(random.nextInt()) % 2;
                System.out.print(maze[i][j] + "\t");
                mark[i][j] = 0;
            }
            System.out.println();
        }
        mark[0][0] = 1;

        move = new Move[8];
        move[0] = new Move(-1, 0, "up ");
        move[1] = new Move(-1, 1, "up right");
        move[2] = new Move(0, 1, "right");
        move[3] = new Move(1, 1, "down right");
        move[4] = new Move(1, 0, "down");
        move[5] = new Move(1, -1, "down left");
        move[6] = new Move(0, -1, "left");
        move[7] = new Move(-1, -1, "up left");
    }

    public void initMaze2(int x, int y) {
        maze = new int[][]{
                {1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1},
                {0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0},
                {0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                {1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1},
                {0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1},
                {0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0}
        };
        mark = new int[x + 1][y + 1];
        mark[0][0] = 1;
        move = new Move[8];
        // 右下角优先试探
        move[5] = new Move(-1, 0, "up ");
        move[4] = new Move(-1, 1, "up right");
        move[1] = new Move(0, 1, "right");
        move[0] = new Move(1, 1, "down right");
        move[2] = new Move(1, 0, "down");
        move[3] = new Move(1, -1, "down left");
        move[6] = new Move(0, -1, "left");
        move[7] = new Move(-1, -1, "up left");
    }

    public void resetMark(int x, int y) {
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                mark[i][j] = 0;
            }
        }
        mark[0][0] = 1;
    }

    /**
     * 递归求解，只能寻找到一个解，只能逆序输出路径
     */
    public boolean seekPath(int x, int y, int targetX, int targetY) {
        if (x == targetX && y == targetY) {
            return true;
        }
        int tempX, tempY;
        String dir;
        for (int i = 0; i < 8; i++) {
            // 每个方向试探
            tempX = x + move[i].x;
            tempY = y + move[i].y;
            dir = move[i].dir;

            if (tempX >= 0 && tempY >= 0 && tempX <= targetX && tempY <= targetY && maze[tempX][tempY] == 0 && mark[tempX][tempY] == 0) {
                mark[tempX][tempY] = 1;
                if (seekPath(tempX, tempY, targetX, targetY)) {
                    System.out.println("(" + tempX + "," + tempY + ", " + dir + ")"); // 逆向输出路径
                    return true;
                }
            }
        }
        if (x == 0 && y == 0) {
            System.out.println("no path");
        }
        return false;
    }

    public void stackSeekPath(int x, int y, int targetX, int targetY) {
        ArrayDeque<Item> stack = new ArrayDeque<>();
        Item start = new Item();
        start.x = x;
        start.y = y;
        start.dir = 0;
        stack.push(start);
        while (!stack.isEmpty()) {
            Item pop = stack.pop();
            int i = pop.x, j = pop.y, dirNum = pop.dir;
            while (dirNum < 8) {
                // 试探 8 个方向
                int g = i + move[dirNum].x;
                int h = j + move[dirNum].y;
                if (g == targetX && h == targetY) {
                    System.out.println("(" + targetX + "," + targetY + move[dirNum].dir + " )");
                    print(stack);
                    return ;
                }
                if (g >= 0 && h >= 0 && g <= targetX && h <= targetY && maze[g][h] == 0 && mark[g][h] == 0) {
                    mark[g][h] = 1;
                    Item item = new Item();
                    item.x = g;
                    item.y = h;
                    item.dir = dirNum; // 到达该店应该走的方向
                    stack.push(item);

                    // 进入到 g、h 点
                    i = g;
                    j = h;
                    dirNum = 0;
                } else {
                    dirNum++;
                }
            }
        }
        System.out.println("no path");
    }

    public void print(ArrayDeque<Item> stack) {

        while (!stack.isEmpty()) {
            Item pop = stack.pop();
            System.out.println("(" + pop.x + "," + pop.y + "," + move[pop.dir].dir + ")");
        }
    }

    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.initMaze2(10, 10);
//        maze.seekPath(0, 0, 10, 10);
//        maze.resetMark(10, 10);
//        System.out.println("--------------------");
        maze.stackSeekPath(0, 0, 10, 10);
    }
}
