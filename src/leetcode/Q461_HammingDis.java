package leetcode;

/**
 * created by Ethan-Walker on 2019/3/20
 */
public class Q461_HammingDis {
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int count = 0;
        while (z != 0) {
            count++;
            z &= (z - 1);
        }
        return count;
    }

}
