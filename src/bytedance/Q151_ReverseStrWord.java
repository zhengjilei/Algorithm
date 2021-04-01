package bytedance;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/2
 */
public class Q151_ReverseStrWord {

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        s = s.trim();
        char[] chs = s.toCharArray();
        // 先整体反转
        for (int i = 0; i < chs.length / 2; i++) {
            swap(chs, i, chs.length - i - 1);
        }

        // 再局部反转
        char[] res = new char[chs.length];
        int resIndex = 0;

        int start = 0, end = 0;
        while (end < chs.length) {
            while (end < chs.length && chs[end] != ' ') {
                end++;
            }
            // 反转 [start,end-1]
            int k = end - 1;
            while (k >= start) {
                res[resIndex++] = chs[k--];
            }
            // end==chs.length 说明后面没有字符串了，不用加 ' '
            if (end < chs.length) {
                res[resIndex++] = ' ';
            }

            while (end < chs.length && chs[end] == ' ') end++;
            start = end;
        }

        return String.valueOf(res, 0, resIndex);

    }

    public void swap(char[] chs, int i, int j) {
        char c = chs[i];
        chs[i] = chs[j];
        chs[j] = c;
    }


    @Test
    public void test() {
        System.out.println(reverseWords(" the sky is blue "));
        System.out.println(reverseWords("blue  "));
        System.out.println(reverseWords(" the  sky  is blue"));
    }

}
