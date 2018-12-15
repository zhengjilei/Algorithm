package 剑指offer;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * n 个骰子的总点数
 * 求每个总点数出现的可能性
 * created by Ethan-Walker on 2018/12/15
 */
public class Q060_NDiceProbability {

    public HashMap<Integer, Double> calProbability(int n) {

        if (n < 1) return null;

        HashMap<Integer, Double> map = new HashMap<>();

        int sum = n;
        int count = (int) Math.pow(6, n), c; // 所有可能的情况总数
        double pro = 0.0;
        for (; sum <= 6 * n; sum++) {
            c = calNSum(n, sum);
            pro = c * 1.0 / count;
            map.put(sum, pro);
        }
        return map;

    }

    /**
     * 计算 n 个骰子出现 sum 的次数
     *
     * @param n
     * @param sum
     * @return
     */
    public int calNSum(int n, int sum) {
        if (n <= 0 || sum < n || sum > 6 * n) return 0;
        if (n == sum || n == 1) return 1;
        // n == 1 && sum <= 6 && sum >= 1  防止 n=1,sum=5 这种情况返回 0

        return calNSum(n - 1, sum - 1) +
                calNSum(n - 1, sum - 2) +
                calNSum(n - 1, sum - 3) +
                calNSum(n - 1, sum - 4) +
                calNSum(n - 1, sum - 5) +
                calNSum(n - 1, sum - 6);
    }


    /**
     * 非递归
     * 二维数组
     * 空间复杂度: O(n*6*n)
     * 时间复杂度: O(n*6*n)
     *
     * @param n
     * @return
     */
    public HashMap<Integer, Double> calProbability2(int n) {
        int sum = 6 * n;
        int[][] p = new int[n + 1][6 * n + 1];
        //p[i][j] = p[i-1][j-1] + p[i-1][j-2] + p[i-1][j-3] + p[i-1][j-4]+p[i-1][j-5] + p[i-1][j-6]

        for (int j = 1; j <= 6; j++) {
            p[1][j] = 1;
        }
        for (int i = 1; i <= n; i++) {
            p[i][i] = 1;
            p[i][6 * i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = i + 1; j < 6 * i; j++) {
                for (int k = 1; k <= 6 && i - 1 <= j - k; k++) {
                    p[i][j] += p[i - 1][j - k];
                }
            }
        }

        HashMap<Integer, Double> map = new HashMap<>();
        double d = 0.0;
        int count = (int) Math.pow(6, n);
        for (int k = n; k <= 6 * n; k++) {
            d = p[n][k] * 1.0 / count;
            map.put(k, d);
        }
        return map;
    }

    /**
     * 将二维数组转换成一维数组
     * 时间复杂度: O(n*6*n)
     * 空间复杂度: O(6*n)
     *
     * @param n
     * @return
     */
    public HashMap<Integer, Double> calProbability3(int n) {

        int[] p = new int[6 * n + 1];
        // 初始化第一轮 ：只有一个骰子的时候
        for (int i = 1; i <= 6; i++) {
            p[i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 6 * i; j >= i; j--) {  // p[j] = p[j-1] + p[j-2] + p[j-3] + p[j-4] + p[j-5] + p[j-6]  依赖左上角的，故从右往左计算
                if (j == i || j == 6 * i) {
                    p[j] = 1;
                    continue;
                }
                p[j] = 0; // 置0，防止用到上一轮中的元素值
                for (int k = 1; k <= 6 && i - 1 <= j - k; k++) {
                    p[j] += p[j - k];
                }
            }
        }

        HashMap<Integer, Double> map = new HashMap<>();

        int count = (int) Math.pow(6, n);
        double d = 0.0;

        for (int k = n; k <= 6 * n; k++) {
            d = p[k] * 1.0 / count;
            map.put(k, d);
        }
        return map;
    }

    @Test
    public void test() {
        printHashMap(calProbability(7));
        printHashMap(calProbability2(7));
        printHashMap(calProbability3(7));

    }

    void printHashMap(HashMap<Integer, Double> map) {
        Set<Map.Entry<Integer, Double>> entries = map.entrySet();
        for (Map.Entry<Integer, Double> entry : entries) {
            System.out.println(entry.getKey() + "=" + String.format("%.7f", entry.getValue()));
        }
        System.out.println();
    }


}
