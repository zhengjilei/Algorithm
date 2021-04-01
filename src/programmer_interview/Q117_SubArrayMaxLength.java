package programmer_interview;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 未排序数组中，累加和等于给定值k的最长子数组长度
 * 数组中可能有负数
 * created by Ethan-Walker on 2018/12/27
 */
public class Q117_SubArrayMaxLength {

    /**
     * 最简单的方法，计算出 n(n+1)/2 个数组的和
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(1)
     *
     * @param arr
     * @param k
     * @return
     */
    public int maxLengthSimple(int[] arr, int k) {
        int maxLength = 0, sum;

        for (int left = 0; left < arr.length; left++) {
            sum = 0;
            for (int right = left; right < arr.length; right++) {
                //a[left,right] 作为子树组
                sum += arr[right];
                if (sum == k && right - left + 1 > maxLength) {
                    maxLength = sum;
                }
            }
        }
        return maxLength;
    }


    /**
     * s[i] : 从 a[0] ~a[j]a[j+1] ~ a[i] 的和 0=< j <=i
     * s[j]: a[0]~a[j] 的和
     * 子数组 a[j+1]~a[i] 的和 : s[i]-s[j]
     * <p>
     * 假设 s[i] = sum , s[j] = sum-k 则 a[j+1]~a[j] 的和等于 k
     * 故每计算一个s[i]，都判断 s[i] - k 是否在 map 中
     * <p>
     * map 的key 为 s[i] 值，value 为对应的 sum=s[i] 值第一次出现的子数组结束位置 i
     *
     * @param arr
     * @param k
     * @return
     */
    public int maxLength(int[] arr, int k) {

        int sum = 0, index = 0;
        HashMap<Integer, Integer> sumIndexMap = new HashMap<>();
        sumIndexMap.put(0, -1); // 重要，处理从位置 0 开始 累加和等于 k 的子数组 的情况
        int maxLength = 0;
        int len = 0;
        for (; index < arr.length; index++) {
            sum += arr[index];          // 计算 s[i]： 从 a[0]~a[i] 的和
            if (sumIndexMap.containsKey(sum - k)) { // 如果存在 s[i] -k : 说明 s[j+1] ~s[i]的和等于 k
                len = index - sumIndexMap.get(sum - k);
                if (len > maxLength) {
                    maxLength = len;
                }
            }
            sumIndexMap.putIfAbsent(sum, index); // 不存在才放
        }
        return maxLength;
    }


    /**
     * 正数和负数个数相同的最大子数组长度
     * 将正数 -> 1 负数-> -1  k=0   （why 变成 1 -1，为的是正数和负数相同的子数组 和始终相等）
     *
     * @return
     */
    public int maxLength2(int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);

        for (int i = 0; i < copy.length; i++) {
            if (copy[i] > 0) copy[i] = 1;
            else if (copy[i] < 0) copy[i] = -1;
        }
        return maxLength(copy, 0);

    }

    /**
     * 数组中只有数字 0 和 1
     * 求数组0 1 个数相同的最大子数组长度
     * 0 -> -1
     *
     * @param arr
     * @return
     */
    public int maxLength3(int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < copy.length; i++) {
            if (copy[i] == 0) copy[i] = -1;
        }

        return maxLength(copy, 0);
    }

}
