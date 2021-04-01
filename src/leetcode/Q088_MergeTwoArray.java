package leetcode;

/**
 * created by Ethan-Walker on 2019/2/20
 */
public class Q088_MergeTwoArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int resultIndex = m + n - 1;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            if (nums1[m] < nums2[n]) {
                nums1[resultIndex--] = nums1[m--];
            } else {
                nums1[resultIndex--] = nums2[n--];
            }
        }
        while (n >= 0) {
            nums1[resultIndex--] = nums2[n--];
        }
    }
}
