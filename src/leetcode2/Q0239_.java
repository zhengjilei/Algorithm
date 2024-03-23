package leetcode2;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Q0239_ {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];

        int[] res = new int[nums.length - k + 1];
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int resIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
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


    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[nums.length - k + 1];
        int resIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);

            if (i >= k - 1) {
                int head = deque.peekFirst();
                if (head <= i - k) {
                    deque.pollFirst();
                    head = deque.peekFirst();
                }
                res[resIndex++] = nums[head];
            }
        }
        return res;
    }

}
