package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/19
 */
public class Q0055 {

    public boolean canJump(int[] nums) {

        int jump = 0, reachPos = 0, nextPos = 0;

        for (int i = 0; i < nums.length; i++) {
            if (reachPos < i) {
                if (nextPos < i) {
                    return false;
                }
                jump++;
                reachPos = nextPos;
            }
            nextPos = Math.max(nextPos, i + nums[i]);
        }
        return true;
    }

    public int jump(int[] nums) {
        int jump = 0, reachPos = 0, nextPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (reachPos < i) {
                if (nextPos < i) {
                    // 到达不了数组的末尾
                    return -1;
                }
                jump++;
                reachPos = nextPos;
                if (nextPos >= nums.length - 1) {
                    break;
                }
            }
            nextPos = Math.max(nextPos, i + nums[i]);
        }
        return jump;
    }


}
