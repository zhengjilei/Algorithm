package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/10
 */
public class Q0009 {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;

        long y = 0;

        int t = x, s;
        while (t != 0) {
            s = t % 10;
            t /= 10;
            y = y * 10 + s;
        }

        return x == y;
    }
}
