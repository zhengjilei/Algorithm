package huawei.threee;

import java.util.Scanner;

/**
 * Created by Ethan-Walker on 2018/4/18.
 */
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Double sumCount = Math.pow(6, n);  // 所有情况总数

        StringBuffer stringBuffer = new StringBuffer("[");
        for (int sum = n; sum <= n * 6; sum++) {

            long totalCount = getTotalCount(n, sum);
            double gailv = totalCount / sumCount;  //概率

            String format = String.format("%.7f", gailv);
            stringBuffer.append("[").append(sum).append(", ").append(format).append("]");
            if (sum != 6 * n) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append("]");
        System.out.println(stringBuffer.toString());

    }

    /**
     * 求 n 个骰子，出现和为 sum 的 总情况数
     *
     * @param n
     * @return
     */
    public static long getTotalCount(int n, int sum) {
        if (n < 1 || sum < n || sum > 6 * n) {
            return 0;
        }
        if (n == 1 || n == sum) return 1;
        return getTotalCount(n - 1, sum - 1) + getTotalCount(n - 1, sum - 2) + getTotalCount(n - 1, sum - 3) + getTotalCount(n - 1, sum - 4) + getTotalCount(n - 1, sum - 5) + getTotalCount(n - 1, sum - 6);

    }
}
