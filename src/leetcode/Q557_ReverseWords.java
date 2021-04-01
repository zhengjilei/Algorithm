package leetcode;

/**
 * created by Ethan-Walker on 2019/1/14
 */
public class Q557_ReverseWords {

    public String reverseWords(String s) {
        char[] chs = s.toCharArray();
        int start = 0, end = 0;
        while (end < chs.length) {
            while (end < chs.length && chs[end] != ' ') {
                end++;
            }
            // 对 start, end-1 进行逆序
            int len = end - start;
            int sum = end - 1 + start; // 关于中点对称的两个下标之和
            for (int i = start; i < start + len / 2; i++) {
                swap(chs, i, sum - i);
            }
            start = ++end;
        }
        return new String(chs);
    }

    void swap(char[] chs, int i, int j) {
        char c = chs[i];
        chs[i] = chs[j];
        chs[j] = c;
    }
}
