package 并查集;

import java.util.Scanner;

/**
 * created by Ethan-Walker on 2018/11/8
 */
public class HDU1272 {

    public static int[] parent = new int[100001];

    public static void init(int count) {
        for (int i = 0; i <= count; i++) {
            parent[i] = -1;
        }
    }

    public static boolean union(int a, int b) {
        int set1 = find(a);
        int set2 = find(b);
        if (set1 == set2) {
            return false;
        } else {
            if (parent[set1] > parent[set2]) {
                parent[set2] += parent[set1];
                parent[set1] = set2;
            } else {
                parent[set1] += parent[set2];
                parent[set2] = set1;
            }
            return true;
        }
    }

    /**
     * 注意，要保证最后只能有一个集合
     * @param max
     * @return
     */
    public static boolean judge(int max) {
        // 计算树根的个数，1~max之间 parent[i] = -1 不算，因为未出现在图中
        int count = 0;
        for (int i = 1; i <= max; i++) {
            if (parent[i] < -1) { // <-1 说明添加到集合中 且是根节点， =-1说明没有加到集合中
                count++;
                if (count > 1) return false;
            }
        }
        return true;
    }

    public static int find(int a) {
        while (parent[a] > 0) a = parent[a];
        return a;
    }

    public static void main(String[] args) {
        int a, b;
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        init(100000);
        int max = 0;
        while (true) {
            a = sc.nextInt();
            max = Math.max(max, a);
            b = sc.nextInt();
            max = Math.max(max, b);
            if (a == -1 && b == -1) return;
            if (a == 0 && b == 0) {
                if (flag && judge(max)) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
                init(max);
                max = 0;
                flag = true;
            } else {
                if (flag) { // flag变成 false之后就不必执行了
                    flag = union(a, b);
                }
            }
        }
    }
}
