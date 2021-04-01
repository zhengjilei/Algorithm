package lca;

import java.util.Scanner;

/**
 * created by Ethan-Walker on 2018/12/31
 */
public class POJ1330 {

    // TODO: 2018/12/31 待验证 poj 网站登录不上去
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        int a, b, n;
        int[] parent;
        while (t > 0) {
            n = scanner.nextInt();
            parent = new int[n + 1];

            for (int i = 0; i < n - 1; i++) {
                a = scanner.nextInt();
                b = scanner.nextInt();
                parent[b] = a;
            }
            a = scanner.nextInt();
            b = scanner.nextInt();

            // 求 a/b 的最近公共节点
            System.out.println(cal(parent, a, b));
            t--;
        }
    }

    public static int cal(int[] parent, int a, int b) {
        int n = parent.length - 1;

        int tempA = a, tempB = b;
        int length1 = 0;
        while (tempA != 0) {
            tempA = parent[tempA];
            length1++;
        }

        int length2 = 0;
        while (tempB != 0) {
            tempB = parent[tempB];
            length2++;
        }

        int step = Math.max(length1, length2);
        int fast = a, slow = b;
        if (length2 > length1) {
            fast = b;
            slow = a;
        }

        while (step > 0) {
            fast = parent[fast];
        }

        while (fast != slow) {
            fast = parent[fast];
            slow = parent[slow];
        }
        return fast;
    }


}
