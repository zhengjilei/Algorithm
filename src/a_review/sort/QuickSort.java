package a_review.sort;

import org.junit.Test;
import sort.SortJudge;

import java.util.Random;

/**
 * created by Ethan-Walker on 2019/2/20
 */
public class QuickSort {

    public void quickSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(nums, left, right);
            quickSort(nums, left, pivotIndex - 1);
            quickSort(nums, pivotIndex + 1, right);
        }
    }

    private int partition(int[] nums, int left, int right) {
//        int pivotIndex = (int) (Math.random() * (right - left + 1)) + left;
        int pivotIndex = median3(nums, left, right);
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, right);// pivot 交换到 right 位置
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[i] <= pivot) i++;
            while (i < j && nums[j] >= pivot) j--;
            if (i < j) {
                swap(nums, i, j);
            }
        }
        // i 指向第一个比pivot 大的数, 最终 pivot 应在的位置
        swap(nums, i, right);
        return i;
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    // 返回pivotIndex
    public int median3(int[] nums, int left, int right) {
        if (right - left <= 1) return left;
        int mid = (left + right) >> 1;
        int minIndex = left;
        if (nums[mid] < nums[minIndex]) {
            minIndex = mid;
        }
        if (nums[right] < nums[minIndex]) {
            minIndex = right;
        }
        if (minIndex != left) swap(nums, minIndex, left);
        if (nums[right] < nums[mid]) swap(nums, mid, right);
        return mid;

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
