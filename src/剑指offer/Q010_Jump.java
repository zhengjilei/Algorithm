package 剑指offer;

import org.junit.Test;

/**
 * 青蛙跳台阶
 * 一次可以跳1级，一次可以跳 2级
 * 求跳上 n 层台阶一共有多少种跳法
 * created by Ethan-Walker on 2018/12/2
 */
public class Q010_Jump {

    public long jumpKind(int n) {
        if (n <= 0) return 0;
        long a = 1, b = 1; // 跳上 1层 1种,跳上 2层 2种
        long c = 1;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    @Test
    public void testJumpKind(){
        System.out.println(jumpKind(1));
        System.out.println(jumpKind(2));
        System.out.println(jumpKind(3));
        System.out.println(jumpKind(4));
        System.out.println(jumpKind(5));
        System.out.println(jumpKind(100));
        System.out.println(jumpKind(1000));
    }
}
