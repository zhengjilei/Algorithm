package leetcode;

/**
 * created by Ethan-Walker on 2019/1/14
 */
public class Q344_ReverseStr {

    public String reverseString(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < chars.length / 2; i++) {
            c = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = c;
        }
        return new String(chars);
    }

}
