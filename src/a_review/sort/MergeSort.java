package a_review.sort;

import org.junit.Test;
import sort.SortJudge;

import java.util.Random;

/**
 * created by Ethan-Walker on 2019/2/20
 */
public class MergeSort {
    int[] help;

    public void mergeSort(int[] nums) {
        help = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
    }

    public void mergeSort(int[] nums, int left, int right) {
        if (left < right) {
            int mid = (left + right) >> 1;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);
            merge(nums, left, mid, mid + 1, right);
        }
    }


    private void merge(int[] nums, int left, int leftEnd, int right, int rightEnd) {

        int saveLeft = left, saveRight = rightEnd;
        int helpIndex = left;
        while (left <= leftEnd && right <= rightEnd) {
            if (nums[left] < nums[right]) {
                help[helpIndex++] = nums[left++];
            } else {
                help[helpIndex++] = nums[right++];
            }
        }
        while (left <= leftEnd) help[helpIndex++] = nums[left++];
        while (right <= rightEnd) help[helpIndex++] = nums[right++];

        for (int i = saveLeft; i <= saveRight; i++) {
            nums[i] = help[i];
        }
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
            mergeSort(a);
            boolean judge = SortJudge.judge(a);
            if (!judge) {
                System.out.println(judge);
            }
        }
    }

}
