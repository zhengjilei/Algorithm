package leetcode;

import java.util.TreeSet;

public class Q0220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        // abs(a-b) <= valueDiff
        // -valueDiff =< a-b <= valueDiff

        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i >= 1) {
                Integer higher = treeSet.ceiling(nums[i]);
                if (higher != null && higher <= nums[i] + valueDiff) {
                    return true;
                }

                Integer lower = treeSet.floor(nums[i]);
                if (lower != null && lower >= nums[i] - valueDiff) {
                    return true;
                }
            }

            treeSet.add(nums[i]);

            if (i >= indexDiff) {
                treeSet.remove(nums[i - indexDiff]);
            }
        }
        return false;
    }

}
