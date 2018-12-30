package leetcode;

import java.util.Arrays;

/**
 * 小于非负整数 n 的质数的数量
 * created by Ethan-Walker on 2018/12/28
 */
public class Q204_Prime {

    // 最简单的方法是判断每一个数是否是质数，时间复杂度为 O(n^1.5) 超时

    /**
     * 方法一：eratosthenes aishi筛选法
     */
    public int eratosthenes(int n) {
        if (n <= 2) return 0;
        Boolean[] isPrime = new Boolean[n];
        Arrays.setAll(isPrime, index -> true); // 全部设置成 true
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
                for (int j = i * 2; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return count;
    }

    /**
     * 埃氏筛选法改进
     */
    public int eratosthenes2(int n) {
        if (n <= 2) return 0;
        boolean[] isPrime = new boolean[n];
        for (int i = 0; i < n; i++) {
            isPrime[i] = true;
        }

        int count = 0;

        int i = 2;
        for (; i * i < n; i++) {
            if (isPrime[i]) {
                count++;
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        while (i < n) {
            if (isPrime[i]) count++;
            i++;
        }
        return count;
    }


}
