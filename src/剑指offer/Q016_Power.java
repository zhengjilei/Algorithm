package 剑指offer;

import org.junit.Test;

/**
 * 数值的整数次方
 * created by Ethan-Walker on 2018/12/4
 */
public class Q016_Power {

    public double power(double base, int expo) {
        if (base == 0) return 0;
        double result = 1;
        boolean negative = (expo < 0); // 负
        if (negative) {
            expo = -expo;
        }

        result = powerWithUnsignedNum(base, expo);
        if (negative) {
            return 1.0 / result;
        }
        return result;
    }

    double powerWithUnsignedNum(double base, int expo) {
        if (base == 0) return 0;
        if (expo == 0) return 1;
        double result = 1.0;
        for (int i = 1; i <= expo; i++) {
            result *= base;
        }
        return result;
    }


    /**
     * a[n] = a[n/2] * a[n/2]  n 是偶数
     * a[n] = a[n/2] * a[n/2] * base;
     *
     * @param base
     * @param expo
     * @return
     */
    double powerWithUnsignedNum2(double base, int expo) {
        if (expo == 0) return 1;
        if (expo == 1) return base;

        double result = powerWithUnsignedNum2(base, expo / 2);
        result *= result;

        if ((expo & 1) == 1) {
            // 奇数
            result *= base;
        }
        return result;
    }

    @Test
    public void test() {
        System.out.println(power(0, 0));
        System.out.println(power(0, 1));
        System.out.println(power(32, 0));
        System.out.println(power(0, -32));
        System.out.println(power(2, -3));
    }
}
