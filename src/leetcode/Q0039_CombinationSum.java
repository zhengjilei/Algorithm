package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/20
 */
public class Q0039_CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        HashSet<List<Integer>> res = new HashSet<>();
        Arrays.sort(candidates);
        int[] selected = new int[target]; // 为什么要设置长度为 target 呢？有可能由  target 个 1组成
        recur(res, candidates, 0, target, selected, 0);
        return new ArrayList<>(res);

    }

    public void recur(HashSet<List<Integer>> res, int[] nums, int index, int target, int[] selected, int seIndex) {
        if (target == 0) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < seIndex; i++) list.add(selected[i]);
            res.add(list);
        }
        if (index == nums.length || target < 0) {
            return;
        }
        // 不选择 nums[index]
        recur(res, nums, index + 1, target, selected, seIndex);

        // 选择 nums[index]
        // 最多可以选择 target/nums[index] 个
        for (int i = 1; i <= target / nums[index]; i++) {
            selected[seIndex + i - 1] = nums[index]; // 选择1个 2个 3个 ...
            recur(res, nums, index + 1, target - nums[index] * i, selected, seIndex + i);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates); // 排序之后可以去重，且可以避免不必要的递归
        find(res, list, candidates, target, 0);
        return res;
    }

    public void find(List<List<Integer>> listAll, List<Integer> tmp, int[] candidates, int target, int index) {
        if (target == 0) {
            listAll.add(tmp);
            return;
        }
        if (index == candidates.length) return;
        if (target < candidates[index]) return;

        for (int i = index; i < candidates.length && candidates[i] <= target; i++) { // candidates[i] <= target 避免不必要的递归
            // 当前位置 可以选择放 num... 之后任意一个元素，选择完之后，继续处理后面的数据
            ArrayList<Integer> list = new ArrayList<>(tmp);
            list.add(candidates[i]);
            find(listAll, list, candidates, target - candidates[i], i); // 放置nums[i] 后，递归仍然是 i
        }
    }

    @Test
    public void test() {
        int[] a = new int[]{2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum2(a, target));
        int[] b = new int[]{2, 3, 5};
        int target2 = 8;
        System.out.println(combinationSum2(b, target2));

    }


}
