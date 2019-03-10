package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/10
 */
public class Q014 {
    public String longestCommonPrefix(String[] strs) {
        int count = 0, j;
        if (strs == null || strs.length == 0 || strs[0].length() == 0) return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c) break;
            }
            if (j != strs.length) break;
            count++;
        }
        return count == 0 ? "" : strs[0].substring(0, count);
    }
}
