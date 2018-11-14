package leetcode.a015ThreeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 2018/6/4.
 */
public class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{0, -2, -2, 2, 2};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(Arrays.toString(lists.toArray()));
    }

    /**
     * 思路：将 3Sum 转化成 2Sum 问题
     * 关键点: 不能有重复的结果 -> 排序巧妙解决重复问题
     * 复杂度 O(n^2)
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> resultsList = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 防止出现连续的两个相同的数，导致结果重复
            // -2 -2 -1 0 0 2 3
            // -2(index=0) -1 3
            // -2(index=1) -1 3 结果重复

            int target = 0 - nums[i];
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int temp = nums[start] + nums[end];
                if (temp == target) {
                    resultsList.add(Arrays.asList(nums[i], nums[start], nums[end]));

                    // 向内紧缩，继续判断是否有满足两数之和 = target 的结果
                    start++;
                    end--;
                    // 如果与之前的相等，再向内紧缩，避免结果重复
                    //如： -3 0 0 1 2 3 3
                    //      0 1 2 3 4 5 6
                    // 第一次得到的结果是 -3 0(index=0) 3(index=6)
                    // 如果紧缩后不判断是否相等，导致得到重复结果: -3 0(index=1) 3(index=5)
                    while (start < end && nums[start - 1] == nums[start]) start++;
                    while (end > start && nums[end + 1] == nums[end]) end--;

                } else if (temp < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return resultsList;
    }

    /**
     * O(nlogn)
     * 一共多少组三个元素之和等于target
     * 考虑重复元素
     */
    public static int threeSumCount(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int index = Arrays.binarySearch(nums, target - nums[i] - nums[j]);
                if (index > j) {
                    cnt++;
                    int left = index - 1, right = index + 1;
                    while (left > j && nums[left] == nums[index]) {
                        left--;
                        cnt++;
                    }
                    while (right < n && nums[right] == nums[index]) {
                        right++;
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}
