package 背包01;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class PAT1068 {

    static class MyComp implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int V = sc.nextInt();
        Integer[] value = new Integer[n + 1];
        for (int i = 1; i <= n; i++) {
            value[i] = sc.nextInt();
        }
        value[0] = 0;
        Arrays.sort(value, 1, n + 1, new MyComp()); // [fromIndex,toIndex)
        System.out.println(Arrays.toString(value));
        print(n, V, value);

    }

    public static void print(int N, int V, Integer[] value) {
        int[][] dp = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                if (value[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - value[i]] + value[i]);
                }
            }
        }
        for(int i=1;i<=N;i++){
            for(int j=1;    j<=V;j++){
                System.out.printf("%6d",dp[i][j]);
            }
            System.out.println();
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        if (dp[N][V] != V) {
            System.out.println("No Solution");
        } else {
            for (int i = N; i >= 1; i--) {
                if (value[i] <= V && dp[i][V] == dp[i - 1][V - value[i]] + value[i]) {
                    queue.offer(value[i]);
                    V -= value[i];
                }
            }
        }
        while (!queue.isEmpty()) {
            if (queue.size() == 1) {
                System.out.print(queue.poll());
            } else {
                System.out.print(queue.poll() + " ");
            }
        }
    }
}


