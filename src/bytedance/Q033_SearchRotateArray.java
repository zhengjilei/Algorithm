package bytedance;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/2
 */
public class Q033_SearchRotateArray {


    /**
     * 二分搜索法的关键在于获得了中间数后，判断下面要搜索左半段还是右半段，
     * 如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，
     * 则左半段是有序的，我们只要在有序的半段里用首尾两个数组来判断目标值
     * 是否在这一区域内，这样就可以确定保留哪半边了
     *
     * 数组中无重复数字
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < nums[right]) {  // 注意：这里是和 nums[right] 比较
                //[mid, right] 是有序的, [left,mid-1] 是无序的
                if (nums[mid] < target && target <= nums[right]) { // target 在有序部分
                    left = mid + 1;
                } else {             // target 在无序部分
                    right = mid - 1;
                }
            } else {
                // [left,mid] 是有序的, [mid+1, right] 是无序的
                if (target >= nums[left] && target < nums[mid]) { // target 在有序部分
                    // 注意这里用 num[mid],不能用num[mid-1] , 当 mid=0 的时候mid-1 会溢出
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;

    }


    @Test
    public void test() {
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(search(new int[]{3, 1}, 0));
        System.out.println(search(new int[]{1, 3}, 0));
        System.out.println(search(new int[]{1, 3}, 1));
        System.out.println(search(new int[]{3, 1}, 3));
        System.out.println(search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 8));
        System.out.println(search(new int[]{3}, 1));
        System.out.println(search(new int[]{3}, 4));
        System.out.println(search(new int[]{0,0,1,2,2,5,6}, 0));

    }
}
