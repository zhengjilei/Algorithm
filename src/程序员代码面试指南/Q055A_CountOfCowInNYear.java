package 程序员代码面试指南;

/**
 * 第 n 年的奶牛个数
 * <p>
 * created by Ethan-Walker on 2019/1/1
 */
public class Q055A_CountOfCowInNYear {

    /**
     * f[n] = f[n-1]+f[n-3] = f[n-2]+f[n-4] +f[n-3]
     *
     * @param n
     * @return
     */
    int count(int n) {
        if (n <= 3) return n;
        int a = 1, b = 2, c = 3, result = 0;
        for (int i = 4; i <= n; i++) {
            result = a + b + c;
            a = b;
            b = c;
            c = result;
        }
        return result;
    }

}
