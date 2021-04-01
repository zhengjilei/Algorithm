package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/20
 */
public class Q477_HammingDisArray {
    public int totalHammingDistance(int[] nums) {
        int count = 0;
        int mask = 1;

        int zeroCount, oneCount;
        for (int i = 0; i < 32; i++) {
            zeroCount = 0;
            oneCount = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & mask) == 0) {
                    zeroCount++;
                } else {
                    oneCount++;
                }
            }
            count += (zeroCount * oneCount);
            mask <<= 1;

        }
        return count;

    }


    @Test
    public void test() {
        int[] a = new int[]{4, 14, 2};
        System.out.println(totalHammingDistance(a));
    }


}
