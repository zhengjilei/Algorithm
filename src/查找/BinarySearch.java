package 查找;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Ethan-Walker on 2018/4/1.
 */
public class BinarySearch {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int n = random.nextInt(100);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(200);
//            Arrays
        }

        Arrays.sort(arr);
        int value = arr[n - 10];
        int index = binarySearch(arr, value);
        int value2 = 200;
        int index2 = binarySearch(arr, value2);
        System.out.println(index);
        System.out.println(index2);
    }

    public static int binarySearch(int[] arr, int value) {

        int l = 0, r = arr.length - 1;
        int mid;
        while (l <= r) {
            mid = (r + l) / 2;
            if (value < arr[mid]) {
                r = mid - 1;
            } else if (value > arr[mid]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找，找到第一个值相等的
     *
     * @param arr
     * @param value
     * @return
     */
    public static int binarySearchFirst(int[] arr, int value) {
        int l = 0, r = arr.length - 1;
        int mid = 0;
        int firstEqualIndex = -1;
        while (l <= r) {
            mid = (l + r) >> 1;
            if (arr[mid] < value) {
                l = mid + 1;
            } else if (arr[mid] == value) {
                firstEqualIndex = mid;
                r = mid - 1;        // 继续往左找
            } else {
                r = mid - 1;
            }
        }
        return firstEqualIndex;
    }

    /**
     * 二分查找，找到第一个大于等于 value 的位置
     *
     * @param arr
     * @param value
     * @return
     */
    public static int binarySearchFirstNotLess(int[] arr, int value) {

        int l = 0, r = arr.length - 1, mid = 0;
        int firstNotLessIndex = -1;
        while (l <= r) {
            mid = (l + r) >> 1;
            if (arr[mid] < value) {
                l = mid + 1;
            } else {
                // arr[mid]>=value
                firstNotLessIndex = mid;
                r = mid - 1; // 往前找
            }
        }
        return firstNotLessIndex;
    }

    /**
     * 二分查找，找到最后一个小于等于 value的位置
     *
     * @param arr
     * @param value
     * @return
     */
    public static int binarySearchFirstNotMore(int[] arr, int value) {
        int l = 0, r = arr.length - 1, mid = 0;
        int firstNotMore = -1;
        while (l <= r) {
            mid = (l + r) >> 1;
            if (arr[mid] > value) {
                r = mid - 1;
            } else {
                firstNotMore = mid;
                l = mid + 1; // 往后找
            }
        }
        return 0;
    }

    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(binarySearchFirstNotLess(a, 10));
    }
}
