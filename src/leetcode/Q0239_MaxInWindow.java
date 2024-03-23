package leetcode;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/1/8
 */
public class Q0239_MaxInWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k == 0 || k > nums.length) return new int[0];
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        int[] max = new int[nums.length - k + 1];
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) { // = 可取可不取
                deque.pollLast();
            }
            deque.offer(i);
            if (i >= k - 1) {    // 窗口中数量满足 k 个了
                // 判断队列中最大值元素是否已经过期（从滑动窗口中移开）
                if (i - deque.peek() >= k) {
                    deque.pollFirst();
                }

                max[cnt++] = nums[deque.peek()];
            }
        }
        return max;

    }
}
