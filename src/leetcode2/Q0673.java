package leetcode2;

import org.junit.Test;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2019/3/19
 */
public class Q0673 {

    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int[] cnt = new int[nums.length];
        int maxLen = 1;
        for (int i = 0; i < nums.length; i++) {
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


        int res = 0;

        int maxLenIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxLen == dp[i]) {
                res += cnt[i];
                maxLenIndex = i;
            }
        }

        int[] resSeq = new int[maxLen];
        int resIndex = maxLen - 1;
        resSeq[resIndex--] = nums[maxLenIndex];

        int i = maxLenIndex - 1;
        while (resIndex >= 0) {
            if (nums[i] < resSeq[resIndex + 1]) {
                resSeq[resIndex--] = nums[i];
            }
            i--;
        }

        System.out.println(Arrays.toString(resSeq));

        return res;
    }


    @Test
    public void test() {
        System.out.println(findNumberOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
