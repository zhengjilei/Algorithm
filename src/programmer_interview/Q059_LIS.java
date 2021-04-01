package programmer_interview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * longest increment substring
 * 最长递增子序列 (不一定是连续的子序列)
 * created by Ethan-Walker on 2019/1/3
 */
public class Q059_LIS {

    /**
     * 最长递增连续子序列
     * 双指针法
     * 时间复杂度:  O(n)
     * 空间复杂度: O(1)
     *
     * @param arr
     * @return
     */
    public int[] lsis(int[] arr) {
        if (arr == null || arr.length <= 1) return arr;

        int start = 0, end = 1;
        int longest = 1;
        int startIndex = start;
        int endIndex = start;
        while (end < arr.length) {
            while (end < arr.length && arr[end] > arr[end - 1]) {
                end++;
            }

            if (end - start > longest) {
                longest = end - start;
                startIndex = start;
                endIndex = end - 1;
            }
            start = end;
            end++;
        }
        int[] result = new int[longest];
        for (int i = 0; i < longest; i++) {
            result[i] = arr[i + startIndex];
        }
        return result;
    }

    /**
     * 最简单的方法，固定一个点，遍历一遍，找到以该点为起点的最长递增序列
     * O(n^2)
     * 思路错误:  2 5 3 4  统计以 2 为起点的最长递增子序列 计算成了 [2,5]
     *
     * @param arr
     * @return
     */
    public int[] lisError(int[] arr) {
        if (arr == null || arr.length <= 1) return arr;
        int longest = 0;
        int start = 0, end = 0;
        List<Integer> list = new ArrayList<>();

        List<Integer> result = null;
        int cnt = 0;

        int prev = 0;
        // arr.length-i 为 a[i]~a[length-1] 的长度, 若 length-i<=longest
        // 就算a[i]~a[n-1] 全部是递增的，也小于 longest
        for (int i = 0; arr.length - i > longest; i++) {
            cnt = 1;
            list.add(arr[i]);
            prev = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[prev]) {
                    cnt++;
                    list.add(arr[j]);
                    prev = j;
                }
            }
            if (cnt > longest) {
                longest = cnt;
                result = list;
                list = new ArrayList<>();
            } else {
                list.clear();
            }
        }

        int[] res = new int[longest];
        for (int i = 0; i < longest; i++) {
            res[i] = result.get(i);
        }
        return res;
    }


    public int[] lis(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        if (arr.length == 1) return arr;

        int[] dp = getDp(arr);

        int longest = 0, index = 0; // index 指向最大序列的末位置
        for (int i = 0; i < arr.length; i++) {
            if (dp[i] > longest) {
                longest = dp[i];
                index = i;
            }
        }
        int[] result = new int[longest];

        int resultIndex = longest - 1;
        result[resultIndex] = arr[index];

        for (int i = index; i >= 0; i--) {
            // 找出上一个 dp[i]
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                result[--resultIndex] = arr[i];
                index = i;
            }
        }
        return result;
    }

    /**
     * dp[i] 表示以 arr[i] 结尾的情况下，a[0]..a[i] 的最长序列长度
     * dp[i] = 1+ max{dp[k]} k< i  && a[k]< a[i]
     *
     * @param arr
     * @return
     */
    public int[] getDp(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        int max = 0;
        for (int i = 1; i < arr.length; i++) {
            max = 0;
            for (int j = 0; j < i; i++) {
                if (dp[j] > max && arr[j] < arr[i]) max = dp[j];
            }
            dp[i] = 1 + max;
        }
        return dp;
    }


    @Test
    public void test() {
        int[] a = {2, 1, 5, 3, 6, 4, 8, 9, 7};

    }
}
