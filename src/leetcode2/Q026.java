package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/21
 */
public class Q026 {
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        if (nums.length <= 1) return nums.length;

        int resultIndex = 0;
        int curIndex = 0;

        int nextIndex;
        while (curIndex < nums.length) {
            nextIndex = curIndex + 1;
            while (nextIndex < nums.length && nums[nextIndex] == nums[curIndex])
                nextIndex++;
            // nextIndex 指向第一个和 nums[curIndex] 不等的位置
            nums[resultIndex++] = nums[curIndex]; // 无论 nums[curIndex] 是否是重复项，保存
            curIndex = nextIndex;
        }
        return resultIndex;
    }
}
