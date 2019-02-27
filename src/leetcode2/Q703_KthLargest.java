package leetcode2;

/**
 * created by Ethan-Walker on 2019/2/26
 */
public class Q703_KthLargest {


    int[] heap;
    int count;

    public Q703_KthLargest() {

    }

    public Q703_KthLargest(int k, int[] nums) {
        this.heap = new int[k];
        for (int i = 0; i < k && i < nums.length; i++) {
            heap[i] = nums[i];
            count++;
        }

        buildMinHeap(0, count);
        for (int i = k; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        if (count == heap.length) {
            if (val > heap[0]) {
                heap[0] = val;
                siftDown(0, count - 1);
            }
        } else {
            heap[count++] = val;
            siftUp(count - 1);
        }
        return heap[0];

    }

    public void buildMinHeap(int start, int size) {
        int pos = size - 2 >> 1;
        while (pos >= 0) {
            siftDown(pos, size - 1);
            pos--;
        }
    }

    public void siftUp(int start) {
        int j = start, i = j - 1 >> 1;
        while (j > 0) {
            if (heap[j] < heap[i]) {
                swap(heap, i, j);
                j = i;
                i = i - 1 >> 1;
            } else {
                break;
            }
        }
    }

    private void siftDown(int pos, int end) {
        int i = pos, j = (pos << 1) + 1;
        while (j <= end) {
            if (j < end && heap[j + 1] < heap[j]) j++;
            if (heap[j] < heap[i]) {
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
    //["KthLargest","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add"]
    //[[7,[-10,1,3,1,4,10,3,9,4,5,1]],[3],[2],[3],[1],[2],[4],[5],[5],[6],[7],[7],[8],[2],[3],[1],[1],[1],[10],[11],[5],[6],[2],[4],[7],[8],[5],[6]]

    public static void main(String[] a) {

        Q703_KthLargest m = new Q703_KthLargest(7, new int[]{-10, 1, 3, 1, 4, 10, 3, 9, 4, 5, 1});
        System.out.println(m.add(3));
        System.out.println(m.add(2));
        System.out.println(m.add(3));
        System.out.println(m.add(1));
        System.out.println(m.add(2));
        System.out.println(m.add(4));
        System.out.println(m.add(5));
        System.out.println(m.add(5));
        System.out.println(m.add(6));
        System.out.println(m.add(7));
        System.out.println(m.add(7));
        System.out.println(m.add(8));
        System.out.println(m.add(2));
        System.out.println(m.add(3));
        System.out.println(m.add(1));
        System.out.println(m.add(1));
        System.out.println(m.add(1));
        System.out.println(m.add(10));

    }
}
