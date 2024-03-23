package leetcode;

import java.util.HashMap;

/**
 * created by Ethan-Walker on 2019/2/18
 */
public class Q0219_DuplicateNumII {
    /**
     * 时间复杂度:O(n^2)
     * 空间复杂度:O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        if (nums == null || nums.length <= 1 || k <= 0) return false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    if (j - i <= k) return true;
                }
            }
        }
        return false;
    }

    /**
     * 时间复杂度:O(n)
     * 空间复杂度:O(n)
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k <= 0) return false;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int index = map.get(nums[i]);
                if (i - index <= k) return true;
            }
            // 即便包括也要覆盖原先的索引，保证新加入的索引位置较大
            map.put(nums[i], i);
        }
        return false;
    }
}
