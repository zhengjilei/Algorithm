package 排列组合;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Permutation {

    int count1 = 0;
    int count2 = 0;
    int count3 = 0;

    /**
     * 求数组 a 从 a[start] 到 a[end] 的全排列
     * 每次选中一个元素放在位置为 end 上，然后递归排列 a[start] a[end-1]
     * <p>
     * 不支持重复元素，不支持字典序
     */
    public void recur(int[] a, int start, int end) {
        if (start == end) {
            count1++;
            System.out.println(Arrays.toString(a));
            return;
        }
        // 将 a[i] 排到末尾，然后对 a[start] ~ a[end-1] 求全排列
        for (int i = start; i <= end; i++) {
            swap(a, i, end);
            recur(a, start, end - 1);
            swap(a, i, end);
        }
    }

    /**
     * <p>
     * 求数组 a 从 a[start] 到 a[end] 的全排列
     * 每次选中一个元素放在位置为 start 上，然后递归排列 a[start+1] a[end]
     * <p>
     * <p>
     * <p>
     * 不支持字典序
     * 不支持重复元素
     */
    public void recur2(int[] a, int start, int end) {
        if (start == end) {
            count2++;
            System.out.println(count2 + ": " + Arrays.toString(a));
            return;
        }
        for (int i = start; i <= end; i++) {
            swap(a, i, start);
            recur2(a, start + 1, end); // 递归排列
            swap(a, i, start);
        }
    }

    // 和 recur2 类似，但能解决重复元素
    public void recur3(int[] a, int start, int end) {
        if (start == end) {
            count3++;
            System.out.println(Arrays.toString(a));
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            if (set.contains(a[i])) {
                // 已经交换过值等于 a[i] 到 a[left] 的元素不再重复交换
                continue;
            }
            set.add(a[i]); // 记录[start...right]中已经交换到a[left] 的元素
            swap(a, i, start);
            recur3(a, start + 1, end);
            swap(a, i, start);

        }
    }


    /**
     * 字典序输出全排列
     * 支持重复元素的字典序
     */
    public List<List<Integer>> lexicalPermutation(int[] a) {
        Arrays.sort(a);
        List<List<Integer>> res = new ArrayList<>();
        if (a == null || a.length == 0) return res;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) list.add(a[i]);
        res.add(list);

        int j;
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

    public void swap(int[] a, int i, int j) {
        a[i] = a[i] + a[j] - (a[j] = a[i]);
    }

    @Test
    public void test3() {
        int[] a = new int[]{1, 2, 3, 4};
        recur(a,0,a.length-1);

    }

    @Test
    public void test2() {
        int[] a = new int[]{1, 1, 2, 2};
        int[] b = Arrays.copyOf(a, a.length);
        int[] c = Arrays.copyOf(a, a.length);
        int[] d = Arrays.copyOf(a, a.length);

        recur(a, 0, a.length - 1);

        System.out.println("count1==" + count1 + "-------------");

        recur2(b, 0, b.length - 1);
        System.out.println("count2==" + count2 + "-------------------------------");
        recur3(c, 0, b.length - 1);
        System.out.println("count3==" + count3 + "-------------------------------");

        lexicalPermutation(d);
    }

    @Test
    public void test() {
        int[] a = new int[]{1, 2, 1, 3};  // 重复元素
        int[] b = Arrays.copyOf(a, a.length);
        int[] c = Arrays.copyOf(a, a.length);
        recur(a, 0, a.length - 1);
        System.out.println("-------------");
        recur2(b, 0, b.length - 1);
        System.out.println("-------------------------------");
        lexicalPermutation(c);
    }

    @Test
    public void test4(){

    }

    /**
     * 字符全排列
     *
     * @param chs
     * @param start
     * @param end
     */
    public void perm(char[] chs, int start, int end) {

        if (start == end) {
            System.out.println(Arrays.toString(chs));
        }
        for (int i = start; i <= end; i++) {
            swap(chs, i, start);
            perm(chs, start + 1, end);
            swap(chs, i, start);
        }

    }

    public void swap(char[] chs, int a, int b) {
        char t = chs[a];
        chs[a] = chs[b];
        chs[b] = t;
    }
}
