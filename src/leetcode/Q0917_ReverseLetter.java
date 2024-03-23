package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/14
 */
public class Q0917_ReverseLetter {


    public String reverseOnlyLetters(String s) {
        char[] chs = s.toCharArray();

        int sum = chs.length - 1;
        int i = 0, j = sum;
        while (i < j) {
            if (!isLetter(chs[i])) {
                i++;
            } else if (!isLetter(chs[j])) {
                j--;
            } else {
                swap(chs, i, j);
                i++;
                j--;
            }
        }
        return new String(chs);
    }

    void swap(char[] chs, int i, int j) {
        char c = chs[i];
        chs[i] = chs[j];
        chs[j] = c;
    }

    public boolean isLetter(char c) {
        if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') return true;
        return false;
    }


    @Test
    public void test(){
        System.out.println(reverseOnlyLetters("z<*zj"));
    }


}
