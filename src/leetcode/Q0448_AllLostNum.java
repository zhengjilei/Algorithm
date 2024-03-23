package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/16
 */
public class Q0448_AllLostNum {
    // 思路一: 排序 时间复杂度:O(nlogn) 空间复杂度:O(n)
    // 思路二: bitMap[]  空间复杂度:O(n)


    // 思路三: 和找到第一个消失的数字  Q0442 相似

    /**
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                res.add(i + 1);
            }
        }
        return res;

    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
