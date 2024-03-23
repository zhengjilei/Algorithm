package leetcode2;

import java.util.HashMap;

/**
 * created by Ethan-Walker on 2019/3/21
 */
public class Q0001 {
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>(); // 元素 和 元素下标
        int match;
        for (int i = 0; i < nums.length; i++) {
            match = target - nums[i];
            if (map.containsKey(match)) {
                return new int[]{map.get(match), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};

    }
}
