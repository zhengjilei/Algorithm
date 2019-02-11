package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/2/3
 */
public class Q060_KthPermutation {

    // 方法一: 全排列
    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        int count = 1;

        if (k == count) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(nums[i]);
            }
            return sb.toString();
        }
        int j = 0, minIndex = -1;

        while (true) {
            j = n - 2;
            while (j >= 0 && nums[j] >= nums[j + 1]) j--;
            if (j == -1) break;
            minIndex = j + 1;
            for (int t = minIndex + 1; t < n; t++) {
                if (nums[t] > nums[j] && nums[t] <= nums[minIndex]) {
                    minIndex = t;
                }
            }
            swap(nums, j, minIndex);
            reverse(nums, j + 1, nums.length - 1);

            count++;
            if (count == k) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    sb.append(nums[i]);
                }
                return sb.toString();
            }
        }
        return "";
    }

    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j] - (nums[j] = nums[i]);
    }

    // 方法二: 以每个数开头的数量是 n!/n ，根据k退出结果的开头字母，再继续推接下来的字母
    public String getPermutation2(int n, int k) {
        List<Integer> nums = new ArrayList<>();

        int ave = 1;
        for (int i = 0; i < n - 1; i++) {
            nums.add(i + 1);
            ave *= (i + 1);
        }
        nums.add(n);
        StringBuilder sb = new StringBuilder();
        recur(nums, nums.size(), 0, ave, sb, k - 1);
        return sb.toString();
    }

    // k 表示在 剩余位置[index,...] 的全排列的第 k 个( k 是下标，从 0 开始)
    // ave 表示以每个数开始的排列个数
    // 确定 index 位置的数
    public void recur(List<Integer> nums, int n, int index, int ave, StringBuilder sb, int k) {

        // 选到 index 位置的数的 下标
        int i = k / ave;        // 选中num[i] 到 sb[index]
        sb.append(nums.get(i));
        if (index == n - 1) {
            return;
        }
        nums.remove(i);
        k = k % ave;

        int jie = n - index - 1;
        ave /= jie;
        recur(nums, n, index + 1, ave, sb, k);

    }

    @Test
    public void test1() {
        System.out.println(-1 / 1);
    }

    @Test
    public void test() {
        System.out.println(getPermutation2(2, 1));
        System.out.println(getPermutation2(2, 2));
        System.out.println("---------------------");

        System.out.println(getPermutation2(3, 1));
        System.out.println(getPermutation2(3, 2));
        System.out.println(getPermutation2(3, 3));
        System.out.println(getPermutation2(3, 4));
        System.out.println(getPermutation2(3, 5));
        System.out.println(getPermutation2(3, 6));
        System.out.println("---------------------");
        System.out.println(getPermutation2(4, 1));
        System.out.println(getPermutation2(4, 2));
        System.out.println(getPermutation2(4, 3));
        System.out.println(getPermutation2(4, 4));
        System.out.println(getPermutation2(4, 5));
        System.out.println(getPermutation2(4, 6));
        System.out.println(getPermutation2(4, 7));
        System.out.println(getPermutation2(4, 8));
        System.out.println(getPermutation2(4, 9));
        System.out.println(getPermutation2(4, 10));
        System.out.println(getPermutation2(4, 11));
        System.out.println(getPermutation2(4, 12));
        System.out.println(getPermutation2(4, 13));
        System.out.println(getPermutation2(4, 14));
        System.out.println(getPermutation2(4, 15));
        System.out.println(getPermutation2(4, 16));
        System.out.println(getPermutation2(4, 17));
        System.out.println(getPermutation2(4, 18));
        System.out.println(getPermutation2(4, 19));
        System.out.println(getPermutation2(4, 20));
        System.out.println(getPermutation2(4, 21));
        System.out.println(getPermutation2(4, 22));
        System.out.println(getPermutation2(4, 23));
        System.out.println(getPermutation2(4, 24));
    }
}
