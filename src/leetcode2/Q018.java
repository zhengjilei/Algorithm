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
        if (nums == null || nums.length <= 3) return res;
        Arrays.sort(nums);
        int target3, target2;
        int left, right;
        int sum;
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            target3 = target - nums[i];
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                target2 = target3 - nums[j];
                left = j + 1;
                right = nums.length - 1;

                while (left < right) {
                    sum = nums[left] + nums[right];
                    if (sum == target2) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    } else if (sum < target2) {
                        left++;
                    } else {
                        right--;
                    }
                }

            }

        }
        return res;
    }
}
