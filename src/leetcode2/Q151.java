package leetcode2;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class Q151 {

    public String reverseWords(String s) {
        if (s == null) return s;
        s = s.trim();
        if (s.length() <= 1) return s;
        char[] chs = s.toCharArray();
        reverse(chs, 0, chs.length - 1);

        int i = 0;
        char[] res = new char[chs.length];
        int resIndex = 0;
        while (i < chs.length) {
            int j = i + 1;
            while (j < chs.length && chs[j] != ' ') j++;

            for (int k = j - 1; k >= i; k--) { // 反转添加到新数组
                res[resIndex++] = chs[k];
            }

            while (j < chs.length && chs[j] == ' ') j++;
            i = j;
            if (i < chs.length) {
                res[resIndex++] = ' ';
            }
        }
        return String.valueOf(res, 0, resIndex);
    }

    public void reverse(char[] chs, int start, int end) {
        while (start < end) {
            swap(chs, start, end);
            start++;
            end--;
        }
    }

    private void swap(char[] nums, int i, int j) {
        char t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    @Test
    public void test() {
        System.out.println(reverseWords("abc"));
        System.out.println(reverseWords("  abc"));
        System.out.println(reverseWords(" "));
        System.out.println(reverseWords(""));
        System.out.println(reverseWords(" abc def  g"));
    }
}
