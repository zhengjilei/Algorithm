package tecent1;

import org.junit.Test;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Ethan-Walker on 2018/4/15.
 */
public class Maze {


    static int count = 0;
    static int destX, destY;
    static int[][] mark = new int[100][100];
    static Stack<Point> path, temp;

    static int[][] dir = {{0, 1}, {1, 0}}; // 0,1表示向上，1,0 表示向右

    static class Point {
        int x, y;
    }

    static Point point = new Point();

    static void dfs(int x, int y) {
        if (x == destX && y == destY) {
            System.out.print("路径 " + (count++) + ": ");

            while (!path.empty()) {
                Point p = path.pop();
                temp.push(p);
            }
            while (!temp.empty()) {
                Point p = temp.pop();
                System.out.print("(" + p.x + "," + p.y + ")   ");
                path.push(p); // 路径放回到 path 中
            }
            System.out.println();
            return;
        }
        if (x < 0 || x > destX || y < 0 || y > destY)
            return;
        for (int i = 0; i < 2; i++) {
            // 从上、右 两个方向探测
            int tempX = x + dir[i][0];
            int tempY = y + dir[i][1];

            if (tempX <= destX && tempY <= destY && mark[tempX][tempY] == 0) {
                mark[tempX][tempY] = 1; // 探查，标记为访问过
                Point p = new Point();
                p.x = tempX;
                p.y = tempY;
                path.push(p);
                dfs(tempX, tempY);

                mark[tempX][tempY] = 0;
                path.pop();
            }
        }
    }

    @Test
    public void testA(){
        for(int i=0;i<100;i++){
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        destX = sc.nextInt();
        destY = sc.nextInt();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                mark[i][j] = 0;
            }
        }
        path = new Stack<>();
        temp = new Stack<>();
        point.x = 0;
        point.y = 0;
        path.push(point);

        dfs(0, 0);


    }
}
