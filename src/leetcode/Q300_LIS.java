package leetcode;

/**
 * created by Ethan-Walker on 2019/1/3
 */
public class Q300_LIS {
    public int lengthOfLIS(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int[] dp = getDp(arr);
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (dp[i] > max) max = dp[i];
        }
        return max;

    }

    /**
     * dp[i] = 1+ max{dp[j] } j< i && a[j]< a[i]
     * <p>
     * 时间复杂度：O(n^2)
     * 空间复杂度: O(n)
     *
     * @param arr
     * @return
     */
    public int[] getDp(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        int max = 0;
        for (int i = 1; i < arr.length; i++) {
            // 计算dp[i]
            max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] > max) {
                    max = dp[j];
                }
            }
            dp[i] = max + 1;

        }
        return dp;
    }

    /**
     * ends[0...right] 为递增序列有效区
     * ends[b] == c  表示遍历到目前为止，所有长度为 b+1 的递增子序列中 ，最小的结尾数为 c
     * 作用：同等长度下，让该长度的结尾数最小，保证构造的递增序列最长
     * <p>
     * 每当访问 arr[i] 时，二分查找 ends[0..right] 找第一个大于或等于 arr[i] 的位置 index
     * 必须是大于等于，不能仅仅是大于
     * <p>
     * 1. 若 index>right 表示没有找到: 扩大 ends 有效区
     * 说明 arr[i] 可以加到 ends 中, ends[++right] = arr[i]
     * 以 arr[i] 结尾的最长递增子序列长度 dp[i] = right+1
     * <p>
     * 2 .若 index<=right 表示找到:
     * 长度为 index+1 的最长递增子序列 最小的结尾数更改为 arr[i] : ends[index] = arr[i]
     * dp[i] = right+1
     *
     *
     * 时间复杂度: O(nlogn)
     * 空间复杂度: O(n)
     * @param arr
     * @return
     */
    public int[] getDp2(int[] arr) {

        int[] ends = new int[arr.length];
        int[] dp = new int[arr.length];
        dp[0] = 1;
        int right = 0;
        ends[0] = arr[0];
        for (int i = 0; i < arr.length; i++) {
            int l = 0, r = right, mid = 0;
            int firstNotLess = -1;
            while (l <= r) {
                mid = (l + r) >> 1;
                if (ends[mid] < arr[i]) {
                    l = mid + 1;
                } else {
                    firstNotLess = mid;
                    r = mid - 1;
                }
            }

            if (firstNotLess == -1) {
                // 未找到大于等于 arr[i]的值，说明 arr[i] 可以加入递增序列有效区ends 中
                ends[++right] = arr[i];
            } else {
                // 找到，更新 firstNotLess 对应位置的值
                ends[firstNotLess] = arr[i];
            }
            dp[i] = right + 1; // 以arr[i] 结尾的最长递增子序列的长度 等于 当前 ends 的有效部分长度
        }
        return dp;
    }
}
