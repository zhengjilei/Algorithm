package leetcode;

/**
 * created by Ethan-Walker on 2019/3/14
 */
public class Q0026_DeleteDupInArray {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int resIndex = 1;
        int prev = nums[0];
        int cur = 1;
        while (cur < nums.length) {
            while (cur < nums.length && nums[cur] == prev) cur++;

            if (cur < nums.length) {
                // cur 指向第一个不等于 prev 的数
                nums[resIndex++] = nums[cur];
                prev = nums[cur];
            }
        }
        return resIndex;
    }
}
