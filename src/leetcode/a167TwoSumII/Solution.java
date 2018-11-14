package leetcode.a167TwoSumII;

import java.util.HashMap;


/**
 * Created by lenovo on 2018/6/1.
 */
public class Solution {

    /**
     * 方法一： 按照 Two Sum 之前的方法做，但是没有运用到 升序排列的特点
     * 时间复杂度 O(n)
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] results = new int[2];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int cur = numbers[i];
            if (hashMap.containsKey(cur)) {
                results[0] = hashMap.get(cur);
                results[1] = i;
                return results;
            } else {
                hashMap.put(target - cur, i);
            }
        }
        return results;
    }

    /**
     * 双指针法
     * 最坏情况 O(n)  最佳 O(log n)
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target) {
        int[] results = new int[2];
        int left = 0, right = numbers.length - 1, mid;
        while (left < right) {

            int t = numbers[left] + numbers[right];
            mid = (left + right) / 2;

            if (t < target) {
                // 判断是 跳一半还是只走一步
                if (numbers[mid] + numbers[right] > target) {
                    left++;
                } else {
                    left = mid;
                }
            } else if (t == target) {
                return new int[]{left + 1, right + 1};
            } else {
                // 判断是 跳一半还是只走一步
                if (numbers[left] + numbers[mid] < target) {
                    right--;
                } else {
                    right = mid;
                }
            }
        }
        return results;
    }
}
