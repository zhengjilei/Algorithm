package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Q0295_FindMedianFromStream {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public Q0295_FindMedianFromStream() {
        this.minHeap = new PriorityQueue<>();
        this.maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    public void addNum(int num) {
        if (minHeap.size() == maxHeap.size()) {
            // add to min heap
            if (!maxHeap.isEmpty() && num < maxHeap.peek()) {
                minHeap.add(maxHeap.poll());
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
        } else {
            // add to max heap
            if (!minHeap.isEmpty() && num > minHeap.peek()) {
                maxHeap.add(minHeap.poll());
                minHeap.add(num);
            } else {
                maxHeap.add(num);
            }
        }
    }

    public double findMedian() {
        int length = minHeap.size() + maxHeap.size();
        if ((length & 1) == 0) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else {
            return minHeap.peek();
        }
    }
}