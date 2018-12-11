package 剑指offer;

import org.junit.Test;

/**
 * 同位词: 两个字符串中，包含的字符的种类相同，且各个字符对应的次数相同，则称这两个字符串互为同位词
 * 例如: silent listen
 * created by Ethan-Walker on 2018/12/11
 */
public class Q050C_Anagram {

    public boolean judge(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.length() == 0 && s2.length() == 0) return true;

        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();

        byte[] hash = new byte[256];


        for (char c : chs1) {
            hash[c]++;
        }
        for (char c : chs2) {
            hash[c]--;
        }

        for (int i = 0; i < 256; i++) {
            if (hash[i] != 0)
                return false;
        }
        return true;
    }

    @Test
    public void test() {

        System.out.println(judge("listen", "silent"));
        System.out.println(judge("evil", "live"));

    }
}
