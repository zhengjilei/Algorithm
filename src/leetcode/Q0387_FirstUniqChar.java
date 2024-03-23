package leetcode;

/**
 * created by Ethan-Walker on 2019/2/27
 */
public class Q0387_FirstUniqChar {

    public int firstUniqChar(String s) {

        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }
}
