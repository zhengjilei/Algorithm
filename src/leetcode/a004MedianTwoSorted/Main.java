package leetcode.a004MedianTwoSorted;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ethan-Walker on 2018/6/12.
 */
public class Main {

    @Test
    public void a() {
        Random random = new Random();
        int m = (10 + random.nextInt(10)); //  a 数组的长度  [10,20]
        int n = (10 + random.nextInt(10)); //  b 数组的长度
        System.out.println(m + "," + n);
        int[] a = new int[m];
        int[] b = new int[n];
        for (int i = 0; i < m; i++) {
            a[i] = random.nextInt(20);
        }
        for (int i = 0; i < n; i++) {
            b[i] = random.nextInt(20);
        }
        Arrays.sort(a);
        Arrays.sort(b);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        double t = findMedianSortedArrays(a, b);
        System.out.println(t);

    }

    /**
     * 按归并排序中的合并法，找到 a[mid] 元素
     * (m+n)/2  O(m+n)
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // 默认是 升序
        int m = nums1.length, n = nums2.length;

        int count = m + n;

        int[] target = new int[count];
        int cnt = 0, i = 0, j = 0;
        int mid = count / 2;

        while (i < m && j < n && cnt <= mid) {
            if (nums1[i] < nums2[j]) {
                target[cnt++] = nums1[i++];
            } else {
                target[cnt++] = nums2[j++];
            }
        }
        if (cnt <= mid) {
            if (i == m) {
                while (cnt <= mid) {
                    target[cnt++] = nums2[j++];
                }
            } else if (j == n) {
                while (cnt <= mid) {
                    target[cnt++] = nums1[i++];
                }
            }
        }

        if (count % 2 == 0) {
            return (target[mid] + target[mid - 1]) / 2.0;
        } else {
            return target[mid];
        }

    }
}
