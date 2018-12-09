package 剑指offer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 寻找最小的 k 个数
 * <p>
 * 方法一：排序 O(nlogn)
 * <p>
 * 方法二：
 * 和寻找第k小的数思路相同，由于划分后，只要找到第k小的数的位置，前面的数都比其小.
 * <p>
 * 优点：时间复杂度低  O(n)  空间复杂度: O(1)
 * 缺点: 会改变原数组
 * <p>
 * created by Ethan-Walker on 2018/12/9
 */
public class Q040_TopKMin {

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        int index = getTopKMinIndex(input, 0, input.length - 1, k);
        ArrayList<Integer> list = new ArrayList<>();
        if (index != -1) {
            for (int i = 0; i <= index; i++) {
                list.add(input[i]);
            }
        }
        return list;
    }

    /**
     * 获取第k小的数的下标，注意不是数值
     */
    public int getTopKMinIndex(int[] array, int left, int right, int k) {
        if (k <= 0 || k > right - left + 1 || array == null || array.length == 0 || left > right) return -1;

        int relativePos = randomPartition(array, left, right);
        if (relativePos + 1 == k) {
            return relativePos + left; // 返回第k小的元素实际位置
        } else if (relativePos + 1 < k) {
            // 第 k 小数在右半部分
            return getTopKMinIndex(array, relativePos + left + 1, right, k - (relativePos + 1));
        } else {
            // 第 k 小数在左半部分
            return getTopKMinIndex(array, left, relativePos + left - 1, k);
        }
    }

    public int randomPartition(int[] array, int left, int right) {
        int pivotIndex = (int) (Math.random() * (right - left + 1) + left);
//        int pivotIndex = right;
        swap(array, pivotIndex, right);
        int i = left, j = right ;
        while (i < j) {
            while (i < j && array[i] <= array[right]) i++;
            while (i < j && array[j] >= array[right]) j--;
            if (i < j) {
                swap(array, i, j);
            }
        }
        swap(array, i, right);
        return i - left;

    }

    public void swap(int[] a, int i, int j) {
        int k = a[i];
        a[i] = a[j];
        a[j] = k;
    }

    @Test
    public void test() {
        Random random = new Random();
        int n = random.nextInt(20) + 15;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(100);
        }

        int k = random.nextInt(10);
        int kthMinIndex = getTopKMinIndex(array, 0, n - 1, k);

        System.out.println("n=" + n);
        System.out.println("arrays= " + Arrays.toString(array));

        System.out.println("k=" + k);
        System.out.print("kthMin= ");
        for (int i = 0; i <= kthMinIndex; i++) {
            System.out.printf("%5d", array[i]);
        }
        System.out.println();

        Arrays.sort(array);
        System.out.println("sorted= " + Arrays.toString(array));
    }

    @Test
    public void test2() {
        int[] a = new int[]{15, 34, 34, 38, 46, 62, 64, 47, 66, 72, 87, 99, 98, 95, 92};
        int topKMinIndex = getTopKMinIndex(a, 0, a.length - 1, 7);
        System.out.println(a[topKMinIndex]);
        for (int i = 0; i <= topKMinIndex; i++) {
            System.out.printf("%5d", a[i]);
        }
        System.out.println();
    }
}
