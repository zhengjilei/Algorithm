package leetcode2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * created by Ethan-Walker on 2019/3/19
 */
public class Q0491 {

    public List<List<Integer>> findSubsequences(int[] nums) {
        HashSet<List<Integer>> set = new HashSet<>();
        List<Integer> seq = new ArrayList<>();
        recur(nums, 0, seq, set);
        return new ArrayList<>(set);
    }

    public void recur(int[] nums, int index, List<Integer> seq, Set<List<Integer>> set) {
        if (seq.size() >= 2) {
            List<Integer> list = new ArrayList<>(seq);
            set.add(list);
        }

        if (index == nums.length) return;
        for (int i = index; i < nums.length; i++) {
            if (seq.size() == 0 || nums[index] >= seq.get(seq.size() - 1)) {
                seq.add(nums[index]);
                recur(nums, index + 1, seq, set);
                seq.remove(seq.size() - 1);
            }
        }
    }

}
