package sort.quicksort;

import org.junit.Test;
import sort.SortJudge;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 * created by Ethan-Walker on 2019/3/17
 */
public class NonRecurQuickSort {

    public void quickSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(nums.length - 1);
        stack.push(0);

        while (!stack.isEmpty()) {
            int i = stack.pop();
            int j = stack.pop();

            if (i < j) {
                int pivotIndex = partition(nums, i, j);
                if (pivotIndex - 1 > i) {      // >= 两个数 才压栈，进行下一次的划分
                    stack.push(pivotIndex - 1);
                    stack.push(i);
                }

                if (pivotIndex + 1 < j) {
                    stack.push(j);
                    stack.push(pivotIndex + 1);
                }
            }
        }
    }


    public int partition(int[] nums, int left, int right) {
        int pivotIndex = (int) (Math.random() * (right - left + 1)) + left;
        int pivot = nums[pivotIndex];
        swap(nums, right, pivotIndex);
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

        for (int k = 0; k < 10000; k++) {
            Random random = new Random();
            int length = random.nextInt(2000);
            int[] a = new int[length];

            for (int i = 0; i < length; i++) {
                a[i] = random.nextInt(2000);
            }
            quickSort(a);
            boolean judge = SortJudge.judge(a);
            if (!judge) {
                System.out.println(judge);
            }
        }
    }


}
