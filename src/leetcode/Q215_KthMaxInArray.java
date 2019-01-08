package leetcode;

import org.junit.Test;

/**
 * 第 k 大，逆序划分
 * created by Ethan-Walker on 2019/1/8
 */
public class Q215_KthMaxInArray {

    public int findKthLargest(int[] nums, int k) {
        return kthMax(nums, 0, nums.length - 1, k);
    }

    int kthMax(int[] nums, int l, int r, int k) {
        if (l > r) return -1;
        int pivotIndex = partition(nums, l, r);

        int leftSize = pivotIndex - l;
        int rightSize = r - pivotIndex;

        if (leftSize + 1 == k) {
            return nums[pivotIndex];
        } else if (leftSize + 1 < k) {
            // 在右半部分，右半部分找第
            return kthMax(nums, pivotIndex + 1, r, k - (leftSize + 1));
        } else {
            // 在左半部分找
            return kthMax(nums, l, pivotIndex - 1, k);
        }
    }

    /**
     * 划分，返回pivot 的位置
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    int partition(int[] nums, int l, int r) {
        int pivotIndex = (int) (Math.random() * (r - l + 1) + l);
        int pivot = nums[pivotIndex]; // 要在交换前

        swap(nums, pivotIndex, r);

        int i = l, j = r;
        while (i < j) {
            while (i < j && nums[i] >= pivot) i++;
            while (i < j && nums[j] <= pivot) j--;
            if (i < j) {
                swap(nums, i, j);
            }
        }
        // i 指向 pivot 应该落在的位置上
        swap(nums, i, r);
        return i;
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    @Test
    public void test() {
        int[] a = {3, 2, 1, 5, 6, 4};
        int k1 = 2;

        int[] b = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 6;

        int[] c = {2, 1};
        int k3 = 2;
        System.out.println(findKthLargest(a, k1));
        System.out.println(findKthLargest(b, k2));
        System.out.println(findKthLargest(c, k3));
    }
}
