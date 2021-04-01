package bytedance;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/2
 */
public class Q081_SearchRotateArray {

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;

        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < nums[right]) {  // 注意：这里是和 nums[right] 比较
                //[mid, right] 是有序的, [left,mid-1] 是无序的
                if (nums[mid] < target && target <= nums[right]) { // target 在有序部分
                    left = mid + 1;
                } else {             // target 在无序部分
                    right = mid - 1;
                }
            } else if (nums[mid] == nums[right]) {
                // 相等,只能排除 nums[right]
                right--;

            } else {
                // [left,mid] 是有序的, [mid+1, right] 是无序的
                if (target >= nums[left] && target < nums[mid]) { // target 在有序部分
                    // 注意这里用 num[mid],不能用num[mid-1] , 当只有一个数的时候mid-1 会溢出
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return false;

    }

    @Test
    public void test() {
        System.out.println(search(new int[]{3, 1, 1}, 3));
        System.out.println(search(new int[]{1, 3, 1, 1, 1}, 3));
    }
}
