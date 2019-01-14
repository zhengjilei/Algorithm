package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/14
 */
public class Q007_ReverseInt {
    public int reverse(int x) {
        int sum = 0;

        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;

        int maxq = Integer.MAX_VALUE / 10;
        int maxr = Integer.MAX_VALUE % 10;
        int r = 0;

        while (x != 0) {
            // 在要接近最大数、最小数时进行判断，x!=0 说明当前 sum 还要乘 10 + x%10
            if (sum < 0) {
                if (sum < minq || (sum == minq && x % 10 < minr)) {
                    return 0;
                }
            } else if (sum > 0) {
                if (sum > maxq || (sum == maxq && x % 10 > maxr)) {
                    return 0;
                }
            }
            sum = sum * 10 + x % 10;
            x /= 10;
        }
        return sum;
    }

    @Test
    public void test() {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(120));

        System.out.println(Integer.MIN_VALUE);

        System.out.println(reverse(2147483647));
        System.out.println(reverse(-2147483112));
        System.out.println(reverse(2113847412));

    }
}
