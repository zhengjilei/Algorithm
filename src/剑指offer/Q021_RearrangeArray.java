package 剑指offer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 调整数组使 数组中的奇数位于左半部分，偶数位于右半部分
 * created by Ethan-Walker on 2018/12/6
 */
public class Q021_RearrangeArray {

    public void change(int[] a) {

        if (a.length == 0 || a.length == 1) return;
        int left = 0, right = a.length - 1;
        while (left < right) {
            while (left < right && (a[left] & 1) == 1) left++; // 在左边找到第一个偶数
            while (left < right && (a[right] & 1) == 0) right--; // 在右边找到第一个奇数
            if (left < right) {
                swap(a, left, right);
            }
        }

    }

    public void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    @Test
    public void test() {
        int n = (int) (Math.random() * 20) + 20;
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = (int) (Math.random() * 100);
        }

        System.out.println(Arrays.toString(a));
        change(a);
        System.out.println(Arrays.toString(a));
    }
}
