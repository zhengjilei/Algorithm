package queen;

import java.util.ArrayDeque;

/**
 * 任意两个皇后不能在同一行、同一列、同一斜线上
 * Created by Ethan-Walker on 2018/7/28.
 */
public class Queen {

    static int n; // 皇后数量，即棋盘的 长宽

    static boolean[] visitedColumn;   // visitedColumn[0] = true, 表示第 0 列已被访问过 ， 保证各皇后不在同一列
    static int[] rowColumn;     // rowColumn[i] = j  表示第 i 行的皇后在第 j 列， 目的是存储各行放置的皇后位置，试探下一行时，判断会不会构成对角线

    static int count = 0;
    static ArrayDeque<String> stack = new ArrayDeque<>();

    static boolean dfs(int x) {
        // x 表示 第 x 行
        if (x == n) return true;

        for (int y = 0; y < n; y++) {
            // y 代表 第 y 列，对第 y 列 进行试探
            if (visitedColumn[y] == false && isNotDuijiao(x, y)) {
                // 未访问过的列 & 列 != 已经放置的皇后的斜线上, 满足条件

                visitedColumn[y] = true;
                rowColumn[x] = y;
                stack.push("(" + x + "," + y + ")");


                if (dfs(x + 1)) {
                    // 递归试探下一行, 如果最终返回 true ，表示得到正确结果，逆向打印栈中路径
                    printStack();
                    count++;
                }

                //  回退（回退的目的：1. 下一次对 x 行进行试探的时候，保证该列设为没访问过 2. ）
                visitedColumn[y] = false;
                stack.pop();

            }
            // 继续试探下一列
        }
        // 执行到这一步，表示 第 x 行所有的列试探结束，返回false
        return false;
    }

    /**
     * 判断试探的坐标 x,y 和 x 行之前排布的皇后是否成对角线
     *
     * @param x
     * @param y
     * @return
     */
    private static boolean isNotDuijiao(int x, int y) {
        int j = 0;
        for (int i = 0; i < x; i++) {
            j = rowColumn[i];
            if (x - i == y - j || (x - i + y - j) == 0) return false; // 是对角线，返回 false
        }
        return true;
    }

    // 双栈打印路径
    private static void printStack() {
        ArrayDeque<String> tempStack = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
        }
        while (!tempStack.isEmpty()) {
            String pop = tempStack.pop();
            System.out.print(pop + "——>");
            stack.push(pop);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        n = 8;
        visitedColumn = new boolean[n];
        rowColumn = new int[n];
        for (int i = 0; i < n; i++) {
            visitedColumn[i] = false;
        }

        dfs(0);
        System.out.println(count);
    }
}
