package 剑指offer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 递增排序的数组，找到两数之和等于 key
 * <p>
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 * (乘积最小，两数的差值越大乘积就越小，从两端开始找，找到的第一对就是乘积最小的)
 * created by Ethan-Walker on 2018/12/14
 */
public class Q057_TwoSum {


    /**
     * hash 表
     * hash[target] = sourceIndex
     * 每当遇到一个元素，检查该元素是否在key中
     * 不在，执行上述操作:index 下标对应的数值 a，存入 hash[key-a] = index
     * 在则找到目标对
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     * 缺点：没有利用好递增排序这一条件，不能保证找到的是乘积最小的
     *
     * @param array
     * @param key
     * @return
     */
    public List<Integer> getTwoSum(int[] array, int key) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array == null || array.length < 2) return list;
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            if (hashMap.containsKey(array[i])) {
                list.add(array[hashMap.get(array[i])]);
                list.add(array[i]);
                return list;
            } else {
                hashMap.put(key - array[i], i);
            }
        }
        return list;
    }

    public List<Integer> getTwoSumBySortedArray(int[] array, int key) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array == null || array.length < 2) return list;
        int left = 0, right = array.length - 1, sum = 0;

        while (left < right) {
            sum = array[left] + array[right];
            if (sum < key) {
                left++;
            } else if (sum == key) {
                list.add(array[left]);
                list.add(array[right]);
                return list;
            } else {
                right--;
            }
        }
        return list;
    }

    @Test
    public void test() {
        int[] a = new int[]{1, 2, 4, 7, 11, 15};
        int key = 15;
        System.out.println(getTwoSum(a, key));
        System.out.println(getTwoSumBySortedArray(a, key));
    }
}
