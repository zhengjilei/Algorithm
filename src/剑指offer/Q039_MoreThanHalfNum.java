package 剑指offer;

import org.junit.Test;

/**
 * 数组中有一个数出现的次数超过数组的一半,找出该数
 * <p>
 * 常规思路：
 * 1.如果数组数值限定为整数，且范围不大，可用空间换空间
 * 比如数值范围在[0~100)
 * 可用hash 法：hash[100] 各数值作为 key，对应下标 ，数值的次数作为 value
 * 如 hash[a[i] ]++; // 表示数值为a[i] ，对应的次数累计+1
 * <p>
 * <p>
 * 时间复杂度: O(n)  n 为数组大小
 * 空间复杂度: O(m)  m 为数值的范围
 * <p>
 * <p>
 * 2. 对数组进行排序，既然该数在数组中的次数超过一半，那么中位数一定是该数了
 * <p>
 * 思路3：选出第 k 小的数 O(n)，次数超过一半, 则 第 n/2 小的数一定是该数了
 * 缺点：当题目中假设成立的时候，找出的第 n/2 小的数才能保证是所要的数
 * 如果数组中没有一个数的次数超过数组的一半呢？
 * <p>
 * 解决？找出第n/2个数后，然后再遍历一遍统计次数
 *
 *
 * <p>
 * created by Ethan-Walker on 2018/12/9
 */
public class Q039_MoreThanHalfNum {

    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0) return 0;
        int num = kthMin(array.length / 2 + 1, array, 0, array.length - 1);   // 要找第 n/2 + 1 小的数，不要传 n/2 , 因为n=1 时，n/2 = 0 ，找第 0 小的数会出错

        if (checkMoreThanHalf(array, num)) {
            return num;
        } else {
            return 0;
        }
    }

    public int kthMin(int k, int[] a, int left, int right) {
        if (k <= 0) return -1;
        int relativePos = randomPartition(a, left, right);
        if (relativePos + 1 == k) {
            return a[relativePos + left];
        } else if (relativePos + 1 < k) {
            // 在右半部分找第 k - (relativePos+1) 的数
            return kthMin(k - relativePos - 1, a, relativePos + left + 1, right);
        } else {
            return kthMin(k, a, left, left + relativePos - 1);
        }
    }

    /**
     * 对数组 a 划分成左右两部分
     *
     * @param a
     * @param left
     * @param right
     * @return
     */
    public int randomPartition(int[] a, int left, int right) {
        int pivotIndex = (int) (Math.random() * (right - left + 1)) + left;
        swap(a, pivotIndex, right);

        int i = left, j = right - 1;
        while (i < j) {
            while (i < j && a[i] <= a[right]) i++;
            while (i < j && a[j] >= a[right]) j--;
            if (i < j) {
                swap(a, i, j);
            }
        }

        swap(a, i, right);
        return i - left;
    }

    public void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public boolean checkMoreThanHalf(int[] a, int num) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == num) {
                count++;
            }
        }
        if (count > a.length / 2) {
            return true;
        }
        return false;
    }



    @Test
    public void test() {
        int[] a = new int[]{1, 3, 4, 5, 2, 2, 2, 2, 2};
        int num = MoreThanHalfNum_Solution(a);
        if (checkMoreThanHalf(a, num)) {
            System.out.println("次数大于数组长度一半的数:" + num);
        } else {
            System.out.println("不存在次数大于数组长度一半的数");
        }
    }


}
