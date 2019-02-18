package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/18
 */
public class Q287_DuplicateNum {
    public int findDuplicate(int[] nums) {

        int start = 1, end = nums.length - 1, mid;
        //注意 start 从1开始，不表示数组的下标位置，而是表示目标重复数的范围从[1,end]
        int count;
        while (start <= end) {
            mid = ((end - start) >> 1) + start;
            count = getCount(nums, start, mid); // nums中 [start,mid] 之间的数
            if (end == start) {  // 目标范围缩小到只有一个数
                if (count > 1) {
                    return start;
                } else {
                    break;
                }
            }
            if (count > (mid - start + 1)) { // 重复数在 [start,mid] 之间
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 返回nums数组中 数值在 [start,end] 之间的数个数
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public int getCount(int[] nums, int start, int end) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= start && nums[i] <= end ) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void test() {
        int[] a = new int[]{1, 3, 4, 2, 2};
        System.out.println(findDuplicate(a));
    }
}
