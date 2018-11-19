package heap;

import java.util.Arrays;

/**
 * created by Ethan-Walker on 2018/11/6
 */
public class MinHeap {

    private int[] heap;
    private int currentSize;

    public MinHeap(int[] heap, int currentSize) {
        this.heap = heap;
        this.currentSize = currentSize;
        buildMinHeap(heap, 0, currentSize);
    }

    // 最小堆排序， 从大到小排序
    public void minHeapSort() {

        buildMinHeap(heap, 0, currentSize);

        int start = 0;
        int end = currentSize - 1;
        while (end > 0) {

            swap(heap, start, end);

            end--;
            // 重新调整
            siftDown(0, end);
        }
    }

    private void buildMinHeap(int[] a, int start, int length) {
        int position = length / 2 - 1;
        while (position >= 0) {
            siftDown(position, length - 1);
            position--;
        }
    }

    /**
     * 下沉调整最小子堆
     *
     * @param startIndex 调整开始索引
     * @param endIndex   最后一个节点索引
     */
    private void siftDown(int startIndex, int endIndex) {
        int i = startIndex;
        int j = i * 2 + 1; // i 节点的左子节点

        while (j <= endIndex) {
            if (j < endIndex && heap[j + 1] < heap[j]) j++; // 如果j<endIndex 说明有右子节点，让j指向 左右子节点中较小的一位
            if (heap[i] <= heap[j]) break; // i 节点比子节点都小
            else {
                swap(heap, i, j);
                i = j;
                j = i * 2 + 1;  // 继续下沉调整
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 删除堆顶元素
     * 最小堆，删除堆顶最小值
     * 返回新堆的堆顶元素值
     */
    public int removeTop() {
        if (currentSize == 0) return -1;
        int val = heap[0];
        heap[0] = heap[currentSize - 1];  // 覆盖堆顶最小值
        currentSize--;          //堆长度减小
        siftDown(0, currentSize - 1);
        return val;
    }

    /**
     * 插入一个新元素到堆中，调整堆
     *
     * @param value
     * @return
     */
    public int insertValue(int value) {
        if (currentSize == heap.length) { // 堆已满
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
        currentSize++;
        heap[currentSize - 1] = value;
        siftUp(currentSize - 1);
        return heap[0];
    }

    public int getMin() {
        if (currentSize > 0) return heap[0];
        else return -1;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * 上浮调整
     *
     * @param startIndex
     */
    private void siftUp(int startIndex) {
        int j = startIndex;
        int i = (j - 1) / 2;  // 父节点
        while (j > 0) {
            // j = 0说明已经调整结束或者不需要调整
            // 不是 j>=0 i>=0 否则(i-1)/2 始终等于0 死循环
            if (heap[j] < heap[i]) {
                swap(heap, i, j);
                j = i;
                i = (j - 1) >> 1;
            } else {
                break;
            }
        }
    }

    public void printHeap() {
        for (int i = 0; i < currentSize; i++) {
            System.out.printf("%5d", heap[i]);
        }
        System.out.println();
    }

}
