package 剑指offer;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 顺时针打印矩阵
 * created by Ethan-Walker on 2018/12/7
 */
public class Q029_PrintMatrixClockwisely {

    public ArrayList<Integer> print(int[][] a) {
        int dir = 0;
        Move[] moves = new Move[4];
        moves[0] = new Move(0, 1);
        moves[1] = new Move(1, 0);
        moves[2] = new Move(0, -1);
        moves[3] = new Move(-1, 0);

        int row = a.length, col = a[0].length;
        boolean[][] visited = new boolean[row][col];
        visited[0][0] = true;
        int x = 0, y = 0;
        int length = row * col, count = 1;
        int tempX, tempY;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(a[0][0]);

        while (count < length) {
            tempX = x + moves[dir].x;
            tempY = y + moves[dir].y;
            if (tempX < row && tempX >= 0 && tempY < col && tempY >= 0 && !visited[tempX][tempY]) {
                visited[x][y] = true;
                count++;
                x = tempX;
                y = tempY;
                list.add(a[x][y]);
            } else {
                dir = (dir + 1) % 4;
            }
        }
        return list;
    }


    class Move {
        int x, y;

        public Move(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    @Test
    public void test() {
        int[][] a = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20}
        };
        System.out.println(print(a));
    }
}
