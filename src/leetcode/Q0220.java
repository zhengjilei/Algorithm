package leetcode;

import java.util.TreeSet;

public class Q0220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        // abs(a-b) <= valueDiff
        // -valueDiff =< a-b <= valueDiff
        // b-valueDiff <= a <= valueDiff+b

        // 找到一个数字和 nums[i] 绝对值之差 <= valueDiff
        // 转换成
        //  - 找比 num[i] 大于等于且最小的值，如果 <= nums[i] + valueDiff ，找到
        //  - 找比 nums[i] 小于等于且最大的值，如果 >= nums[i] - valueDiff， 找到

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
