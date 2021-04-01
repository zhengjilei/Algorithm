package 最长递增子序列;

/**
 * created by Ethan-Walker on 2019/3/9
 */
public class LISReview {

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxLen = 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            int j = 0;
            for (; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            if (dp[i] > maxLen)
                maxLen = dp[i];
        }

        // 得到其中的一个递增子序列
        int i = 0;
        for (; i < nums.length; i++) {
            if (dp[i] == maxLen) break;
        }
        int[] seq = new int[maxLen];
        int seqIndex = maxLen - 1;
        seq[seqIndex--] = nums[i];
        int j = i - 1;
        while (j >= 0) {
            if (nums[j] < nums[i] && dp[i] == dp[j] + 1) {
                seq[seqIndex--] = nums[j];
                i = j;
                j = i - 1;
            } else {
                j--;
            }
        }

        return maxLen;
    }

    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxLen = 1;
        int[] dp = new int[nums.length];
        int[] cnt = new int[nums.length];
        cnt[0] = dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
            }
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] == maxLen) {
                count += cnt[i];
            }
        }
        return count;
    }


}
