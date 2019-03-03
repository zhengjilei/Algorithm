package leetcode2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class Q015 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int target, sum;

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            target = 0 - nums[i];

            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                sum = nums[start] + nums[end];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[start], nums[end]));

                    start++;
                    end--;

                    while (start < end && nums[start] == nums[start - 1]) start++;
                    while (start < end && nums[end] == nums[end + 1]) end--;
                } else if (sum < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return res;
    }
}
