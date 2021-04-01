package sort.mergesort;

import org.junit.Test;
import sort.SortJudge;

import java.util.Random;

/**
 * created by Ethan-Walker on 2019/3/16
 */
public class NonRecurMergeSort {
    int[] help;
    public void mergeSort(int[] nums) {
        help = new int[nums.length];

        int len = 1; // 归并段长度
        int i;
        while (len < nums.length) { // 归并段 >= nums.length ，说明只有一段了，不需要再归并
            for (i = 0; i + len * 2 <= nums.length; i += 2 * len) { // i+len*2<= nums.length 保证对最后两个长度等于 len的段进行归并
                merge(nums, i, i + len - 1, i + 2 * len - 1);
            }
            if (i + len < nums.length) {//未合并的元素个数大于 len ，介于 (len, 2*len) 之间 ,说明剩余的元素有两段，一段长度是len ，另一段小于 len
                //未合并的元素 等于 len 不用管，只有一段，不需要合并
                merge(nums, i, i + len - 1, nums.length - 1); // 合并末尾两段
            }
            len <<= 1;
        }
    }


    public void merge(int[] nums, int lStart, int lEnd, int rEnd) {
        int rStart = lEnd + 1;
        int saveStart = lStart;
        int hIndex = lStart;
        while (lStart <= lEnd && rStart <= rEnd) {
            if (nums[lStart] <= nums[rStart]) {
                help[hIndex++] = nums[lStart++];
            } else {
                help[hIndex++] = nums[rStart++];
            }
        }
        while (lStart <= lEnd) help[hIndex++] = nums[lStart++];
        while (rStart <= rEnd) help[hIndex++] = nums[rStart++];

        while (saveStart <= rEnd) {
            nums[saveStart] = help[saveStart];
            saveStart++;
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
