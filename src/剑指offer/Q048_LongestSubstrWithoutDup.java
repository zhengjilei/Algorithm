package 剑指offer;

import org.junit.Test;

/**
 * 最长的不重复子字符串
 * created by Ethan-Walker on 2018/12/11
 */
public class Q048_LongestSubstrWithoutDup {


    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(range)  range 是指题中给定的字符范围大小
     * <p>
     * 左右指针变换法
     *
     * @param str
     * @return
     */
    public int getLongestSubStr(String str) {

        boolean[] bitmap = new boolean[255];
        char[] chs = str.toCharArray();
        int length = chs.length;

        int fast = 0, low = 0;
        int maxLength = 0;
        while (fast < length) {
            if (bitmap[chs[fast]] == false) {
                bitmap[chs[fast]] = true;
            } else {
                if (fast - low > maxLength) {
                    maxLength = fast - low;
                }

                // 找到 chs[low] == chs[fast] 去掉重复元素及其之前的元素(bitmap 对应位置设为 false)
                while (chs[low] != chs[fast]) {
                    bitmap[chs[low]] = false;
                    low++;
                }
                low++;
            }
            fast++;
        }
        // 防止最后一次字符串超过最大值，但是由于越界先跳出循环，没有统计到 maxLength中
        if (fast - low > maxLength) {
            maxLength = fast - low;
        }
        return maxLength;
    }


    /**
     * 动态规划法
     *
     * @param str
     * @return
     */
    public int getLongestSubStr2(String str) {
        if (str.length() == 0) return 0;
        int[] position = new int[255];// position['c'] = 12 表示字符 c 最近一次出现的位置是 12
        for (int i = 0; i < 255; i++) {
            position[i] = -1;
        }
        char[] chs = str.toCharArray();
        int length = chs.length;

        int[] dp = new int[length];
        // dp[i] = j 表示以字符chs[i] 结尾的且不包含重复子字符串的最大长度为 j

        dp[0] = 1;
        int maxLength = dp[0];
        position[chs[0]] = 0;

        for (int i = 1; i < length; i++) {
            if (position[chs[i]] == -1) {
                // 字符 chs[i]之前都没有出现
                dp[i] = dp[i - 1] + 1;

            } else {
                int d = i - position[chs[i]];
                if (d <= dp[i - 1]) {
                    // 说明chs[i] 字符在 以chs[i-1]为结尾的不包含重复字符的最长子字符串中
                    dp[i] = d;// dp[i] 就等于两个重复字符距离之差，因为这两个字符之间没有重复字符
                } else {
                    dp[i] = dp[i - 1] + 1;
                }
            }
            position[chs[i]] = i;

            if (dp[i] > maxLength) {
                maxLength = dp[i];
            }
        }


        return maxLength;

    }

    @Test
    public void test() {
        System.out.println(getLongestSubStr2(" "));
    }
}
