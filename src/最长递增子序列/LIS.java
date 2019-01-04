package 最长递增子序列;

/**
 * created by Ethan-Walker on 2019/1/3
 */
public class LIS {


    /**
     * 计算数组 nums 的最长递增子序列长度
     * <p>
     * dp[i] 表示以 nums[i]结尾的最长递增子序列个数
     * 每当计算nums[i] 对应的 dp[i] 时，在dp[0~i-1] 中找到 nums[j]< nums[i] 且dp[j] 最大的值
     * dp[i] = 1+max{dp[j] }  nums[j]< nums[i] && dp[j] 最大
     * <p>
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(n)
     *
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int maxLength = 1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1; // 以 nums[i] 结尾的最长递增子序列个数初始值 一定为1 (本身)
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1; // 更新最大值
                }
            }
            maxLength = dp[i] > maxLength ? dp[i] : maxLength;
        }
        return maxLength;
    }

    /**
     * 计算数组 nums 的最长递增子序列种类总数
     * <p>
     * cnt[i] 表示以 nums[i] 结尾的最长递增子序列种类总数
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {

        int[] dp = new int[nums.length];
        int[] cnt = new int[nums.length];

        int maxLength = 1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = cnt[i] = 1;
            for (int j = 0; j < i; i++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;      // 更新当前以 nums[i] 结尾的最长递增子序列长度
                        cnt[i] = cnt[j];        // 以 nums[i] 结尾的最长递增子序列个数 等于以 nums[j] 结尾的最长递增子序列种类个数
                        // 因为仅仅将 nums[i] 添加到原来以  nums[i] 结尾的字符串
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];        // 这里不需要更新 dp[i], 因为已经是目标值了
                        // 但是以 nums[j] 结尾的最长递增子序列 可以替代以 nums[i]结尾的最长递增子序列除了 nums[i]的所有元素
                        // 故增加的种类即为以nums[j]结尾的最长递增子序列的种类个数
                    }
                }
            }
            maxLength = dp[i] > maxLength ? dp[i] : maxLength;
        }
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] == maxLength) result += cnt[i];
        }
        return result;
    }

    /**
     * 计算数组 nums 的最长递增子序列长度
     * ends[b] == c  表示遍历到目前为止，所有长度为 b+1 的递增子序列中 ，最小的结尾数为 c
     * ends[0...right] 为递增序列有效区
     * 作用：同等长度下，让该长度的结尾数最小，保证之后构造的递增序列最长
     * <p>
     * 每当访问 nums[i] 时，二分查找 ends[0..right] 找第一个大于或等于 arr[i] 的位置 index
     * 必须是包括等于，不能仅仅是大于
     * 假设是大于: ends[0..2]={1,3,5} 中找> 3 的值, 找到位置 index=2, 更新 ends[2] = 3  错误
     * <p>
     * 1. 若 index>right 表示没有找到:
     * 说明 nums[i] 可以加到 ends 中,扩大 ends 有效区， ends[++right] = nums[i]
     * 2 .若 index<=right 表示找到:
     * 长度为 index+1 的最长递增子序列 最小的结尾数更改为 arr[i] : ends[index] = arr[i]
     * <p>
     * 以 nums[i] 结尾的最长递增子序列长度 dp[i] = 当前统计的 ends 有效区的长度（right+1）
     * <p>
     * 时间复杂度: O(nlogn)
     * 空间复杂度: O(n)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        int[] ends = new int[nums.length];
        int right = 0, maxLength = 1;
        int l, mid, r;
        int firstNotLess = -1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            l = 0;
            r = right;
            firstNotLess = -1;
            // 在 ends[0..right]中找到第一个大于等于 nums[i] 的值
            while (l <= r) {
                mid = (l + r) >> 1;
                if (ends[mid] < nums[i]) {
                    l = mid + 1;
                } else {
                    firstNotLess = mid;
                    r = mid - 1;
                }
            }
            if (firstNotLess == -1) {
                // 未找到大于等于 arr[i]的值，说明 arr[i] 可以加入递增序列有效区ends 中
                ends[++right] = nums[i];
            } else {
                // 找到，更新 firstNotLess 位置的值, 长度为 firstNotLess+1 最小的结尾数为 nums[i]
                ends[firstNotLess] = nums[i];
            }
            dp[i] = right + 1;

            maxLength = dp[i] > maxLength ? dp[i] : maxLength;
        }
        return maxLength;
    }


    /**
     * 返回最长递增子序列的其中一个
     * 思路: 先计算出 dp[] ,然后找到dp[i] = maxLength 位置 i
     * 逆序寻找 nums[j]< nums[i] && dp[j] == dp[i]-1
     *
     * @param nums
     * @return
     */
    public int[] seqOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int[] ends = new int[nums.length];
        int right = 0, l, r, mid, firstNotLess;

        int maxLength = 1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            l = 0;
            r = right;
            firstNotLess = -1;
            while (l <= r) {
                mid = (l + r) >> 1;
                if (ends[mid] < nums[i]) {
                    l = mid + 1;
                } else {
                    firstNotLess = mid;
                    r = mid - 1;
                }
            }
            if (firstNotLess == -1) {
                ends[++right] = nums[i];
            } else {
                ends[firstNotLess] = nums[i];
            }
            dp[i] = right + 1;
            maxLength = dp[i] > maxLength ? dp[i] : maxLength;
        }
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] == maxLength) {
                index = i;
                break;
            }
        }

        int[] seq = new int[maxLength];
        int seqIndex = maxLength - 1;
        seq[seqIndex] = dp[index];

        for (int j = index - 1; j >= 0; j--) {
            if (nums[j] < nums[index] && dp[j] + 1 == dp[index]) {
                seq[--seqIndex] = nums[j];
                index = j;
            }
        }
        return seq;

    }

    // TODO: 2019/1/4所有长度大于2 的递增子序列  leetcode 491
}
