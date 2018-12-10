package 剑指offer;

import java.util.PriorityQueue;

/**
 * 求数据流中的中位数
 * created by Ethan-Walker on 2018/12/10
 */
public class Q041_DynamicArray {

    private PriorityQueue<Integer> maxHeap; // 最大堆，左边的数
    private PriorityQueue<Integer> minHeap; // 最小堆，右边的数

    public int getMedian() {
        if ((getSize() & 1) == 0) {// 偶数
            return (maxHeap.peek() + minHeap.peek()) / 2;
        } else {
            return minHeap.peek(); // 奇数时，中位数在最小堆中
        }
    }


    /**
     * 插入一个数
     *
     * @param value
     */
    public void insert(int value) {
        if ((getSize() & 1) == 0) { // 偶数，插入最小堆

            if (maxHeap.size() > 0 && value < maxHeap.peek()) {
                maxHeap.offer(value);
                minHeap.offer(maxHeap.poll());
            } else {
                minHeap.offer(value);
            }
        } else { // 奇数，插入最大堆
            if (minHeap.size() > 0 && value > minHeap.peek()) {
                minHeap.offer(value);
                maxHeap.offer(minHeap.poll());
            } else {
                maxHeap.offer(value);
            }
        }
    }


    public int getSize() {
        return maxHeap.size() + minHeap.size();
    }
}
