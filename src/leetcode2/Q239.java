package leetcode2;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class Q239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return new int[0];
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[nums.length - k + 1];
        int resIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) deque.pollLast();
            deque.offerLast(i);

            if (i >= k - 1) {
                if (deque.peekFirst() <= i - k) {
                    deque.pollFirst();
                }
                res[resIndex++] = nums[deque.peekFirst()];
            }
        }
        return res;
    }

    @Test
    public void test() {

        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{7, 2, 4}, 2)));

    }
}
