package leetcode;

import java.util.PriorityQueue;

public class Q0703_kth_largest_elem {

    PriorityQueue<Integer> minHeap;

    int size;

    public Q0703_kth_largest_elem(int k, int[] nums) {
        this.minHeap = new PriorityQueue<>();
        this.size = k;

        for (int i = 0; i < nums.length; i++) {
            if (i >= k) {
                if (nums[i] > this.minHeap.peek()) {
                    this.minHeap.poll();
                    this.minHeap.offer(nums[i]);
                }
            } else {
                this.minHeap.offer(nums[i]);
            }
        }
    }

    public int add(int val) {
        if (this.minHeap.size() < this.size) {
            this.minHeap.offer(val);
            return this.minHeap.peek().intValue();
        }

        if (val <= this.minHeap.peek()) {
            return this.minHeap.peek().intValue();
        }

        this.minHeap.poll();
        this.minHeap.offer(val);
        return this.minHeap.peek().intValue();
    }
}
