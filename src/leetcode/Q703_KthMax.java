package leetcode;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/8
 */
public class Q703_KthMax {


    class KthLargest {

        int[] minHeap; // 小顶堆中 k 个元素，为数据流中的最大的 k 个数
        int nowCount = 0;

        public KthLargest(int k, int[] nums) {
            minHeap = new int[k];
            for (int i = 0; i < k && i < nums.length; i++) {
                minHeap[i] = nums[i];
            }
            if (nums.length >= k) {
                buildHeap();
                nowCount = k;
                for (int i = k; i < nums.length; i++) {
                    add(nums[i]);
                }
            } else {
                nowCount = nums.length;
            }
        }

        public int add(int val) {
            if (nowCount == minHeap.length) {
                if (val <= minHeap[0]) {
                    return minHeap[0];
                } else {
                    minHeap[0] = val;
                    siftDown(0, minHeap.length - 1);
                    return minHeap[0];
                }
            } else if (nowCount == minHeap.length - 1) {
                minHeap[nowCount++] = val;
                buildHeap();
                return minHeap[0];
            } else {
                minHeap[nowCount++] = val;
                return -1;
            }

        }

        public void buildHeap() {

            int k = minHeap.length;
            int index = (k - 2) >> 1;
            while (index >= 0) {
                siftDown(index, k - 1);
                index--;
            }
        }

        public void siftDown(int start, int end) {
            int i = start, j = 2 * start + 1;

            while (j <= end) {
                if (j < end && minHeap[j + 1] < minHeap[j]) j++;
                if (minHeap[j] >= minHeap[i]) break;
                else {
                    swap(i, j);
                    i = j;
                    j = (i << 1) + 1;   // 括号！！！不能忘记！！！
                }
            }
        }

        public void swap(int i, int j) {
            int t = minHeap[i];
            minHeap[i] = minHeap[j];
            minHeap[j] = t;
        }


    }

    @Test
    public void test() {
        int[] a = {-10, 1, 3, 1, 4, 10, 3, 9, 4, 5, 1};
        int k = 7;

        KthLargest kthLargest = new KthLargest(k, a);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(2));

    }

}
