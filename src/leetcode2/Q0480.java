package leetcode2;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class Q0480 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new double[0];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        double[] res = new double[nums.length - k + 1];
        int resIndex = 0;


        for (int i = 0; i < nums.length; i++) {
            if (maxHeap.size() == minHeap.size()) {
                // 插入一个值到 minHeap 中
                if (maxHeap.isEmpty() || nums[i] >= maxHeap.peek()) { // 直接插入 minHeap
                    minHeap.add(nums[i]);
                } else {
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(nums[i]);
                }
            } else {
                // 插入一个值到 maxHeap 中
                if (nums[i] > minHeap.peek()) {
                    maxHeap.add(minHeap.poll());
                    minHeap.add(nums[i]);
                } else {
                    maxHeap.add(nums[i]);
                }
            }


            if (i >= k - 1) {
                int removeIndex = i - k + 1;
                // 删除之后维持最大堆、最小堆数量的平衡

                if ((k & 1) == 0) {
                    // 偶数，取中间两位数
                    res[resIndex++] = ((long) maxHeap.peek() + minHeap.peek()) / 2.0;

                    // 删除掉窗口中最左边的位置删除
                    // 由于窗口中是偶数，优先删除最大堆中的
                    if (!maxHeap.remove(nums[removeIndex])) {
                        // 不在最大堆中
                        minHeap.remove(nums[removeIndex]);
                        minHeap.add(maxHeap.poll());
                    }
                } else {

                    res[resIndex++] = minHeap.peek();

                    // 由于窗口中是奇数,优先删除最小堆中的
                    if (!minHeap.remove(nums[removeIndex])) {
                        maxHeap.remove(nums[removeIndex]);
                        maxHeap.add(minHeap.poll());
                    }
                }
            }

        }
        return res;
    }
}
