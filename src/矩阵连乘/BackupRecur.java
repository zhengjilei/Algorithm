package 矩阵连乘;

import java.util.Scanner;

/**
 * Created by EthanWalker on 2017/11/27.
 */
public class BackupRecur {

    // k= div[i][j] 代表 第i~j 个矩阵连乘时，在第 k 个矩阵断开 得到最小计算次数 (A1A2..Ak)(Ak+1 Ak+2)
    // s[i][i+1]  = i
    private static int[][] div;

//    矩阵行列值 (p[i] 代表第 i 个矩阵的列值, p[i-1]代表第 i 个矩阵的行值 )

    private static int[] p;

    // s[i][j] 表示 i~j 个矩阵连乘时的最小计算次数
    private static int[][] s;
    /**
     * 第 i~j 个矩阵连乘, 返回最小的计算次数
     *
     * @param i
     * @param j
     * @return
     */
    static int recurMatrixChain(int i, int j) {

        if(s[i][j]!=0){
            return s[i][j];
        }
        if (i == j) return 0;
        div[i][j] = i;
        //第 i 个矩阵处断开
        int u = recurMatrixChain(i, i) + recurMatrixChain(i + 1, j) + p[i - 1] * p[i] * p[j];
        for (int k = i + 1; k < j; k++) {
            // 第 k 个矩阵处断开
            int t = recurMatrixChain(i, k) + recurMatrixChain(k + 1, j) + p[i - 1] * p[k] * p[j];
            if (t < u) {
                u = t;
                div[i][j] = k;
            }
        }
        s[i][j] = u;
        return u;
    }

    public static void main(String[] args) {
        div = new int[7][7];
        s = new int[7][7];
        p = new int[7];
        for (int i = 0; i < div.length; i++) {
            for (int j = 0; j < div[i].length; j++) {
                div[i][j] = 0;
                s[i][j]=0;
            }
        }
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < p.length; i++) {
            p[i] = scanner.nextInt();
        }
        int r = recurMatrixChain(1, 6);
        System.out.println(r);

        printJoin(1, 6);
    /*    System.out.println("-------------");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(div[i][j]+" "); //"div["+i+"]["+j+"]"+
            }
            System.out.println();
        }*/

    }

    // s[i][i+1] = i ,       =>   i x i    and (i+1)*(i+1)
    public static void printJoin(int i, int j) {
        if (i == j || j - i == 1) {
            return;
        }
        if (div[i][j] != 0) {
            printJoin(i, div[i][j]);
            printJoin(div[i][j] + 1, j);
        }
        System.out.print("( " + i + " x " + div[i][j] + " )   and   ");
        System.out.println("( " + (div[i][j] + 1) + " x " + j + " )");
    }

}