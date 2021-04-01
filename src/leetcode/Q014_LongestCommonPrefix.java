package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/4
 */
public class Q014_LongestCommonPrefix {
    /**
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * <p>
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        int index = 0;
        int minLength = getMinLengthOfStrings(strs);

        boolean exit = false;
        while (index < minLength) {
            char c = strs[0].charAt(index);
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].charAt(index) != c) {
                    exit = true;
                    break;
                }
            }
            if (exit) break;
            index++;
        }
        return strs[0].substring(0, index); // 不包括 index 所在的位置
    }

    public int getMinLengthOfStrings(String[] strs) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < min) min = strs[i].length();
        }
        return min;
    }

    @Test
    public void test() {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println();

    }
}
