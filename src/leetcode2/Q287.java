package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/15
 */
public class Q287 {

    public int findDuplicate(int[] nums) {
        int count = 0;
        int left = 1, right = nums.length - 1; // 数组长度是 n+1
        int mid;
        while (left <= right) {
            mid = ((right - left) >> 1) + left;

            // 寻找数组中在 [left,mid] 之间的数 总数
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] >= left && nums[i] <= mid) {
                    count++;
                }
            }
            if (left == right) {
                if (count > 1) {
                    return left;
                } else {
                    break;
                }
            }
            if (count > mid - left + 1) {
                right = mid;
            } else {
                // count <= mid - left + 1
                left = mid + 1;
            }
            count = 0;
        }

        return -1;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     *
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        int tmp;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1) { // 第 i 位应该存放 i+1 的数
                // 试图将 nums[i] 交换到正确的位置 nums[i]- 1
                if (nums[i] == nums[nums[i] - 1]) {
                    return nums[i]; // nums[i] 就是重复元素
                } else {
                    swap(nums, i, nums[i] - 1);
                }
            }
        }
        return -1;

    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    public int findDuplicate3(int[] nums) {
        int left = 1, right = nums.length - 1;
        int mid;
        int count;
        while (left <= right) {
            mid = (right - left >> 1) + left;
            count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] >= left && nums[i] <= mid) {
                    count++;
                }
            }
            if (left == right) {
                if (count > 1) return left;
                break;
            }
            if (count > mid - left + 1) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
