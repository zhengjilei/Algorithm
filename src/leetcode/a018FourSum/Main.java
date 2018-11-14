package leetcode.a018FourSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 2018/6/12.
 */
public class Main {
    public static void main(String[] args) {
        fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
    }

    /**
     * 类似 3Sum 转化成 2Sum
     * 将4Sum -> 3Sum -> 2Sum, 关键点 : 去重
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;  // 去重

            int threeTarget = target - nums[i];
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) continue;  // 去重

                int twoTarget = threeTarget - nums[j];
                int start = j + 1;
                int end = nums.length - 1;
                while (start < end) {
                    int temp = nums[start] + nums[end];
                    if (temp == twoTarget) {
                        resultList.add(Arrays.asList(nums[i],nums[j],nums[start],nums[end]));
                        start++;
                        end--;

                        // 去重
                        while (start < end && nums[start - 1] == nums[start]) start++;
                        while (end > start && nums[end + 1] == nums[end]) end--;
                    } else if (temp < twoTarget) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }

        }
        return resultList;
    }
}
