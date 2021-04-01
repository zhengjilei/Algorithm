package leetcode;

/**
 * created by Ethan-Walker on 2019/2/27
 */
public class Q264_UglyNumber {

    public int nthUglyNumber(int n) {
        if (n <= 0) return -1;
        int[] uglyNumber = new int[n];
        uglyNumber[0] = 1;

        int m2 = 0, m3 = 0, m5 = 0;
        int index = 1;
        while (index < n) {
            // 计算下一个丑数
            uglyNumber[index] = getMinFrom3(uglyNumber[m2] * 2, uglyNumber[m3] * 3, uglyNumber[m5] * 5);

            // 更新 m2 m3 m5 的位置
            while (uglyNumber[m2] * 2 <= uglyNumber[index]) m2++; // 找到第一个 m2 位置，使得 uglyNumbers[m2]*2 > min
            while (uglyNumber[m3] * 3 <= uglyNumber[index]) m3++;
            while (uglyNumber[m5] * 5 <= uglyNumber[index]) m5++;

            index++;
        }

        return uglyNumber[n - 1];

    }

    public int getMinFrom3(int a, int b, int c) {
        a = a < b ? a : b;
        return a < c ? a : c;
    }

}
