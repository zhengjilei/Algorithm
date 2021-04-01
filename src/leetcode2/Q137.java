package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/1
 */
public class Q137 {

    public int singleNumber(int[] nums) {


        int result = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                sum += (nums[j] >> i & 1);
            }
            if (sum % 3 == 1) {
                result |= (1 << i);
            }
        }
        return result;

    }
}
