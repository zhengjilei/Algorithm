package 剑指offer;

import org.junit.Test;

/**
 * 只包含因子 2/3/5 的称为丑数
 * 12 是丑数，12 = 2*3*3
 * 78不是， 78 = 2*3*13
 * <p>
 * created by Ethan-Walker on 2018/12/11
 */
public class Q049_UglyNumber {


    /**
     * 获取第 k 个丑数
     *
     * @param k
     * @return
     */
    public int getKthUglyNumbers(int k) {

        int cnt = 0;
        int i = 0;
        while (cnt < k) {
            i++;
            if (isUgly(i)) {
                cnt++;
            }
        }

        return i;
    }

    boolean isUgly(int n) {

        while (n % 2 == 0)
            n /= 2;
        while (n % 3 == 0)
            n /= 3;
        while (n % 5 == 0)
            n /= 5;

        return n == 1;
    }


    public int getKthUglyNumber(int k) {
        if (k == 0) return 0;
        int[] uglyNumbers = new int[k];
        uglyNumbers[0] = 1;

        int m2 = 0, m3 = 0, m5 = 0;// 表示第一个位置

        int nextUglyIndex = 1;
        while (nextUglyIndex < k) {
            int min = getMinFrom3(uglyNumbers[m2] * 2, uglyNumbers[m3] * 3, uglyNumbers[m5] * 5);
            uglyNumbers[nextUglyIndex] = min;

            while (uglyNumbers[m2] * 2 <= min) m2++; // 找到第一个 m2 位置，使得 uglyNumbers[m2]*2 > min
            while (uglyNumbers[m3] * 3 <= min) m3++;
            while (uglyNumbers[m5] * 5 <= min) m5++;

            nextUglyIndex++;
        }

        return uglyNumbers[k - 1];
    }

    public int getMinFrom3(int a, int b, int c) {
        a = a < b ? a : b;
        return a < c ? a : c;
    }

    @Test
    public void test() {
        System.out.println(isUgly(12));
        long start = System.currentTimeMillis();
        System.out.println(getKthUglyNumbers(1500));
        System.out.println("耗时: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        System.out.println(getKthUglyNumber(1500));
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
    }
}
