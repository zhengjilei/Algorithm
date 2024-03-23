package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 不包含重复元素的全排列
 * created by Ethan-Walker on 2019/2/3
 */
public class Q0046_Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        permute(nums, 0, res);
        return res;
    }

    /**
     * 求 nums[index] ~结束 的全排列
     * 依次将 [index , ... 位置上的数交换到 index 位置上
     *
     * @param nums
     * @param index
     */
    public void permute(int[] nums, int index, List<List<Integer>> res) {
        if (index == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int v : nums) {
                list.add(v);
            }
            res.add(list);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            permute(nums, index + 1, res);
            swap(nums, index, i);
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
