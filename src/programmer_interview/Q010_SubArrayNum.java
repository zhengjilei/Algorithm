package programmer_interview;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * 给定一个数组，求所有满足下列条件的子数组数目
 * 最大值减去最小值小于或者等于 num 的子数组数量
 * created by Ethan-Walker on 2018/12/19
 */
public class Q010_SubArrayNum {


    public int getNum(int[] arr, int num) {

        ArrayDeque<Integer> minDeque = new ArrayDeque<>();
        ArrayDeque<Integer> maxDeque = new ArrayDeque<>();

        int i = 0, j = 0;
        int count = 0;
        while (i < arr.length) {
            while (j < arr.length) {

                while (!maxDeque.isEmpty() && arr[j] >= arr[maxDeque.peek()]) {
                    maxDeque.pollLast();
                }
                maxDeque.offerLast(j);

                while (!minDeque.isEmpty() && arr[j] <= arr[minDeque.peek()]) {
                    minDeque.pollLast();
                }
                minDeque.offerLast(j);

                if (maxDeque.peek() - minDeque.peek() > num) {
                    break;
                }
                j++;
            }

            if (minDeque.peekFirst() == i) {
                minDeque.pollFirst();
            }
            if (maxDeque.peekFirst() == i) {
                maxDeque.pollFirst();
            }
            count += j - i;
            i++;
        }
        return count;
    }

    @Test
    public void test() {
        int[] arr = {12};
        int num = 1;
        System.out.println(getNum(arr, 1));
    }

    /**
     * n*(n-1)/2 个子数组
     * 再对每个子数组求最大值，最小值
     * 时间复杂度: O(n^3)
     *
     * @param arr
     * @param num
     * @return
     */
    public int simpleSolve(int[] arr, int num) {

        int length = arr.length;

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int count = 0;
        // i 表示数组起始点， j 表示数组末位置
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                max = Integer.MIN_VALUE;
                min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    if (arr[k] > max) {
                        max = arr[k];
                    }
                    if (arr[k] < min) {
                        min = arr[k];
                    }
                }
                if (max - min <= num) {
                    count++;
                }
            }

        }
        return count;
    }
}
