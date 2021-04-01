package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/2/3
 */
public class Q047_Permutation {

    // 方法一: 递归
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        recur3(nums, 0, nums.length - 1, res);
        return res;
    }

    public void recur3(int[] a, int start, int end, List<List<Integer>> res) {
        if (start == end) {
            List<Integer> list = new ArrayList<>();
            for (int v : a) {
                list.add(v);
            }
            res.add(list);
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            if (set.contains(a[i])) {
                // 已经交换过到 a[left] 的元素
                continue;
            }
            set.add(a[i]); // 记录已经交换到a[left] 的元素
            swap(a, i, start);
            recur3(a, start + 1, end, res); // 递归排列
            swap(a, i, start);

        }
    }


    public void swap(int[] nums, int left, int right) {
        int v = nums[left];
        nums[left] = nums[right];
        nums[right] = v;
    }


    //方法二: 非递归
    public List<List<Integer>> permuteUnique2(int[] a) {
        Arrays.sort(a);
        List<List<Integer>> res = new ArrayList<>();
        if (a == null || a.length == 0) return res;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) list.add(a[i]);
        res.add(list);

        int j ;
        while (true) {
            j = a.length - 2;
            while (j >= 0 && a[j] >= a[j + 1]) j--;
            // 从右往左找到第一个a[j]< a[j+1]
            if (j == -1) { // 说明当前a[] 顺序为降序, 全排列结束
                break;
            }
            // 从[j+1,) 往后找大于 a[j]的最小值，与a[j] 交换
            int minIndex = j + 1;
            for (int k = minIndex + 1; k < a.length; k++) {
                if (a[k] > a[j] && a[k] <= a[minIndex]) {
                    // = 必须要取，保证目标值有多个时，与 a[j] 交换的是最后一个值,使得交换后[j+1, ) 之后的数字仍然保持降序
                    minIndex = k;
                }
            }
            swap(a, minIndex, j);

            // [end,之后的所有元素是逆序，反转即可
            reverse(a, j + 1, a.length - 1);
            list = new ArrayList<>();
            for (int i = 0; i < a.length; i++) list.add(a[i]);
            res.add(list);
        }
        return res;
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    @Test
    public void test() {
        System.out.println(permuteUnique2(new int[]{1,2,3,4}));
    }

}
