package 剑指offer;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 在一个排序数组中某个数出现的次数
 * created by Ethan-Walker on 2018/12/13
 */
public class Q053_CountOfNumberInSortedArray {


    /**
     * 最坏情况下 时间复杂度: O(n)
     *
     * @param array
     * @param key
     * @return
     */
    public int getCountOfNumberInSortedArray(int[] array, int key) {
        int index = binarySearch(array, key);
        int count = 1;
        if (index == -1) return 0;
        int t = index - 1;
        while (array[t] == array[index]) count++;
        t = index + 1;
        while (array[t] == array[index]) count++;
        return count;

    }

    public int binarySearch(int[] a, int key) {
        int left = 0, right = a.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (a[mid] < key) {
                left = mid + 1;
            } else if (a[mid] > key) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * 两次二分查找
     * 分别找出第一次出现的位置和最后一次出现的位置
     *
     * @param array
     * @param key
     * @return
     */
    public int getCountOfNumberInSortedArray2(int[] array, int key) {

        int first = getFirstBinarySearch(array, key);
        if (first == -1) return 0;
        int last = getLastBinarySearch(array, key);
        return last - first + 1;
    }

    /**
     * 二分查找，找第一次出现的位置
     * <p>
     * 刚开始写法出现错误
     * if (a[mid]>= key) {
     * right = mid - 1; // = 时也往左找
     * }
     * <p>
     * 举例： 0  1  2 2 4 key =2
     * left =0 right =3 ,mid = 2 a[mid] = key 找到 继续往左找
     * right =mid-1 = 1  （在 0 1 中找，已经找不到了  ）
     *
     * @param a
     * @param key
     * @return
     */
    public int getFirstBinarySearch(int[] a, int key) {
        int left = 0, right = a.length - 1, mid = 0;
        int firstIndex = -1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (a[mid] < key) {
                left = mid + 1;
            } else if (a[mid] == key) {
                firstIndex = mid;
                right = mid - 1;   //  继续往左找
            } else {
                right = mid - 1;
            }
        }
        return firstIndex;
    }


    /**
     * 二分查找，找最后一次出现的位置
     *
     * @param a
     * @param key
     * @return
     */
    public int getLastBinarySearch(int[] a, int key) {
        int left = 0, right = a.length - 1, mid = 0;
        int lastIndex = -1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (a[mid] < key) {
                left = mid + 1;
            } else if (a[mid] == key) {
                lastIndex = mid;
                left = mid + 1;       // 继续往右找
            } else {
                right = mid - 1;
            }
        }

        return lastIndex;
    }

    @Test
    public void test() {
        Random random = new Random();
        int n = random.nextInt(20) + 40;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(10) + 10;
        }
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        int count = getCountOfNumberInSortedArray2(array, 12);
        System.out.println(count);

    }


}
