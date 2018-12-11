package 剑指offer;

/**
 * 字符串中第一个只出现一次的字符
 * created by Ethan-Walker on 2018/12/11
 */
public class FirstNotRepeatingChar {

    /**
     * 返回位置
     *
     * @param s
     * @return
     */
    public int getFirstCharNotRepeating(String s) {
        char[] chs = s.toCharArray();
        int[] count = new int[256]; // count[chs[i]] = 2 表示字符 chs[i] 出现的累计次数为 2

        for (int i = 0; i < chs.length; i++) {
            count[chs[i]]++;
        }
        for (int i = 0; i < chs.length; i++) {
            if (count[chs[i]] == 1) {
                return i;
            }
        }
        return -1;
    }
}
