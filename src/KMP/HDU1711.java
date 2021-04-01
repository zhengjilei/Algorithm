package KMP;

import java.util.Scanner;

public class HDU1711 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        while (n-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int[] q = new int[a];
            int[] p = new int[b];

            for (int i = 0; i < a; i++) {
                q[i] = sc.nextInt();
            }
            for (int i = 0; i < b; i++) {
                p[i] = sc.nextInt();
            }
            System.out.println(kmp(q, p));
        }
    }

    public static int kmp(int[] s, int[] p) {
        int[] next = getNext(p);
        int i = 0, j = 0;
        while (i < s.length && j < p.length) {
            if (j == -1 || s[i] == p[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == p.length) return i - p.length + 1;
        return -1;
    }

    private static int[] getNext(int[] p) {
        int[] next = new int[p.length + 1];
        int k = -1, j = 0;
        next[j] = k;
        while (j < p.length) {
            // 求 next[j+1]
            if (k == -1 || p[j] == p[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
