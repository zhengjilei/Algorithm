package leetcode;

/**
 * created by Ethan-Walker on 2019/1/14
 */
public class Q0189_Rotate {
    void swap(int[] chs, int i, int j) {
        int c = chs[i];
        chs[i] = chs[j];
        chs[j] = c;
    }

    // 空间复杂度:O(1) 时间复杂度:O(n)
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k <= 0 || k == nums.length) return;

        if (k > nums.length) {
            k %= nums.length;
        }
        // 先整体逆序，再将前左右半区各自逆序
        for (int i = 0; i < nums.length / 2; i++) {
            swap(nums, i, nums.length - i - 1);
        }

        // 逆转左半区
        int leftLen = k;
        for (int i = 0; i < leftLen / 2; i++) {
            swap(nums, i, leftLen - i - 1);
        }

        // 逆转右半区, leftLen  为右半区起点
        int rightLen = nums.length - leftLen;
        int limit = leftLen + rightLen / 2;
        int sum = nums.length - 1 + leftLen;
        for (int i = leftLen; i < limit; i++) {
            swap(nums, i, sum - i);
        }
    }

    // 思路：思考一个两倍长度数组，数组中元素是之前的两次复制
    // 从 位置 [len- k, len-k +len-1] 是目标元素，复制到原数组中
    // 实际上只需要一倍长度的结果数组即可
    // 时间复杂度:O(n) 空间复杂度: O(n)

    public void rotate2(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k <= 0 || k == nums.length) return;

        if (k > nums.length) {
            k %= nums.length;
        }
        int[] res = new int[nums.length];
        int resIndex = 0;
        for (int i = nums.length - k; i < nums.length; i++) {
            res[resIndex++] = nums[i];
        }
        for (int i = 0; i < nums.length - k; i++) {
            res[resIndex++] = nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = res[i];
        }
    }
}
