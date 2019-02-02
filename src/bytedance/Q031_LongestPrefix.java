package bytedance;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/1
 */
public class Q031_LongestPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        int minLen = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minLen) {
                minLen = strs[i].length();
                minIndex = i;
            }
        }
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < strs[minIndex].length(); i++) {
            // strs[minIndex] 和其他所有字符比较第 i 个字符
            char c = strs[minIndex].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (j == minIndex) continue;
                if (strs[j].charAt(i) != c) {
                    return prefix.toString();
                }
            }
            prefix.append(c);
        }

        return prefix.toString();
    }

    @Test
    public void test() {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
    }
}
