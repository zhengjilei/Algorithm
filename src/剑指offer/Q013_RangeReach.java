package 剑指offer;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2018/12/4
 */
public class Q013_RangeReach {

    Move[] dirs;

    class Move {
        int x, y;

        public Move(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    void init() {
        dirs = new Move[4];
        dirs[0] = new Move(0, 1);
        dirs[1] = new Move(1, 0);
        dirs[2] = new Move(0, -1);
        dirs[3] = new Move(-1, 0);
    }

    boolean[][] visited;
    int count;

    public int seek(int row, int col, int k) {
        visited = new boolean[row][col];
        return seek2(row, col, k, 0, 0);
    }

    class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 队列
    public int seek(int row, int col, int k, int x, int y) {
        ArrayDeque<Pos> queue = new ArrayDeque<>();
        queue.push(new Pos(x, y));
        visited[x][y] = true;
        count = 1;
        while (!queue.isEmpty()) {
            Pos pos = queue.poll();
            x = pos.x;
            y = pos.y;
            int p, q;
            for (int i = 0; i < 4; i++) {
                p = x + dirs[i].x;
                q = y + dirs[i].y;
                if (p >= 0 && p < row && q >= 0 && q < col && !visited[p][q]) {
                    visited[p][q] = true; // p,q 被访问过
                    if (calXYSum(p, q) <= 18) {
                        count++;
                        queue.push(new Pos(p, q));
                    } else {
                        System.out.println("(" + p + "," + q + ")");
                    }
                }
            }
        }
        return count;
    }

    public int seek2(int row, int col, int k, int p, int q) {
        int count = 0;
        if (p >= 0 && p < row && q >= 0 && q < col && !visited[p][q] && calXYSum(p, q) <= k) {
            // 满足条件
            visited[p][q] = true;
            count = 1 + seek2(row, col, k, p + 1, q) + seek2(row, col, k, p - 1, q) + seek2(row, col, k, p, q + 1) + seek2(row, col, k, p, q - 1);
        }
        return count;
    }


    public int calXYSum(int x, int y) {
        return calSum(x) + calSum(y);
    }

    public int calSum(int n) {
        int sum = 0;
        int e = 0;
        while (n != 0) {
            sum = sum + n % 10;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        Q013_RangeReach instance = new Q013_RangeReach();
        instance.init();
        System.out.println(instance.seek(39, 39, 18));
    }

}
