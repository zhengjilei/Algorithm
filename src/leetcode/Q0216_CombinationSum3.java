package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/20
 */
public class Q0216_CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<List<Integer>> res = new ArrayList<>();
        int[] seq = new int[nums.length];
        find(res, nums, 0, n, k, seq, 0);

        return res;

    }

    public void find(List<List<Integer>> res, int[] nums, int index, int target, int remainK, int[] seq, int seqIndex) {
        if (target == 0) {
            if (remainK == 0) {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < seqIndex; i++) list.add(seq[i]);
                res.add(list);
            }
            return;
        }
        if (index == nums.length || remainK == 0) return;

        if (nums[index] > target) return;

        for (int i = index; i < nums.length && nums[i] <= target; i++) {
            seq[seqIndex] = nums[i];// seq[seqIndex] 尝试放
            find(res, nums, i + 1, target - nums[i], remainK - 1, seq, seqIndex + 1);
        }
    }

    @Test
    public void test() {
        System.out.println(combinationSum3(3, 7));
        System.out.println(combinationSum3(3, 9));
    }

}
