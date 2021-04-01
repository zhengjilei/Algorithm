package math;

import java.util.BitSet;

/**
 * 小于等于非负整数 n 的质数的数量
 * created by Ethan-Walker on 2018/12/28
 */
public class Prime {
    /**
     * 方法一：判断每个数是否是素数
     * 超时
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) count++;
        }
        return count;
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }


    /**
     * 方法二：eratosthenes 线性筛选法
     */
    public int eratosthenes(int n) {
        if (n <= 1) return 0;
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            isPrime[i] = true;
        }

        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                count++;
                for (int j = i * 2; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return count;
    }

    /**
     * 线性筛选法改进
     */
    public int eratosthenes2(int n) {
        if (n <= 1) return 0;
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            isPrime[i] = true;
        }

        int count = 0;
        int i = 2;
        for (; i * i <= n; i++) { // 改进点1
            if (isPrime[i]) {
                count++;
                for (int j = i * i; j <= n; j += i) { // 改进点2
                    isPrime[j] = false;
                }
            }
        }

        for (; i <= n; i++) {
            if (isPrime[i]) count++;
        }
        return count;
    }

    // BitSet 代替 boolean 处理
    public int eratosthenes3(int n) {
        if (n <= 1) return 0;
        BitSet isPrime = new BitSet(n + 1);
        for (int i = 0; i <= n; i++) {
            isPrime.set(i);
        }

        int count = 0;
        int i = 2;
        for (; i * i <= n; i++) {
            if (isPrime.get(i)) {
                count++;
                for (int j = i * i; j <= n; j++) {
                    isPrime.clear(j);
                }
            }
        }
        for (; i <= n; i++) {
            if (isPrime.get(i)) count++;
        }
        return count;
    }


    // 欧拉筛选法，没看懂

}
