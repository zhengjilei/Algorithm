package leetcode;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Q055_JumpGame {


    public boolean canJump(int[] nums) {
        int jump = 0, cur = 0, next = 0;
        for (int i = 0; i < nums.length; i++) {
            if (cur < i) {
                if (next < i) {
                    return false;
                }
                jump++;
                cur = next;
            }
            next = Math.max(next, i + nums[i]);
            if (next >= nums.length - 1) return true;
        }

        return true;
    }
}
