package programmer_interview;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/25
 */
public class Q084_MakeHuiwen {
    /**
     * dp[i][j] 表示将 str i..j 变成回文子串的最少步骤
     * 1.j==i dp[i][j] = 0
     * <p>
     * 2.j-i==1
     * if str[i]==str[j], dp[i][j]=0
     * else   dp[i][j] = 1
     * <p>
     * 3. j-i>2
     * if str[i]==str[j], dp[i][j] = dp[i+1][j-1]
     * else  dp[i][j] = 1+min{dp[i+1][j],dp[i][j-1]}
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     *
     * @param str
     * @returnw
     */
    public int[][] getDp(String str) {
        int[][] dp = new int[str.length()][str.length()];
        //计算方向错误 ,dp[i][j] 依赖左下角数据
        for (int i = str.length() - 2; i >= 0; i--) {
            dp[i][i + 1] = str.charAt(i) == str.charAt(i + 1) ? 0 : 1;
            for (int j = i + 2; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }

    public String makeHuiwen(String str) {
        int[][] dp = getDp(str);
        int i = 0, j = str.length() - 1;
        char[] res = new char[str.length() + dp[0][str.length() - 1]];
        int left = 0, right = res.length - 1;
        while (i <= j) {
            if (str.charAt(i) == str.charAt(j)) {
                // j==i 时也适用
                res[left++] = str.charAt(i++);
                res[right--] = str.charAt(j--);
            } else if (dp[i + 1][j] < dp[i][j - 1]) {
                // 将 str[i] 插入到 最后
                res[right--] = str.charAt(i);
                res[left++] = str.charAt(i++);
            } else {
                // j-i == 1 时也适用
                // 将 str[j] 插入到最前面
                res[left++] = str.charAt(j);
                res[right--] = str.charAt(j--);
            }
        }
        return String.valueOf(res);
    }

    /**
     * @param str
     * @param subPalindrome str中最长回文子序列
     * @return
     */
    public String makeHW(String str, String subPalindrome) {

        int resLen = 2 * str.length() - subPalindrome.length();
        int subLeft = 0, subRight = subPalindrome.length() - 1;
        int resLeft = 0, resRight = resLen - 1;
        char[] res = new char[resLen];

        int strLeft = 0, strRight = str.length() - 1;
        while (subLeft <= subRight) {
            int saveStrLeft = strLeft;
            int saveStrRight = strRight;

            // 在str中找 sub[subLeft] 的左部分序列
            while (strLeft < strRight && str.charAt(strLeft) != subPalindrome.charAt(subLeft)) {
                strLeft++;
            }

            // 在str中找 sub[subRight]的右半部分序列
            while (strLeft < strRight && str.charAt(strRight) != subPalindrome.charAt(subRight)) {
                strRight--;
            }

            while (saveStrLeft < strLeft) {
                res[resLeft++] = res[resRight--] = str.charAt(saveStrLeft++);
            }
            while (saveStrRight > strRight) {
                res[resLeft++] = res[resRight--] = str.charAt(saveStrRight--);
            }


            res[resLeft++] = str.charAt(strLeft++);

            if (subLeft != subRight) {// 可能sub是奇数个字符，subLeft、subRight 恰好指向中间字符
                res[resRight--] = str.charAt(strRight--);
            }

            subLeft++;
            subRight--;
        }
        return String.valueOf(res);

    }

    @Test
    public void test1() {
        System.out.println(makeHuiwen("A1B21C"));
    }

    @Test
    public void test2() {
        System.out.println(makeHW("A1BC22DE1F", "1221"));
        System.out.println(makeHW("A1B21C", "121"));
    }
}
