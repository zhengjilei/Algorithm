package 剑指offer;

import org.junit.Test;

/**
 * 给定一个递增数组的一个旋转，求最小元素
 * created by Ethan-Walker on 2018/12/3
 */
public class Q011_RotateMin {


    /**
     * 考虑特殊情况
     * <p>
     * 1. 旋转部分长度为 0 示例 ： 1 2 3 4 5
     * 2. a[mid] = a[left] = a[right] 示例: 1 0 1 1 1  或者 1 1 1 0 1
     * 3. 数组长度为1、0
     *
     * @param a
     * @return
     */
    public int getRotateMin(int[] a) throws Exception {

        int i = 0, j = a.length - 1;
        if (j < i) throw new Exception("数组不能为空");
        if (a[i] < a[j]) {
            // 第一个数字小于最后一个数字，说明旋转的部分长度为 0
            return a[i];
        }
        int mid;
        while (j - i > 1) {
            // i、j 始终指向左边递增序列、右边递增序列
            mid = (j + i) / 2;

            if (a[mid] == a[i] && a[mid] == a[j]) {
                // 特殊情况，顺序查找
                // 例  1 0 1  1 1 或者 1 1 1 0 1
                int min = a[i];
                for (int k = i + 1; k <= j; k++) {
                    if (a[k] < min) {
                        min = a[k];
                    }
                }
                return min;
            }
            if (a[mid] >= a[i]) {
                // 最小值在右边
                i = mid;
            } else if (a[mid] <= a[j]) {
                // 最小值在左边
                j = mid;
            }
        }

        return a[j];
    }

    @Test
    public void testGetRotateMin() throws Exception {
        int[] a = new int[]{1, 2};
        System.out.println(getRotateMin(a));
    }

}
