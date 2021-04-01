package heap;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ethan-Walker on 2018/3/26.
 * 从小到大排序，构造最大堆, 每次选出最大值，放在数组末尾
 * 从大到小排序 ，构造最小堆
 */
public class HeapSortTest {

    public static void main(String[] args) {
        Random random = new Random();
        int currentSize = random.nextInt(20)+30;
        int[] t = new int[currentSize];

        for (int i = 0; i < currentSize; i++) {
            t[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(t));

        MinHeap heap = new MinHeap(t, currentSize);
        heap.printHeap();
        heap.minHeapSort();
        heap.printHeap();
    }

}
