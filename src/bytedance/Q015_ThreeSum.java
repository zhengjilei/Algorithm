package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/2/2
 */
public class Q015_ThreeSum {
    //a + b + c = 0

    /**
     * 错误：会导致结果重复
     * 方法一: 三个位置分别相加
     * 时间复杂度: O(n^3)
     */


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int target = 0, sum = 0;
        for (int i = 0; i <= nums.length - 3; i++) {
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


    @Test
    public void test() {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    // 找出 nums 中的三个数之和与 target 最接近，返回这个和
    // 假定每组输入只存在唯一答案。
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minClosest = Integer.MAX_VALUE;
        int closestSum = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int start = i + 1;
            int end = nums.length - 1;

            while (start < end) {
                sum = nums[i] + nums[start] + nums[end];

                int sub = Math.abs(sum - target);
                if (sub < minClosest) {
                    minClosest = sub;
                    closestSum = sum;
                }
                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return closestSum;
    }


    @Test
    public void test2() {
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }
}
