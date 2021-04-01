package programmer_interview;

import org.junit.Test;

/**
 * 第 n 年的奶牛个数
 * <p>
 * created by Ethan-Walker on 2019/1/1
 */
public class Q055A_CountOfCowInNYear {

    /**
     * f[n] = f[n-1]+f[n-3]
     *
     * @param n
     * @return
     */
    int count(int n) {
        if (n <= 3) return n;
        int a = 1, b = 2, c = 3, result = 0;
        for (int i = 4; i <= n; i++) {
            result = a + c;
            a = b;
            b = c;
            c = result;
        }
        return result;
    }

    @Test
    public void test() {
        System.out.println(count(4));
        System.out.println(count(5));
        System.out.println(count(6));
        System.out.println(count(7));
        System.out.println(count(8));
        System.out.println(count(9));
    }
}
