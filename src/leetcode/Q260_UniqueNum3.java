package leetcode;

/**
 * created by Ethan-Walker on 2019/1/13
 */
public class Q260_UniqueNum3 {


    public int[] singleNumber(int[] nums) {
        int[] result = new int[2];
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum ^= nums[i];
        }

        // sum = a^b (a和b是最终要求的两个数,a!=b ,故 sum!=0)
        int t = sum ^ (sum - 1) & sum; // t 等于 sum 仅保存最后一个 1（设为第x位），其他位都为0的值    0000100000

        //将原数组分为两部分，一部分在第 x 位为1，一部分在第 x 位为0; 两部分分别异或就分别得到 a、b值
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & t) == 0) {
                result[0] ^= nums[i];
            } else {
                result[1] ^= nums[i];
            }
        }
        return result;

    }
}
