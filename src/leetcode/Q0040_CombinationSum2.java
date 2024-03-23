package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/20
 */
public class Q0040_CombinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        HashSet<List<Integer>> res = new HashSet<>();
        Arrays.sort(candidates);
        int[] seq = new int[candidates.length];
        find(res, candidates, 0, target, seq, 0);
        return new ArrayList<>(res);
    }

    public void find(HashSet<List<Integer>> res, int[] nums, int index, int target, int[] selected, int seIndex) {
        if (target == 0) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < seIndex; i++) list.add(selected[i]);
            res.add(list);
            return;
        }
        if (index == nums.length) return;
        if (target < nums[index]) return;

        for (int i = index; i < nums.length && nums[i] <= target; i++) {
            selected[seIndex] = nums[i];// 选 nums[i] 作为 seIndex 位置的数
            find(res, nums, i + 1, target - nums[i], selected, seIndex + 1);
        }
    }


    @Test
    public void test() {
        int[] a = new int[]{10, 1, 2, 7, 6, 1, 5};
        int targetA = 8;
        System.out.println(combinationSum2(a, targetA));

        int[] b = new int[]{2, 5, 2, 1, 2};
        int targetB = 5;
        System.out.println(combinationSum2(b, targetB));


    }


}
