package 剑指offer;

import org.junit.Test;

/**
 * 判断一个整数是否是 2 的整数次方
 * 若一个数是 2 的整数次方，则数的二进制表示中只存在一个 1
 * created by Ethan-Walker on 2018/12/4
 */
public class Q015B_JudgeIsPowOfTwo {

    /**
     * 正确 但效率低
     * @param n
     * @return
     */
    public boolean judge(int n) {
        if (n < 0) return false;

        int count = 0;
        int t;
        while (n != 0 && count <= 1) {
            if (n % 2 == 1) {
                count++;
            }
            n /= 2;
        }
        if (count > 1) return false;
        return true;
    }

    /**
     * n&(n-1)  删除 n 末尾最后一个 1，如果结果不等于0，说明数中还有 1 ，即数中存在多个1
     *
     * @param n
     * @return
     */
    public boolean judge2(int n) {
        if (n < 0) return false;
        if (n == 0) return true;
        if ((n & (n - 1)) != 0) {
            return false;
        }
        return true;
    }

    @Test
    public void testA() {

        int i = 0;
/*        for ( i = 0; i < Integer.MAX_VALUE; i++) {
            if (judge(i) != judge2(i)) {
                System.out.println(false + "," + i);
            }
        }
        if (judge(i) != judge2(i)) {
            System.out.println(false + "," + i);
        }*/
        for (i = 0; i < Integer.MAX_VALUE; i++) {
            judge(i);
        }
    }
}
