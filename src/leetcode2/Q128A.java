package leetcode2;

import java.util.HashMap;

/**
 * created by Ethan-Walker on 2019/3/19
 */
public class Q128A {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length <= 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>(); // key: 已经存在的数值, value: 该数值所在连续序列的长度
        int max = 1;
        int len;
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {        // 已经存在 nums[i] 了，不需要重复放置
                map.put(nums[i], 1);
                if (map.containsKey(nums[i] - 1)) {
                    len = merge(nums[i] - 1, nums[i], map);
                    if (len > max) {
                        max = len;
                    }
                }
                if (map.containsKey(nums[i] + 1)) {
                    len = merge(nums[i], nums[i] + 1, map);
                    if (len > max) {
                        max = len;
                    }
                }
            }

        }
        return max;
    }

    public int merge(int small, int big, HashMap<Integer, Integer> map) {
        int left = small - map.get(small) + 1;
        int right = big + map.get(big) - 1;

        int newLen = right - left + 1;
        map.put(left, newLen);
        map.put(right, newLen);
        return newLen;
    }

}
