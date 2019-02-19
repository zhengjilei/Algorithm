package leetcode;

/**
 * 排序的数组的旋转数组中搜索最小值
 * 考虑数组有重复值
 * created by Ethan-Walker on 2019/2/19
 */
public class Q154_SearchInRatate {

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1, mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }else if(nums[mid]< nums[right]){
                right = mid;
            }else{
                // nums[mid]==nums[right]
                // 可以将 nums[right]剔除，即使 nums[right]是最小值，中间还有一个等值的 nums[mid]
                right--;
            }
        }

        // 跳出循环时，left ==right
        return nums[left];

    }
}
