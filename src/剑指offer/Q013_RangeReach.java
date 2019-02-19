package 剑指offer;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2018/12/4
 */
public class Q013_RangeReach {

    // 方法一: dfs
    public int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0) return 0;
        boolean[][] visited = new boolean[rows][cols];
        return move(threshold, rows, cols, 0, 0, visited);
    }

    public int move(int threshold, int rows, int cols, int i, int j, boolean[][] visited) {
        if (i >= 0 && i < rows && j >= 0 && j < cols && isValid(i, j, threshold) && !visited[i][j]) {
            visited[i][j] = true;
            return 1 + move(threshold, rows, cols, i + 1, j, visited)
                    + move(threshold, rows, cols, i - 1, j, visited)
                    + move(threshold, rows, cols, i, j + 1, visited)
                    + move(threshold, rows, cols, i, j - 1, visited);
        }
        return 0;
    }


    public boolean isValid(int i, int j, int threshold) {
        int count = 0;
        while (i != 0) {
            count += i % 10;
            i /= 10;
        }
        while (j != 0) {
            count += j % 10;
            j /= 10;
        }
        return count <= threshold;
    }


    // 方法二: bfs 队列
    class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

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

    Move[] moves = new Move[4];

    void init() {
        moves = new Move[4];
        moves[3] = new Move(0, 1, "down");
        moves[2] = new Move(1, 0, "right");
        moves[1] = new Move(-1, 0, "left");
        moves[0] = new Move(0, -1, "up");
    }

    // bfs
    public int movingCount2(int threshold, int rows, int cols) {
        ArrayDeque<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(0, 0));
        int count = 0;
        if (isValid(0, 0, threshold)) {  // 可能 threshold < 0
            count++;
        }
        init();
        boolean[][] visited = new boolean[rows][cols];
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Pos poll = queue.poll();
            for (int i = 0; i < moves.length; i++) {
                int x = poll.x + moves[i].x;
                int y = poll.y + moves[i].y;
                if (x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y] && isValid(x, y, threshold)) {
                    visited[x][y] = true;
                    count++;
                    queue.offer(new Pos(x, y));
                }
            }
        }
        return count;
    }


}
