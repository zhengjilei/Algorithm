package leetcode;

/**
 * created by Ethan-Walker on 2018/12/29
 */
public class Q0028_KMP {
    public int strStr(String haystack, String needle) {
        return kmp(haystack, needle);
    }


    public int kmp(String str, String pattern) {
        if (str == null || pattern == null || str.length() < pattern.length()) return -1;
        if (pattern.length() == 0) return 0; // pattern = "" 时，不管 str 是什么("" "av") 都返回 0

        int i = 0, j = 0;
        int[] next = getNext(pattern);

        while (i < str.length() && j < pattern.length()) {
            if (j == -1 || str.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == pattern.length()) return i - j;
        return -1;
    }

    public int[] getNext(String pattern) {
        int k = -1, j = 0;
        int[] next = new int[pattern.length()];
        next[j] = k;
        while (j < pattern.length() - 1) {
            // 求 next[j+1]
            if (k == -1 || pattern.charAt(k) == pattern.charAt(j)) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }

        return next;
    }


}
