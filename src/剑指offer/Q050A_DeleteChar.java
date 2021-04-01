package 剑指offer;

import org.junit.Test;

/**
 * 输入两个字符串，从第一个字符串中删除在第二个字符串中出现的字符
 * created by Ethan-Walker on 2018/12/11
 */
public class Q050A_DeleteChar {

    public String delete(String s1, String s2) {

        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        byte[] hash = new byte[256];
        for (int i = 0; i < ch2.length; i++) {
            hash[ch2[i]] = 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ch1.length; i++) {
            if (hash[ch1[i]] != 1) {
                // 不在 s2 中
                sb.append(ch1[i]);
            }
        }
        return sb.toString();
    }


    @Test
    public void test() {
        System.out.println(delete("We are students.", "aeiou"));
    }

}
