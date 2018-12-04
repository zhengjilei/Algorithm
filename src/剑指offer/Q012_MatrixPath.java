package 剑指offer;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2018/12/3
 */
public class Q012_MatrixPath {
    Move[] moves;
    boolean[][] visited;
    ArrayDeque<Pos> stack = new ArrayDeque<>();

    void init() {
        moves = new Move[4];
        moves[3] = new Move(0, 1, "down");
        moves[2] = new Move(1, 0, "right");
        moves[1] = new Move(-1, 0, "left");
        moves[0] = new Move(0, -1, "up");
    }

    public boolean getMatrixPath(String s, char[][] chs) {
        int row = chs.length, col = chs[0].length;
        visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                for (int k = 0; k < row; k++) {
                    for (int q = 0; q < col; q++) {
                        if (visited[k][q]) {
                            visited[k][q] = false;
                        }
                    }
                }
                if (getMatrixPath2(s, chs, 0, visited, i, j)) {
                    while (!stack.isEmpty()) {
                        Pos p = stack.pop();
                        System.out.print("(" + p.x + "," + p.y + ")  ");
                    }
                    System.out.println();
                    return true;
                }
            }
        }
        return false;

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

    class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private boolean getMatrixPath(String s, char[][] chs, int index, boolean[][] visited, int i, int j) {
        if (index == s.length()) {
            return true;
        }
        if (i < 0 || i >= chs.length || j < 0 || j >= chs[0].length) return false;


        if (visited[i][j] == false && s.charAt(index) == chs[i][j]) {
            visited[i][j] = true;
            stack.push(new Pos(i, j));
            for (int k = 0; k < 4; k++) {
                if (getMatrixPath(s, chs, index + 1, visited, i + moves[k].x, j + moves[k].y)) {
                    // 返回 true 表明找到成功路径了   false 表示当前路径不成功
                    return true;
                }
            }
            // 四个方向上 都探索失败，回退
            stack.pop();
            visited[i][j] = false;
        }
        return false;
    }

    public boolean getMatrixPath2(String s, char[][] chs, int index, boolean[][] visited, int i, int j) {
        if (index == s.length()) return true;
        if (i < 0 || i >= chs.length || j < 0 || j >= chs[0].length) return false;
        boolean hasPath = false;
        if (visited[i][j] == false && s.charAt(index) == chs[i][j]) {
            visited[i][j] = true;
            stack.push(new Pos(i, j));
            hasPath = getMatrixPath2(s, chs, index + 1, visited, i + 1, j)
                    || getMatrixPath2(s, chs, index + 1, visited, i, j + 1)
                    || getMatrixPath2(s, chs, index + 1, visited, i - 1, j)
                    || getMatrixPath2(s, chs, index + 1, visited, i, j - 1);
            if (!hasPath) {
                stack.pop();
                visited[i][j] = false;
            }
        }
        return hasPath;

    }

    public static void main(String[] args) {
        Q012_MatrixPath p = new Q012_MatrixPath();
        p.init();
//        char[][] chs = new char[][]{{'a', 'b', 't', 'g'}, {'c', 'f', 'c', 's'}, {'j', 'd', 'e', 'h'}};
        char[][] chs = new char[][]{{'a', 'b', 'f', 'e'}, {'m', 'f', 'g', 'k'}, {'b', 'k', 'c', 'd'}};
        System.out.println(p.getMatrixPath("bfekgf", chs));
    }
}
