package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * created by Ethan-Walker on 2019/2/3
 */
public class Q128_LongestConsecutive {
    public int longestConsecutive(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int max = 1, temp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1); // value 是arr[i] 所在连续序列的长度
                if (map.containsKey(arr[i] - 1)) {
                    // 合并 arr[i]-1 arr[i]
                    temp = merge(map, arr[i] - 1, arr[i]);
                    if (temp > max) {
                        max = temp;
                    }
                }
                if (map.containsKey(arr[i] + 1)) {
                    temp = merge(map, arr[i], arr[i] + 1);
                    if (temp > max) {
                        max = temp;
                    }
                }
            }
        }
        return max;
    }

    /**
     * 合并 small 、big 所在连续序列，只要更新该序列的两端数值在 map 中的数据
     *
     * @param map
     * @param small
     * @param big
     * @return
     */
    public int merge(Map<Integer, Integer> map, int small, int big) {
        small = small - map.get(small) + 1; // 较小值
        big = big + map.get(big) - 1; //  较大值

        int newLen = big - small + 1;
        map.put(small, newLen);
        map.put(big, newLen);
        return newLen;
    }

}