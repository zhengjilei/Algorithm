package leetcode;

import java.util.Arrays;
import java.util.HashSet;

/**
 * created by Ethan-Walker on 2019/2/18
 */
public class Q217_DuplicateNum {
    /**
     * 最简单思路
     * 时间复杂度:O(n^2)
     * 空间复杂度:O(1)
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) return false;

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * hashset
     * 时间复杂度:O(n)
     * 空间复杂度:O(n)
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate2(int[] nums) {
        if (nums == null || nums.length <= 1) return false;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    /**
     * 快排，然后遍历
     * 时间复杂度:O(nlogn)
     * 空间复杂度:O(n)
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate3(int[] nums) {
        if (nums == null || nums.length <= 1) return false;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }

}


