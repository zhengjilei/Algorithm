package a_review.sort;

import org.junit.Test;
import sort.SortJudge;

import java.util.ArrayDeque;
import java.util.Random;

/**
 * created by Ethan-Walker on 2019/3/21
 */
public class NonRecurQuickSort {

    public void quickSort(int[] nums) {

        int l = 0, r = nums.length - 1;
        int pivotIndex;

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        stack.push(r);
        stack.push(l);
        while (!stack.isEmpty()) {
            l = stack.pop();
            r = stack.pop();
            if (l < r) {
                pivotIndex = partition(nums, l, r);
                if (pivotIndex - 1 > l) {
                    stack.push(pivotIndex - 1);
                    stack.push(l);
                }
                if (pivotIndex + 1 < r) {
                    stack.push(r);
                    stack.push(pivotIndex + 1);
                }
            }

        }
    }


    public int partition(int[] nums, int left, int right) {
        int pivotIndex = (int) (Math.random() * (right - left + 1)) + left;
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[i] <= pivot) i++;
            while (i < j && nums[j] >= pivot) j--;
            if (i < j) {
                swap(nums, i, j);
            }
        }
        swap(nums, i, right);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    @Test
    public void test() {

        for (int k = 0; k < 1000; k++) {
            Random random = new Random();
            int length = random.nextInt(20000);
            int[] a = new int[length];

            for (int i = 0; i < length; i++) {
                a[i] = random.nextInt(20000);
            }
//            bubbleSort(a);
//            selectSort(a);
            quickSort(a);
//            binaryInsertSort(a);
            boolean judge = SortJudge.judge(a);
            if (!judge) {
                System.out.println(judge);
            }
        }
    }
}
