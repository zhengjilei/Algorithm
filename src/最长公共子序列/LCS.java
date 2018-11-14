package 最长公共子序列;

import java.util.Scanner;

/**
 * Created by EthanWalker on 2017/11/28.
 */
public class LCS {

    private static char[] a;
    private static char[] b;
    private static int[][] lcsArr;

    /**
     * 递归求 a、b 序列的最长公共子序列
     * 备忘录递归
     * @param i  数组 a 的长度
     * @param j  数组 b 的长度
     * @return
     */
    public static int lcs(int i, int j) {
        if (lcsArr[i][j] != 0) {
            return lcsArr[i][j];
        }
        if (i == 0 || j == 0) {
            lcsArr[i][j] = 0;
            return 0;
        }
        if (i == 1 && j == 1) {
            if (a[0] == b[0]) {
                lcsArr[i][j] = 1;
                return 1;
            } else
                return 0;
        }
        if (a[i - 1] == b[j - 1]) {
            lcsArr[i][j] = lcs(i - 1, j - 1) + 1;
            return lcsArr[i][j];
        } else {
            lcsArr[i][j] = Math.max(lcs(i - 1, j), lcs(i, j - 1));
            return lcsArr[i][j];
        }

    }

    /**
     * 动态规划迭代实现
     * @param aLength 数组 a 的长度
     * @param bLength 数组 b 的长度
     * @return
     */
    public static int lcsIteration(int aLength,int bLength){
        for(int i=1;i<=aLength;i++){
            for(int j=1;j<=bLength;j++){
                if(a[i-1]==b[j-1]){
                    lcsArr[i][j] = 1+lcsArr[i-1][j-1];
                }else if(lcsArr[i-1][j]>=lcsArr[i][j-1]){
                    lcsArr[i][j] = lcsArr[i-1][j];
                }else {
                    lcsArr[i][j] = lcsArr[i][j-1];
                }
            }
        }
        return lcsArr[aLength][bLength];

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int aLength = 7;
        int bLength = 6;
        a = new char[]{'A', 'B', 'C', 'B', 'D', 'A', 'B'};
        b = new char[]{'B', 'D', 'C', 'A', 'B', 'A'};

        lcsArr = new int[a.length + 1][b.length + 1];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                lcsArr[i][j] = 0;
            }
        }
        int maxLength = lcs(7, 6);
        System.out.println(maxLength);

        int maxLength2 = lcsIteration(7,6);
        System.out.println(maxLength2);
    }

}
/*
        7 6
        A B C B D A B
        B D C A B A
*/


