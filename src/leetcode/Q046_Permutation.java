package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 不包含重复元素的全排列
 * created by Ethan-Walker on 2019/2/3
 */
public class Q046_Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        permute(nums, 0, nums.length - 1, res);
        return res;
    }

    /**
     * 求 nums[left] ~nums[right] 的全排列
     * 依次将 left~right 位置上的数交换到 left 位置上
     *
     * @param nums
     * @param left
     * @param right
     */
    public void permute(int[] nums, int left, int right, List<List<Integer>> res) {
        if (left == right) {
            List<Integer> list = new ArrayList<>();
            for (int v : nums) {
                list.add(v);
            }
            res.add(list);
            return;
        }

        for (int i = left; i <= right; i++) {
            swap(nums, left, i);
            permute(nums, left + 1, right, res);
            swap(nums, left, i);
        }
    }

    public void swap(int[] nums, int left, int right) {
        int v = nums[left];
        nums[left] = nums[right];
        nums[right] = v;
    }

    @Test
    public void test() {
        System.out.println(permute(new int[]{1, 2, 3}));
    }
}
