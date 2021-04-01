package maze;

import stackqueue.ArrayQueue;

public class MinLengthMaze {
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

    public MinLengthMaze(int x, int y) {
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

    class Point {
        int x, y;
        int length;  // 出发点到该点的最短距离
        Point previous;// 到达该点的上一个节点

        Point(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }
    }

    public int minLength(int x, int y, int targetX, int targetY) {
        ArrayQueue<Point> queue = new ArrayQueue<>();
        queue.offer(new Point(0, 0, 0));
        mark[0][0] = 1;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int i = poll.x, j = poll.y, g, h, length = poll.length;
            for (int k = 0; k < 8; k++) {
                g = i + moves[k].x;
                h = j + moves[k].y;
                if (g == targetX && h == targetY) {

                    while (poll != null) {
                        System.out.println("(" + poll.x + ", " + poll.y + ")");
                        poll = poll.previous;
                    }
                    //输出
                    return length + 1;
                }
                if (g >= 0 && g < targetX && h >= 0 && h < targetY && maze[g][h] == 0 && mark[g][h] == 0) {
                    mark[g][h] = 1;
                    Point p = new Point(g, h, length + 1);
                    p.previous = poll;
                    queue.offer(p);
                }
            }
        }
        return -1; // 说明未找到路径
    }

    public static void main(String[] args) {
        MinLengthMaze maze = new MinLengthMaze(10, 10);
        maze.initMove();
        int minLength = maze.minLength(0, 0, 10, 10);
        System.out.println(minLength);
    }
}
