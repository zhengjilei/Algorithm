package 剑指offer;

import org.junit.Test;

/**
 * 给定两个整数 m,n
 * 问 m需要改变多少个 二进制位使得 m==n
 * created by Ethan-Walker on 2018/12/4
 */
public class Q015C_ChangeToEqual {

    public int changeToEqual(int m, int n) {

        int k = m ^ n; // 异或结果中有多少个 1 表明需要改变多少位
        return numberOf1(k);
    }

    public int numberOf1(int n) {

        int count = 0;
        while (n != 0) {
            n = n & (n - 1); // 删除n的最右边的 1
            count++;
        }
        return count;
    }

    @Test
    public void testA() {
        System.out.println(changeToEqual(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}
