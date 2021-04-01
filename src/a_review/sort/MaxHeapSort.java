package a_review.sort;

/**
 * created by Ethan-Walker on 2019/2/20
 */
public class MaxHeapSort {
    public void maxHeapSort(int[] nums) {
        int count = nums.length;
        buildMaxHeap(nums, count);

        while (count > 1) {
            swap(nums, count - 1, 0);
            count--;
            siftDown(nums, 0, count - 1);
        }
    }

    public void buildMaxHeap(int[] heap, int count) {
        int start = count - 2 >> 1;
        while (start >= 0) {
            siftDown(heap, start, count - 1);
            start--;
        }
    }

    private void siftDown(int[] heap, int start, int end) {
        int i = start, j = (i << 1) + 1;
        while (j <= end) {
            if (j < end && heap[j] < heap[j + 1]) j++;
            if (heap[i] < heap[j]) {
                swap(heap, i, j);
                i = j;
                j = (j << 1) + 1;
            } else {
                break;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
