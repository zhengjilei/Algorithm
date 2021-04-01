package leetcode2;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/16
 */
public class Q448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) res.add(i + 1);
        }

        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
