package leetcode2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class Q018 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int target3, target2, sum, start, end;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            target3 = target - nums[i];
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                target2 = target3 - nums[j];

                start = j + 1;
                end = nums.length - 1;
                while (start < end) {
                    sum = nums[start] + nums[end];
                    if (sum == target2) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));
                        start++;
                        end--;
                        while (start < end && nums[start] == nums[start - 1]) start++;
                        while (start < end && nums[end] == nums[end + 1]) end--;
                    } else if (sum < target2) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }
        return res;
    }
}
