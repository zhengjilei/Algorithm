package a_review.sort;

import org.junit.Test;
import sort.SortJudge;

import java.util.Random;

/**
 * created by Ethan-Walker on 2019/2/20
 */
public class MaxHeapSort {

    public void maxHeapSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.buildMaxHeap(nums);
        maxHeap.maxHeapSort();
    }


    @Test
    public void test() {

        for (int k = 0; k < 10000; k++) {
            Random random = new Random();
            int length = random.nextInt(2000);
            int[] a = new int[length];

            for (int i = 0; i < length; i++) {
                a[i] = random.nextInt(2000);
            }
            maxHeapSort(a);
            boolean judge = SortJudge.judge(a);
            if (!judge) {
                System.out.println(judge);
            }
        }
    }
}

class MaxHeap {
    int[] heap;
    int count;

    public void buildMaxHeap(int[] nums) {
        heap = nums;
        count = nums.length;
        int changePos = nums.length - 2 >> 1;

        while (changePos >= 0) {
            siftDown(heap, changePos, nums.length - 1);
            changePos--;
        }
    }

    public void siftDown(int[] heap, int changePos, int endPos) {
        int i = changePos, j = (i << 1) + 1;
        while (j <= endPos) {
            if (j < endPos && heap[j + 1] > heap[j]) j++;
            if (heap[j] > heap[i]) {
                swap(heap, i, j);
                i = j;
                j = (i << 1) + 1;
            } else {
                break;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    public int getMax() {
        if (!isEmpty()) {
            return heap[0];
        }
        throw new RuntimeException("堆已空");
    }


    public int pollMax() {
        if (!isEmpty()) {
            int res = heap[0];
            heap[0] = heap[--count];
            siftDown(heap, 0, count - 1);
            return res;
        }
        throw new RuntimeException("堆已空");
    }

    public boolean insert(int val) {
        if (count == heap.length) return false;
        heap[count] = val;
        siftUp(heap, count++);
        return true;
    }

    public void siftUp(int[] heap, int index) {
        int j = index, i = index - 1 >> 1;
        while (i >= 0) {
            if (heap[j] > heap[i]) {
                swap(heap, i, j);
                j = i;
                i = i - 1 >> 1;
            }
        }
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public void maxHeapSort() {
        while (count > 1) {
            swap(heap, count - 1, 0);
            count--;
            siftDown(heap, 0, count - 1);
        }
    }

}
