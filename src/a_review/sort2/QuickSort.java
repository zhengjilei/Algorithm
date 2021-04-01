package a_review.sort2;

import org.junit.Test;
import sort.SortJudge;

import java.util.Random;

/**
 * created by Ethan-Walker on 2019/3/11
 */
public class QuickSort {

    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(nums, left, right);
            quickSort(nums, left, pivotIndex - 1);
            quickSort(nums, pivotIndex + 1, right);
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
