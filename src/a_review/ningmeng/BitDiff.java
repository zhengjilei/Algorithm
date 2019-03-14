package a_review.ningmeng;

/**
 * created by Ethan-Walker on 2019/3/13
 */
public class BitDiff {

    /**
     * 时间复杂度: O(n^2)
     *
     * @param nums
     * @return
     */
    public int bitDiff(int[] nums) {
        boolean[] bitMap = new boolean[32];
        int x;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; i < nums.length; j++) {
                int t = nums[i] ^ nums[j];

                while (t != 0) {
                    x = t ^ (t - 1);  // 得到 t 的末位1
                    bitMap[(int) (Math.log(x) / Math.log(2))] = true; // 对应位设置为 true
                    t = t & (t - 1);  // 消除末位 1
                }
            }
        }

        int count = 0;
        for (int i = 0; i < 32; i++) {
            if (bitMap[i]) count++;
        }
        return count;
    }

    /**
     * 时间复杂度 :O(32n)
     *
     * @param nums
     * @returnDup
     */
    public int bitDiff2(int[] nums) {
        int count = 0;
        int mask = 1;

        for (int i = 0; i < 32; i++) {
            // 判断倒数第 i 位是否存在异位情况
            for (int j = 0; j < nums.length - 1; j++) {
                if ((nums[j] & mask) != (nums[j + 1] & mask)) {
                    // 第 i 位出现不同情况
                    count++;
                    break;
                }
            }
            mask <<= 1;
        }
        return count;
    }


    public int getPos(int t) {
        int pos = 0;
        t >>= 1;
        while (t != 0) {
            t >>= 1;
            pos++;
        }
        return pos;
    }

}
