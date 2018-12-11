package 剑指offer;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2018/12/11
 */
public class Q046_TranslateNumToLetter {


    /**
     * 从左到右递归，可能有重复子问题
     *
     * @param num
     * @return
     */
    public int translate(int num) {

        if (num < 0) return 0;
        if (num < 10) return 1;

        String s = String.valueOf(num);

        if (s.length() == 2) {
            if (num <= 25) {
                return 2;
            } else
                return 1;
        }
        int a = Integer.parseInt(s.substring(1, s.length()));
        int prefix = Integer.parseInt(s.substring(0, 2));
        int suffix = Integer.parseInt(s.substring(2, s.length()));

        if (prefix <= 25) {
            return translate(a) + translate(suffix);
        } else {
            return translate(a);
        }

    }

    /**
     * 数组，消除重复子问题
     * 空间复杂度: O(log10 n)
     * <p>
     * a[i] 表示从数的第 0 位 到 第 i 位构成的数字有多少种翻译
     * <p>
     * a[i] = a[i-1]
     * + a[i-2] 当 str[i-1]str[i] 构成的两位数k>=10 &&k<=25 时
     *
     * @param num
     * @return
     */
    public int translate2(int num) {
        if (num < 0) return 0;
        String str = String.valueOf(num);
        int[] a = new int[str.length()];

        a[0] = 1;
        for (int i = 1; i < str.length(); i++) {
            a[i] += a[i - 1];
            int t = Integer.parseInt(str.substring(i - 1, i + 1)); // str[i-1] str[i] 构成的两位数整数
            if (t >= 10 && t <= 25) {
                if (i == 1) {
                    a[i] += 1;
                } else {
                    a[i] += a[i - 2];
                }
            }
        }
        return a[str.length() - 1];
    }

    @Test
    public void test() {
        for (int i = 0; i <= 1000000; i++) {
            if (translate(i) != translate2(i)) {
                System.out.println(i);
            }
        }
    }
}
