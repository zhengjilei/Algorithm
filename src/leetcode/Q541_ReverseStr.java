package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/14
 */
public class Q541_ReverseStr {
    public String reverseStr(String s, int k) {
        char[] chs = s.toCharArray();
        int len = 0;
        for (int i = 0; i < chs.length; i += 2 * k) {
            if (chs.length - i < k) {
                reverse(chs, i, chs.length - 1);
            } else {
                reverse(chs, i, i + k - 1);
            }
        }
        return new String(chs);
    }

    public void reverse(char[] chs, int start, int end) {
        int limit = ((end - start + 1) >> 1) + start;
        int sum = start + end;
        char c;
        for (int i = start; i < limit; i++) {
            c = chs[i];
            chs[i] = chs[sum - i];
            chs[sum - i] = c;
        }
    }

    @Test
    public void test() {
        System.out.println(reverseStr("abcdefg", 2));
    }
}
