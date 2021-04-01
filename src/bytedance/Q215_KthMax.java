package bytedance;


import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/3
 */
public class Q215_KthMax {

    // 方法一: 堆 O(nlogk)

    // 创建一个k个元素的最小堆，堆顶为最小值
    // 每访问一个元素num，如果num 比堆顶大，替换堆顶最小值（堆顶最小值一定不是第k大）
    public int findKthLargest(int[] nums, int k) {
        if (k > nums.length || k <= 0) return -1;
        buildHeap(nums, k); //

        for (int i = k; i < nums.length; i++) {
            if (nums[i] > nums[0]) {
                nums[0] = nums[i];
                siftDown(nums, 0, k);
            }
        }
        return nums[0];

    }

    // nums[0...k-1] 元素构建最小堆
    public void buildHeap(int[] nums, int k) {
        int startIndex = (k - 2) >> 1; // 注意k是长度， k-1 为末尾元素下标
        while (startIndex >= 0) {
            siftDown(nums, startIndex, k);
            startIndex--;
        }
    }

    // 从位置index向下调整
    public void siftDown(int[] nums, int index, int k) {
        int i = index, j = 2 * i + 1;
        while (j < k) {
            if (j < k - 1 && nums[j + 1] < nums[j]) j++;
            if (nums[j] < nums[i]) {
                swap(nums, i, j);
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


    @Test
    public void test() {
        System.out.println(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    // 方法二：划分 O(n), 从大到小
    public int findKthLargest2(int[] nums, int k) {
        if (k > nums.length || k <= 0) return -1;
        return findKthLargest2(nums, k, 0, nums.length - 1);
    }

    public int findKthLargest2(int[] nums, int k, int left, int right) {

        if (left > right) return -1;

        int pivotIndex = partition(nums, left, right);
        int t = pivotIndex - left + 1; // [left,pivotIndex] 总数
        if (t == k) {
            return nums[pivotIndex];
        } else if (t < k) {
            // 在右半部分,
            return findKthLargest2(nums, k - t, pivotIndex + 1, right);
        } else {
            // 在左半部分
            return findKthLargest2(nums, k, left, pivotIndex - 1);
        }

    }

    public int partition(int[] nums, int left, int right) {

        int pivotValue = median3(nums, left, right);
        int i = left, j = right; // 划分，左大右小
        while (i < j) {
            while (i < j && nums[i] >= pivotValue) i++;
            while (i < j && nums[j] <= pivotValue) j--;
            if (i < j) {
                swap(nums, i, j);
            }
        }
        // i 指向比 pivot 小的数
        swap(nums, i, right);
        return i; // 返回pivotIndex
    }

    // 从nums[start..end] 中选出一个数作为pivot
    public int median3(int[] nums, int start, int end) {
        int minIndex = start, mid = (start + end) >> 1;
        if (nums[mid] < nums[minIndex]) minIndex = mid;
        if (nums[end] < nums[minIndex]) minIndex = end;
        if (minIndex != start) swap(nums, minIndex, start);

        if (mid != end && nums[mid] < nums[end]) {
            swap(nums, mid, end); // pivot 交换到末尾
        }
        return nums[end];
    }

    @Test
    public void test2() {
        System.out.println(findKthLargest2(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(findKthLargest2(new int[]{3, 2, 1, 5, 6, 4}, 6));
        System.out.println(findKthLargest2(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        System.out.println(findKthLargest2(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 1));

    }

}
