package leetcode;

import org.junit.Test;

import java.util.*;

/**
 * created by Ethan-Walker on 2019/3/9
 */
public class Q491_AllLIS {

    public List<List<Integer>> findSubsequences(int[] nums) {
        int[] seq = new int[nums.length];
        Set<List<Integer>> res = new HashSet<>();
        all(res, seq, 0, nums, 0);
        return new ArrayList<>(res);
    }

    //List<List<Integer>> 出现 4 6 7  + 4 6 7 的情况，如何去重，用 HashSet<List<>>
    public void all(Set<List<Integer>> res, int[] seq, int seqIndex, int[] nums, int numsIndex) {
        if (numsIndex == nums.length) {
            if (seqIndex >= 2) {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < seqIndex; i++) {
                    list.add(seq[i]);
                }
                res.add(list);
            }
            return;
        }

        // 选择 numsIndex
        if (seqIndex == 0 || nums[numsIndex] >= seq[seqIndex - 1]) {
            seq[seqIndex] = nums[numsIndex];
            all(res, seq, seqIndex + 1, nums, numsIndex + 1);
        }
        // 不选择 numsIndex
        all(res, seq, seqIndex, nums, numsIndex + 1);
    }


    public List<List<Integer>> findSubsequences2(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        all2(res, list, nums, 0);
        return new ArrayList<>(res);
    }

    public void all2(Set<List<Integer>> res, List<Integer> list, int[] nums, int numsIndex) {
        if (list.size() >= 2) {
            List<Integer> l = new ArrayList<>(list);
            res.add(l);
        }
        if (numsIndex == nums.length)
            return;
        for (int i = numsIndex; i < nums.length; i++) {
            if (list.size() == 0 || nums[i] >= list.get(list.size() - 1)) {
                list.add(nums[i]);
                all2(res, list, nums, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    @Test
    public void test2() {
        Set<List<Integer>> set = new HashSet<>();
        set.add(Arrays.asList(1, 2, 3));
        set.add(Arrays.asList(1, 2));
        set.add(Arrays.asList(1, 2, 3)); // Set 自动过滤

        System.out.println(set);
    }
}
