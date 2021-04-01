package 剑指offer;

/**
 * 给定一个排序整数数组（每个元素都是唯一的），找出数组中任意一个下标值等于元素本身的数
 * created by Ethan-Walker on 2018/12/14
 */
public class Q053B_GetIndexEqualValue {

    /**
     * 二分查找法
     * 元素大于下标，往左查找
     * 元素小于小标，往右查找
     *
     * @param array
     * @return
     */
    public int getIndexEqualValue(int[] array) {
        int left = 0, right = array.length - 1, mid = 0;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (array[mid] == mid) {
                return mid;
            } else if (array[mid] < mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
