package 程序员代码面试指南;

/**
 * created by Ethan-Walker on 2019/1/12
 */
public class Q075_IsRotation {

    public boolean rotateString(String A, String B) {
        if (A == null || B == null || A.length() != B.length()) return false;
        return kmp(A + A, B);
    }

    /**
     * A 中查找字符串 B
     *
     * @param A
     * @param B
     * @return
     */
    public boolean kmp(String A, String B) {
        if (A.length() == 0 && B.length() == 0) return true;
        int i = 0, j = 0;
        int[] next = getNext(B);
        while (i < A.length() && j < B.length()) {
            if (j == -1 || A.charAt(i) == B.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        if (j == B.length()) return true;
        return false;

    }


    public int[] getNext(String B) {
        int[] next = new int[B.length()];
        char[] chs = B.toCharArray();
        int k = -1, j = 0;
        next[0] = -1;

        // k 总是等于 next[j]
        while (j < B.length() - 1) {
            // 求next[j+1]
            if (k == -1 || chs[j] == chs[k]) {
                // next[j+1] = j+1; k= k+1;
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

}
