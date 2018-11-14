package 全排列;

import java.util.Arrays;

public class Permutation {

    /**
     * 求数组 a 从 a[0] 到 a[i] 的全排列
     * recur(a,i,n) 表示a[0]到 a[i] 的全排列
     *
     * @param a
     * @param i
     * @param n
     */
    public static void recur(int[] a, int i, int n) {
        if (i == 0) {
            System.out.println(Arrays.toString(a));
            return;
        }
        for (int j = 0; j <= i; j++) {
            swap(a, j, i);
            recur(a, i - 1, n);
            swap(a, j, i);
        }

    }

    /**
     * recur2(a,i,n) 表示a[i]~a[n-1]的全排列
     *
     * @param a
     * @param i
     * @param n
     */
    public static void recur2(int[] a, int i, int n) {
        if (i == n - 1) {
            System.out.println(Arrays.toString(a));
            return;
        }
        for (int j = i; j <= n - 1; j++) {
            swap(a, j, i);
            recur2(a, i + 1, n);
            swap(a, j, i);
        }
    }

    /**
     * 字典序输出全排列
     */
    public static void lexicalPermutation(int[] a, int n) {
        int i = 0;
        int count = 1;
        System.out.println(Arrays.toString(a));
        while (true) {
            int index = -1;
            for (int j = n - 2; j >= 0; j--) {
                if (a[j] < a[j + 1]) {
                    index = j;
                    break;
                }
            }
            if (index != -1) {
                int min = a[index + 1];
                int minIndex = index + 1;
                for (int j = minIndex + 1; j < n; j++) {
                    if (a[j] > a[index] && a[j] < min) {
                        min = a[j];
                        minIndex = j;
                    }
                }
                swap(a, index, minIndex);
                Arrays.sort(a, index + 1, n);
                System.out.println(Arrays.toString(a));
                count++;
            } else {
                break;
            }
        }
        System.out.println("count=" + count);
    }

    public static void swap(int[] a, int i, int j) {
        a[i] = a[i] + a[j] - (a[j] = a[i]);
    }

    /**
     * 字符全排列
     *
     * @param chs
     * @param start
     * @param end
     */
    public static void perm(char[] chs, int start, int end) {

        if (start == end) {
            for (int i = 0; i <= end; i++) {
                System.out.print(chs[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = start; i <= end; i++) {
                swap(chs, i, start);
                perm(chs, start + 1, end);
                swap(chs, i, start);
            }
        }

    }

    public static void swap(char[] chs, int a, int b) {
        char t = chs[a];
        chs[a] = chs[b];
        chs[b] = t;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 1, 2, 3};
//        recur2(a, 0, 4);
        lexicalPermutation(a, a.length);
    }
}
