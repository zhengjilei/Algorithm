package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class Q344 {
    public void reverseString(char[] s) {
        if (s == null || s.length <= 1) return;
        int start = 0, end = s.length - 1;
        while (start < end) {
            swap(s, start, end);
            start++;
            end--;
        }
    }

    private void swap(char[] nums, int i, int j) {
        char t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
