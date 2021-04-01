package a_review.sort;

import org.junit.Test;
import sort.SortJudge;

import java.util.Random;

/**
 * created by Ethan-Walker on 2019/3/21
 */
public class NonRecurMergeSort {

    public void mergeSort(int[] nums) {
        help = new int[nums.length];
        int i;
        int len = 1;
        while (len < nums.length) {
            for (i = 0; i <= nums.length - 2 * len; i += 2 * len) {
                // [i,i+len-1] [i+len,i+2*len-1]
                merge(nums, i, i + len - 1, i + 2 * len - 1);
            }
            if (i + len < nums.length) {
                merge(nums, i, i + len - 1, nums.length - 1);
            }
            len <<= 1;
        }

    }

    int[] help;

    public void merge(int[] nums, int lStart, int lEnd, int rEnd) {
        int rStart = lEnd + 1;
        int helpIndex = lStart;
        int saveStart = lStart;
        while (lStart <= lEnd && rStart <= rEnd) {
            if (nums[lStart] <= nums[rStart]) {
                help[helpIndex++] = nums[lStart++];
            } else {
                help[helpIndex++] = nums[rStart++];
            }
        }
        while (lStart <= lEnd) {
            help[helpIndex++] = nums[lStart++];
        }
        while (rStart <= rEnd) {
            help[helpIndex++] = nums[rStart++];
        }

        while (saveStart <= rEnd) {
            nums[saveStart] = help[saveStart];
            saveStart++;
        }
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
            mergeSort(a);
//            binaryInsertSort(a);
            boolean judge = SortJudge.judge(a);
            if (!judge) {
                System.out.println(judge);
            }
        }
    }
}
