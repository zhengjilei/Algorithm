package sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 长度为 n 的数组乱序存放着0至n-1. 没有重复数字
 * 交换排序，要求时间复杂度: O(n) 空间复杂度:O(1)
 * <p>
 * created by Ethan-Walker on 2019/3/16
 */
public class SwapSort {

    public void sort(int[] nums) {
        // 最多交换 n 次
        for (int i = 0, len = nums.length; i < len; i++) {
            while (nums[i] != i) { // 直到 i 位置上的值 等于 i , 肯定存在这个数，所以不必担心死循环，最多交换 n 次就得到了(其他 n-1 次将剩余 n-1 个数都交换到了正确的位置)
                swap(nums, i, nums[i]); // 将 nums[i] 这个值交换到其 应该在的位置 i上
            }
        }
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
                a[i] = i;
            }
            Shuffle.shuffle(a);
            sort(a);
            boolean judge = SortJudge.judge(a);
            if (!judge) {
                System.out.println(judge);
            }
        }
    }


    @Test
    public void test2() {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7};
        List<int[]> ints = Arrays.asList(a);
        int[] res = ints.get(0);
        System.out.println(Arrays.toString(res));
    }
}
