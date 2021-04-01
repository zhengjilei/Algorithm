package a_review.interview.grab;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/18
 */
public class MinEditCost {


    /**
     * dp[i][j]=k 含义: 将 s1[0..i-1] 编辑成 s2[0..j-1] 的最小编辑距离为 k
     * 1. 处理边界:空字符串
     * 空字符串 编辑成 str2[0..j]
     * dp[0][j] = j;
     * str1[0..i] 编辑成 空字符串
     * dp[i][0] = i;
     * 2. 求 dp[i][j]
     * (1) s1[i-1]==str2[j-1] ，将 str1[0..i-2] 编辑成 str2[0..j-2] 即可  =>  dp[i-1][j-1]
     * str1[i-1]!=str2[j-1] , 将 str1[0..i-2] 编辑成 str2[0..j-2], 再替换 str1[i-1] -> str2[j-1]   => dp[i-1][j-1]+1
     * (2) 删除 str1[i-1], 将 str1[0..i-2] 编辑成 str2[0..j-1]  =>  dp[i-1][j]+1
     * (3) 将 str1[0..i-1] 编辑成 str2[0..j-2], 再插入 str2[j-1]  => dp[i][j-1] +1
     * <p>
     * 时间复杂度:O(s1Len*s2Len)
     * 空间复杂度:O(s1Len*s2Len)
     *
     * @return
     */
    public int minCost(String s1, String s2) {
        if (s1 == null || s2 == null) return -1; // 处理空的情况，防止抛出空指针异常
        int s1Len = s1.length();
        int s2Len = s2.length();
        if (s1Len == 0) return s2Len;
        if (s2Len == 0) return s1Len;

        int[][] dp = new int[s1Len + 1][s2Len + 1];
        // 计算将空字符串 编辑成 s2
        for (int j = 0; j <= s2Len; j++) {
            dp[0][j] = j;
        }
        // 将 s1 编辑成 空字符串
        for (int i = 0; i <= s1Len; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= s1Len; i++) {
            for (int j = 1; j <= s2Len; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (dp[i - 1][j] + 1 < dp[i][j]) dp[i][j] = dp[i - 1][j] + 1;
                if (dp[i][j - 1] + 1 < dp[i][j]) dp[i][j] = dp[i][j - 1] + 1;
            }
        }
        return dp[s1Len][s2Len];
    }

    /**
     * 压缩空间至 O(min{s1Len,s2Len})
     * <p>
     * 时间复杂度:O(s1Len*s2Len)
     * 空间复杂度:O(min{s1Len,s2Len})
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minCost2(String s1, String s2) {
        if (s1 == null || s2 == null) return -1; // 处理空的情况，防止抛出空指针异常
        int s1Len = s1.length();
        int s2Len = s2.length();
        if (s1Len == 0) return s2Len;
        if (s2Len == 0) return s1Len;

        if (s1Len < s2Len) {
            String t = s1;
            s1 = s2;
            s2 = t;
            s1Len = s1.length();
            s2Len = s2.length();
        }
        // 让 s1 指向长度较长的字符串,s2指向短字符串
        int[] dp = new int[s2Len + 1];
        for (int j = 0; j <= s2Len; j++) {
            dp[j] = j;
        }

        int prev; // prev 相当于存储 dp[i-1][j-1]
        int cur;    // 计算dp[j] 之前,用 cur 存储dp[j] 即存储dp[i-1][j],作为下一次的 prev 的值
        int num;  // 临时变量，计算dp[j] 时的临时过程值先用 num 保存，防止 dp[j] 被覆盖
        for (int i = 1; i <= s1Len; i++) {
            prev = dp[0];
            dp[0] = i;
            for (int j = 1; j <= s2Len; j++) {
                cur = dp[j]; // 作为下一次的 prev 的值
                // 因为有多次比较赋值，需要反复用到上一行的 dp[j], 故不能立即更新 dp[j],先用 num 临时保存
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    num = prev;
                } else {
                    num = prev + 1;
                }
                if (dp[j] + 1 < num) num = dp[j] + 1;
                if (dp[j - 1] + 1 < num) num = dp[j - 1] + 1;
                dp[j] = num;
                prev = cur; // 保存上一行的 dp[i-1][j-1], 下一次计算会用到
            }
        }
        return dp[s2Len];

    }

    @Test
    public void test() {
        // 不压缩空间 和 压缩空间两种解 对比
        System.out.println(minCost(null, "") + " vs " + minCost2(null, ""));
        System.out.println(minCost("", "") + " vs " + minCost2("", ""));
        System.out.println(minCost("a", "") + " vs " + minCost2("a", ""));
        System.out.println(minCost("", "aaa") + " vs " + minCost2("", "aaa"));
        System.out.println(minCost("abcd", "ad") + " vs " + minCost2("abcd", "ad"));
        System.out.println(minCost("abcd", "acdfe") + " vs " + minCost2("abcd", "acdfe"));
        System.out.println(minCost("aaa", "a") + " vs " + minCost2("aaa", "a"));
        System.out.println(minCost("abcde", "abfdg") + " vs " + minCost2("abcde", "abfdg"));
    }

}
