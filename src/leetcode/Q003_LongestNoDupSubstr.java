package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/10
 */
public class Q003_LongestNoDupSubstr {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        int[] pos = new int[256];
        for (int i = 0; i < 256; i++) {
            pos[i] = -1;
        }


        int[] dp = new int[s.length()];
        dp[0] = 1;
        pos[s.charAt(0)] = 0;
        int maxLen = 1;
        char c;
        for (int i = 1; i < s.length(); i++) {
            dp[i] = 1;
            c = s.charAt(i);

            if (pos[c] == -1 || pos[c] < i - dp[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = i - pos[c];
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
            }
            pos[c] = i;
        }

        return maxLen;

    }

    public int lengthOfLongestSubstring2(String s) {

        if (s == null || s.length() == 0) return 0;
        int[] pos = new int[256];
        for (int i = 0; i < 256; i++) {
            pos[i] = -1;
        }
        int dp = 1;
        pos[s.charAt(0)] = 0;
        int maxLen = 1;
        int lastPos;
        for (int i = 1; i < s.length(); i++) {
            lastPos = pos[s.charAt(i)];
            if (lastPos == -1 || lastPos < i - dp) {
                dp++;
            } else {
                dp = i -lastPos;
            }
            if (dp > maxLen) {
                maxLen = dp;
            }
            pos[s.charAt(i)] = i;
        }
        return maxLen;

    }

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
