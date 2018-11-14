package 矩阵连乘;

import java.util.Scanner;

/**
 * 动态规划迭代实现
 * Created by EthanWalker on 2017/11/27.
 */
public class MatrixChain {

    // s[i][j] 的值表示 第i~j 个矩阵连乘时，最小的连乘次数
    private static int[][] s;

    private static int[] p;

    // div[i][j] 的值 value 表示 第i~j 个矩阵连乘时，在第 value 个矩阵断开 才能取得最小的连乘次数
    private static int[][] div;

    //
    static void matrixChain(int i, int j) {
        for (int sep = 1; sep <= j - i; sep++) {
            // 间距逐渐增大计算 (s[i][i+1] -> s[i][i+2] ) 因为 s[i][i+1] 可以直接算出, s[i][i+2] 在s[i][i+1]的基础上算出
            for (int k = i; k + sep <= j; k ++) {
                // 计算 s[k][k+sep]     即s[1][1+sep] s[2][2+sep] s[3][3+sep]

                int min = s[k][k] + s[k + 1][k + sep] + p[k - 1] * p[k] * p[k + sep];
                div[k][k + sep]= k;
                for (int t = 1; t < sep; t++) {
                    int temp = s[k][k + t] + s[k + t + 1][k + sep] + p[k - 1] * p[k+t] * p[k + sep];
                    if (temp < min) {
                        min = temp;
                        div[k][k + sep] = k + t;
                    }
                }
                s[k][k + sep] = min;
            }
        }
    }

    // s[i][i+1] = i ,       =>   i x i    and (i+1)*(i+1)
    public static void printJoin(int i, int j) {
        if (i == j||j-i==1) {
            return;
        }
        if (s[i][j] != 0) {
            printJoin(i, div[i][j]);
            printJoin(div[i][j] + 1, j);
        }
        System.out.print("( " + i + " x " + div[i][j] + " )   and   ");
        System.out.println("( " + (div[i][j] + 1) + " x " + j + " )");
    }
    public static void main(String[] args) {

        // i = 1, j = 6
        s = new int[7][7];
        div = new int[7][7];
        p = new int[7];
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                s[i][j] = 0;
                div[i][j] = 0;
            }
        }
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < p.length; i++) {
            p[i] = scanner.nextInt();
        }

        matrixChain(1, 6);
        System.out.println("最优乘次为: "+s[1][6]);
        System.out.println("1,6 在第 "+ div[1][6] +"个矩阵处分割");
        printJoin(1,6);
    }
}


//  3 5 6 2 4 8 7
//30 35 15 5 10 20 25
//15125