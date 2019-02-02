package bytedance;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/2/2
 */
public class Q695_MaxArea {


    class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    //  dfs
    // 使用队列存储当前加入陆地的点，队头弹出点，遍历该点的四个方向，看是否能加入
    public int maxAreaOfIsland(int[][] grid) {

        int maxArea = 0;
        int row = grid.length, col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        int cnt = 0;
        Pos pos = null;
        ArrayDeque<Pos> queue = new ArrayDeque<>();
        Pos[] dir = new Pos[4];
        dir[0] = new Pos(0, 1);
        dir[1] = new Pos(1, 0);
        dir[2] = new Pos(-1, 0);
        dir[3] = new Pos(0, -1);
        int newX, newY;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1 && visited[i][j] == false) {
                    cnt = 1;
                    queue.offer(new Pos(i, j));
                    visited[i][j] = true;

                    // grid[i][j] 向四个方向查找
                    while (!queue.isEmpty()) {
                        pos = queue.pollFirst();
                        for (int k = 0; k < 4; k++) {
                            newX = pos.x + dir[k].x;
                            newY = pos.y + dir[k].y;
                            if (newX < row && newX >= 0 && newY < col && newY >= 0 && grid[newX][newY] == 1 && !visited[newX][newY]) {
                                cnt++;
                                queue.offerLast(new Pos(newX, newY));
                                visited[newX][newY] = true;
                            }
                        }
                    }
                    if (cnt > maxArea) {
                        maxArea = cnt;
                    }
                }
            }
        }
        return maxArea;
    }

    @Test
    public void test() {
        int[][] area = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };


        System.out.println(maxAreaOfIsland(area));
    }
}
