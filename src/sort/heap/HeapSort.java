package sort.heap;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ethan-Walker on 2018/3/26.
 * 从小到大排序，构造最大堆, 每次选出最大值，放在数组末尾
 * 从大到小排序 ，构造最小堆
 */
public class HeapSort {
    public static int[] heap;
    private static int heapLength;

    public static void main(String[] args) {
        Random random = new Random();
        heapLength = random.nextInt(100);

        System.out.println(heapLength);
        heap = new int[heapLength];

        for (int i = 0; i < heapLength; i++) {
            heap[i] = random.nextInt(200);
        }
        System.out.println(Arrays.toString(heap));
        System.out.println();
        minHeapSort();
        System.out.println(Arrays.toString(heap));
    }

    // 最小堆排序， 从大到小排序
    public static void minHeapSort() {

        buildMinHeap(heap, 0, heapLength);

        int start = 0;
        int end = heapLength - 1;
        buildMinHeap(heap, 0, end);
        do {
            swap(0, end);         // 最小值交换到堆末尾
            siftDown(0, --end);   // 只需要调整根节点即可
        } while (end >= 1);
    }

    public static void buildMinHeap(int[] a, int start, int length) {
        int position = (length - 2) >> 1;
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
    private static void siftDown(int startIndex, int endIndex) {
        int i = startIndex;
        int j = (i << 1) + 1; // i 节点的左子节点
        while (j <= endIndex) {
            if (j < endIndex && heap[j + 1] < heap[j]) j++; // 如果 j 不是最后一个节点 且 i 的右子节点比左子节点要小， 则j +1
            if (heap[i] <= heap[j]) break; // i 节点比子节点都小
            else {
                swap(i, j);
                i = j;
                j = (i << 1) + 1;  // 继续下沉调整
            }
        }
    }

    /**
     * 删除堆顶元素
     * 最小堆，删除堆顶最小值
     * 返回新堆的堆顶元素值
     */
    private static void removeTop() {
        if (heapLength == 0) return;
        if (heapLength == 1) {
            heapLength--;
            return;
        }

        // 交换堆中最后一个元素 和堆顶元素
        heap[0] = heap[0] + heap[heapLength - 1] - (heap[heapLength - 1] = heap[0]);
        heapLength--;
        siftDown(0, heapLength - 1);

    }

    /**
     * 插入一个新元素到堆中，调整堆
     *
     * @param value
     * @return
     */
    private static int insertValue(int value) {
        if (heapLength == heap.length) {
            heap = new int[heapLength + 1];
        }
        heapLength++;
        heap[heapLength - 1] = value;
        siftUp(heapLength - 1);
        return heap[0];
    }

    /**
     * 上浮调整
     *
     * @param startIndex
     */
    private static void siftUp(int startIndex) {
        int j = startIndex;
        int i = (j - 1) >> 1;
        while (i >= 0) {
            if (heap[j] < heap[i]) {
                heap[j] = heap[i] + heap[j] - (heap[i] = heap[j]);
                j = i;
                i = (j - 1) >> 1;
            } else {
                break;
            }
        }

    }

    public static void swap(int i, int j) {
        int a = heap[i];
        heap[i] = heap[j];
        heap[j] = a;
    }
}
