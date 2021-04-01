package a_review.sort2;

import org.junit.Test;
import sort.SortJudge;

import java.util.Random;

/**
 * created by Ethan-Walker on 2019/3/11
 */
public class MergeSort {

    int[] help;

    public void mergeSort(int[] nums) {
        help = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
    }

    public void mergeSort(int[] nums, int left, int right) {
        if (left < right) {
            int mid = left + right >> 1;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);
            merge(nums, left, mid, right);
        }
    }

    public void merge(int[] nums, int leftStart, int leftEnd, int rightEnd) {
        int lStart = leftStart, rStart = leftEnd + 1;

        int hIndex = leftStart;
        while (lStart <= leftEnd && rStart <= rightEnd) {
            if (nums[lStart] <= nums[rStart]) {
                help[hIndex++] = nums[lStart++];
            } else {
                help[hIndex++] = nums[rStart++];
            }
        }
        while (lStart <= leftEnd) help[hIndex++] = nums[lStart++];
        while (rStart <= rightEnd) help[hIndex++] = nums[rStart++];

        while (leftStart <= rightEnd) {
            nums[leftStart] = help[leftStart];
            leftStart++;
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
//            bubbleSort(a);
//            selectSort(a);
//            insertSort(a);
            mergeSort(a);
//            binaryInsertSort(a);
            boolean judge = SortJudge.judge(a);
            if (!judge) {
                System.out.println(judge);
            }
        }
    }
}
