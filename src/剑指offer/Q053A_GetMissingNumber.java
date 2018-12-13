package 剑指offer;

import org.junit.Test;

/**
 * 长度为 n-1 的递增排序且没有重复元素的整数数组
 * 数组中各值的范围都在 0~n-1 范围之内
 * 则0~n-1（一共n个数）有且只有一个不在该数组内,找出该数
 * created by Ethan-Walker on 2018/12/14
 */
public class Q053A_GetMissingNumber {

    /**
     * 哈希解决
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     * <p>
     * 缺点： 没有利用数组是排序的这一条件
     *
     * @param a
     * @param n
     * @return
     */
    public int getMissingNumber(int[] a, int n) {
        boolean[] exist = new boolean[n];
        // exist[i] = true 表示元素在数组内
        for (int i = 0; i < a.length; i++) {
            exist[i] = true;
        }

        for (int i = 0; i < n; i++) {
            if (!exist[i]) return i;
        }
        return -1;
    }

    /**
     * 数组元素在0~n-1 范围内，数组长度为 n-1 ，且数组是排序的
     * 意味着前一部分元素下标和数值是相同的，剩余部分下标比数值都多1
     * 找到最后一个下标和数值相同的元素
     * 或者找到第一个下标和数值不相同的元素
     *
     * @param a
     * @param n
     * @return
     */
    public int getMissingNumber2(int[] a, int n) {

        int left = 0, right = a.length - 1, mid = 0;

        while (left <= right) {
            mid = (left + right) >> 1;
            if (a[mid] != mid) {
                if (mid == 0 || a[mid - 1] == mid - 1) { // 前一个匹配，说明当前位置是第一个不匹配
                    return mid;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (left == a.length) {// n-1个元素分别从 0~n-2 无间隔的，找不到下标和数值不相同的元素，缺少数值 n-1
            return a.length;
        }

        return -1;// 无效输入
    }

    @Test
    public void test() {
        int[] a = new int[]{0, 1, 2, 3, 5, 6, 7};
        System.out.println(getMissingNumber2(a, 8));
    }
}
