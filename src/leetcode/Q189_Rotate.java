package leetcode;

/**
 * created by Ethan-Walker on 2019/1/14
 */
public class Q189_Rotate {
    void swap(int[] chs, int i, int j) {
        int c = chs[i];
        chs[i] = chs[j];
        chs[j] = c;
    }


    public void rotateStr(int[] chs, int size) {
        if (chs == null || chs.length <= 1 || size <= 0 || size == chs.length) return;

        if (size > chs.length) {
            size %= chs.length;
        }
        // 先整体逆序，再将前左右半区各自逆序
        for (int i = 0; i < chs.length / 2; i++) {
            swap(chs, i, chs.length - i - 1);
        }

        // 逆转左半区
        int leftLen = size;
        for (int i = 0; i < leftLen / 2; i++) {
            swap(chs, i, leftLen - i - 1);
        }

        // 逆转右半区, leftLen  为右半区起点
        int rightLen = chs.length - leftLen;
        int limit = leftLen + rightLen / 2;
        int sum = chs.length - 1 + leftLen;
        for (int i = leftLen; i < limit; i++) {
            swap(chs, i, sum - i);
        }

    }

}
