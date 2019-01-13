package leetcode;

/**
 * created by Ethan-Walker on 2019/1/13
 */
public class Q137_UniqueNum2 {


    /**
     * 方法一：统计各个数字在 32 位上出现的总次数
     * 如果次数%3==0 说明目标值该位上值为0
     * 次数 %3 == 1  说明目标值在该位上值为1
     * <p>
     * 时间复杂度: O(32n)
     * 空间复杂度: O(1)
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {

        int count = 0, result = 0;
        for (int i = 0; i < 32; i++) {
            // 计算 result 的第 i 位（从右往左数）
            count = 0;
            for (int j = 0; j < nums.length; j++) {
                count += ((nums[j] >> i) & 1);
            }
            if (count % 3 == 1) {
                result |= (1 << i); // 将第 i 位置为 1
            }
        }
        return result;

    }


    // 解法二: https://blog.csdn.net/jiangxiewei/article/details/82227451
    // TODO: 2019/1/13
}
