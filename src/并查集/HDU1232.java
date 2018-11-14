package 并查集;

import java.util.Scanner;

/**
 * created by Ethan-Walker on 2018/11/8
 */
public class HDU1232 {

    private static int[] parent = new int[1001];
    private static int size;

    public static void initParent() {
        for (int i = 0; i < size; i++) {
            parent[i] = -1;
        }
    }

    public static void weightUnion(int val1, int val2) {
        int a = find(val1);
        int b = find(val2);
        if (a == b) return;
        int aLength = -parent[a];
        int bLength = -parent[b];
        if (aLength > bLength) {
            parent[a] += parent[b];
            parent[b] = a;
        } else {
            parent[b] += parent[a];
            parent[a] = b;
        }
    }

    /**
     * 查找值val属于哪个集合
     *
     * @param val
     * @return
     */
    public static int find(int val) {
        while (parent[val] > 0) val = parent[val];
        return val;
    }

    /**
     * 计算需要铺设多少条路：集合数-1
     *
     * @return
     */
    public static int cal() {
        int count = 0;
        for (int i = 1; i < size; i++) {
            if (parent[i] < 0) {
                count++;
            }
        }
        return count - 1;
    }

    public static void main(String[] args) {
        int n, m, a, b;
        Scanner sc = new Scanner(System.in);
        while ((n = sc.nextInt()) != 0) {
            m = sc.nextInt();
            size = n + 1;
            initParent();
            for (int i = 0; i < m; i++) {
                a = sc.nextInt();
                b = sc.nextInt();
                weightUnion(a, b);
            }
            System.out.println(cal());
        }

    }
}
