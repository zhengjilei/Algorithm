package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/1
 */
public class Q260 {
    public int[] singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }
        int t = res ^ (res - 1) & res;
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if ((t & nums[i]) == 0) {
                result[0] ^= nums[i];
            } else {
                result[1] ^= nums[i];
            }
        }
        return result;
    }
}
