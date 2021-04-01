package leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2019/3/21
 */
public class Q080_DeleteDupInArray {

    public int removeDuplicates(int[] nums) {
        int curIndex = 0;
        int nextIndex;
        int resultIndex = 0;

        while (curIndex < nums.length) {
            nextIndex = curIndex + 1;
            while (nextIndex < nums.length && nums[curIndex] == nums[nextIndex]) nextIndex++;

            nums[resultIndex++] = nums[curIndex];
            if (nextIndex - curIndex > 1) { // 重复数 >=2 个
                nums[resultIndex++] = nums[curIndex];
            }
            curIndex = nextIndex;
        }
        return resultIndex;
    }

    /**
     * 重复数全部删除
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        int resultIndex = 0;
        int curIndex = 0;
        int nextIndex;
        while (curIndex < nums.length) {
            nextIndex = curIndex + 1;
            while (nextIndex < nums.length && nums[curIndex] == nums[nextIndex]) nextIndex++;

            if (nextIndex - curIndex == 1) {
                // curIndex 位置的数不是重复数
                nums[resultIndex++] = nums[curIndex];
            }
            curIndex = nextIndex;
        }
        return resultIndex;
    }


    @Test
    public void test() {
        int[] a = new int[]{1, 1, 2, 3, 4, 4, 5};
        int count = removeDuplicates2(a);
        System.out.println(count + ":" + Arrays.toString(a));

        int[] b = new int[]{1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 4};
        int count2 = removeDuplicates2(b);
        System.out.println(count2 + ":" + Arrays.toString(b));


    }

}
