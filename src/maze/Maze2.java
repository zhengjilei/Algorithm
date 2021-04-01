package maze;

import java.util.ArrayDeque;

public class Maze2 {
    private Move[] moves;
    private int[][] maze;
    private int[][] mark;

    class Move {

        int x;
        int y;
        String dir;

        public Move(int x, int y, String dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public Maze2(int x, int y) {
/*        maze = new int[][]{
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
        };*/
        maze = new int[][]{
                {0, 1, 1, 0, 0, 1},
                {1, 0, 1, 1, 0, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 0, 0, 1, 1, 0},
                {1, 1, 1, 0, 1, 0},
                {1, 1, 1, 1, 1, 0}
        };
        mark = new int[x + 1][y + 1];
        mark[0][0] = 1;
    }

    public void initMove() {
        moves = new Move[8];
        moves[0] = new Move(1, 1, "down right");
        moves[1] = new Move(1, 0, "down");
        moves[2] = new Move(0, 1, "right");
        moves[3] = new Move(-1, 1, "up right");
        moves[4] = new Move(1, -1, "down left");
        moves[5] = new Move(-1, 0, "up");
        moves[6] = new Move(0, -1, "left");
        moves[7] = new Move(-1, -1, "up left");
    }

    public boolean seekPath(int x, int y, int targetX, int targetY) {
        if (x == targetX && y == targetY) {
            System.out.println("(" + x + "," + y + ")");
            return true;
        }
        int i, j;
        String dir;
        for (int k = 0; k < 8; k++) {
            i = x + moves[k].x;
            j = y + moves[k].y;
            dir = moves[k].dir;
            if (i >= 0 && j >= 0 && i <= targetX && j <= targetY && maze[i][j] == 0 && mark[i][j] == 0) {
                mark[i][j] = 1;
                if (seekPath(i, j, targetX, targetY)) {
                    System.out.println("(" + x + "," + y + "," + dir + ")");
                    return true;
                }
            }

        }
        System.out.println("no path");

        return false;
    }

    class Item {
        int x, y, dirNum; // dirNum 表示从当前点的前进方向

        public Item(int a, int b, int c) {
            x = a;
            y = b;
            dirNum = c;
        }
    }

    /**
     * 由于栈中压的项很可能要取出来，然后继续根据该项栈中的方向值 遍历下一个方向，所以新建类，方向类型为 int 型
     */
    public boolean seekPathByStack(int x, int y, int targetX, int targetY) {
        ArrayDeque<Item> stack = new ArrayDeque<>();
        stack.push(new Item(x, y, -1));
        while (!stack.isEmpty()) {
            Item pop = stack.pop();
            int i = pop.x, j = pop.y, dirNum = pop.dirNum + 1; // 之前已经进入的旧方向+1
            while (dirNum < 8) {
                int g = i + moves[dirNum].x, h = j + moves[dirNum].y;
                if (g == targetX && h == targetY) {
                    System.out.println("(" + targetX + "," + targetY + ")");
                    stack.push(new Item(i, j, dirNum));
                    print(stack);
                    return true;
                }
                if (g >= 0 && h >= 0 && g <= targetX && h <= targetY && maze[g][h] == 0 && mark[g][h] == 0) {
                    stack.push(new Item(i, j, dirNum));
                    mark[g][h] = 1;
                    // 关键的几步，进入到压的点处
                    i = g;
                    j = h;
                    dirNum = 0;
                } else {
                    dirNum++;
                }
            }
        }

        return false;
    }

    void print(ArrayDeque<Item> stack) {
        while (!stack.isEmpty()) {
            Item pop = stack.pop();
            System.out.println("(" + pop.x + "," + pop.y + "," + moves[pop.dirNum].dir + ")");
        }
    }

    public static void main(String[] args) {
        Maze2 maze = new Maze2(5, 5);
        maze.initMove();
//        maze.seekPath(0, 0, 10, 10);
        maze.seekPathByStack(0, 0, 5, 5);
    }

}
