package leetcode;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Q069_Sqrt {
    public int mySqrt(int x) {
        if (x <= 1) return x;
        long i = 1;
        while (i * i <= x) {
            i++;
        }
        return (int) i - 1;
    }

    // 二分
    public int mySqrt2(int x) {
        if (x <= 1) return x;

        long l = 1, r = x - 1;
        long mid;
        long mul;
        while (l <= r) {
            mid = l + r >> 1;
            mul = mid * mid;
            if (mul <= x) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return (int) r;// r指向第一个等于 sqrt(x) 或小于sqrt(x)且最接近的整数
    }

}
