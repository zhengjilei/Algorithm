package leetcode;

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
public class Q004_MedianTwoSorted {

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
     *
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

    /**
     * 两个有序数组 求中位数
     * 时间复杂度: O((m+n)/2)
     * 空间复杂度: O(1)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        int filterCount = total - 1 >> 1;
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length && filterCount > 0) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
            filterCount--;
        }
        while (i < nums1.length && filterCount > 0) {
            i++;
            filterCount--;
        }
        while (j < nums2.length && filterCount > 0) {
            j++;
            filterCount--;
        }
        int[] si = new int[]{i};
        int[] sj = new int[]{j};
        if (total % 2 == 0) {
            //取中间两位数
            int first = getMin(nums1, nums2, si, sj);
            int second = getMin(nums1, nums2, si, sj);
            return (first + second) / 2.0;
        } else {
            return getMin(nums1, nums2, si, sj);
        }

    }

    // 得到最小值之后，要将所在数组对应的位置右移一位
    public int getMin(int[] nums1, int[] nums2, int[] i, int[] j) {
        if (i[0] == nums1.length) return nums2[j[0]++];
        if (j[0] == nums2.length) return nums1[i[0]++];

        if (nums1[i[0]] <= nums2[j[0]]) {
            return nums1[i[0]++];
        } else {
            return nums2[j[0]++];
        }

    }

}
