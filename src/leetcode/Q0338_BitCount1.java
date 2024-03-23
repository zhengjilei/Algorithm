package leetcode;

/**
 * created by Ethan-Walker on 2019/3/13
 */
public class Q0338_BitCount1 {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        res[0] = 0;
        for (int i = 1; i <= num; i++) {
            res[i] = res[i & (i - 1)] + 1;
        }
        return res;
    }
}
