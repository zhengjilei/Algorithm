package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Q028 {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) return -1;
        if (needle.length() == 0) return 0; // pattern = "" 时，不管 str 是什么("" "av") 都返回 0
        return kmp(haystack, needle);
    }

    public int kmp(String s, String p) {
        int i = 0, j = 0;
        int[] next = getNext(p);
        while (i < s.length() && j < p.length()) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                ++i;
                ++j;
            } else {
                j = next[j];
            }
        }
        if (j == p.length()) return i - j;
        return -1;
    }

    public int[] getNext(String p) {
        int[] next = new int[p.length()];
        int j = 0, k = -1;
        next[j] = k;
        while (j < p.length() - 1) { // 计算 next[j+1]
            if (k == -1 || p.charAt(j) == p.charAt(k)) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
