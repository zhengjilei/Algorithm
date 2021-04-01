package leetcode2;

import java.util.HashMap;
import java.util.Map;

/**
 * created by Ethan-Walker on 2019/3/9
 */
public class Q128 {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        int maxLen = 1;
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
                if (map.containsKey(nums[i] - 1)) {
                    int len = merge(map, nums[i] - 1, nums[i]);
                    if (len > maxLen) {
                        maxLen = len;
                    }
                }
                if (map.containsKey(nums[i] + 1)) {
                    int len = merge(map, nums[i], nums[i] + 1);
                    if (len > maxLen) {
                        maxLen = len;
                    }
                }
            }
        }
        return maxLen;
    }

    public int merge(Map<Integer, Integer> map, int small, int big) {
        small = small - map.get(small) + 1;
        big = big + map.get(big) - 1;

        // small big 指向两端的值
        int newLen = big - small + 1;
        map.put(small, newLen);
        map.put(big, newLen);
        return newLen;
    }



}
