package leetcode2;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/16
 */
public class Q0442 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int m;
        for (int i = 0; i < nums.length; i++) {
            m = Math.abs(nums[i]);
            // 数 m 应该在的位置m-1:  nums[m-1]
            if (nums[m - 1] > 0) {
                nums[m - 1] *= -1;
            } else {
                res.add(m);
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
