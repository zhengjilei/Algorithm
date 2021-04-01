package 剑指offer;


import org.junit.Test;

/**
 * 删除字符串中所有重复的字符(第一次出现的不删除)
 * 例如：输入 google 输出 gole
 * created by Ethan-Walker on 2018/12/11
 */
public class Q050B_DeleteDupChar {

    public String deleteDupChar(String s) {

        byte[] hash = new byte[256];
        char[] chs = s.toCharArray();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chs.length; i++) {
            if (hash[chs[i]] == 0) {
                sb.append(chs[i]);
            }
            hash[chs[i]]++;
        }
        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(deleteDupChar("google"));
    }

}
