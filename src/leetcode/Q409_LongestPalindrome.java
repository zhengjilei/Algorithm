package leetcode;

/**
 * created by Ethan-Walker on 2019/3/10
 */
public class Q409_LongestPalindrome {
    public int longestPalindrome(String s) {
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        boolean hasOne = false;
        int len = 0;
        for (int i = 0; i < 256; i++) {
            if ((count[i] & 1) == 0) {
                // 偶数
                len += count[i];
            } else {
                len += count[i] - 1;
                if (!hasOne) {
                    hasOne = true;
                }
            }
        }
        if (hasOne) len++;
        return len;
    }

}
