package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2018/12/28
 */
public class Q762_BinaryPrime {


    public int countPrimeSetBits(int l, int r) {
        int count = 0;
        for (int i = l; i <= r; i++) {
            if (isPrime(calBit1Count2(i))) {
                count++;
            }
        }
        return count;
    }

    /**
     * 统计数字 n 的二进制表示中 1 的总数
     *
     * @param n
     * @return
     */
    public int calBit1Count(int n) {
        int flag = 1;
        int count = 0;
        while (flag != 0) {
            if ((n & flag) != 0) count++; // 注意是 !=0 而不是 ==1 （错误，因为 n&flag 返回的是某一位上的 1 ，值不一定等于1,可能是 1,2,4,8,16）
            flag <<= 1;
        }
        return count;
    }

    // n-1  将最后一位1变成0，将最后一位1后面的0全部变成1
    // n&(n-1) 删去最后一位1
    public int calBit1Count2(int n) {

        int count = 0;
        while (n != 0) {
            count++;
            n = n & n - 1;
        }
        return count;
    }

    public boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    @Test
    public void test() {
        int result = countPrimeSetBits(6, 10);
        System.out.println(result);
    }
}
