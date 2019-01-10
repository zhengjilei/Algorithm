package 程序员代码面试指南;

import java.util.HashMap;

/**
 * created by Ethan-Walker on 2019/1/9
 */
public class Q070_LongConsecutive {


    /**
     * map key 为数组中遍历到的值 ，value 是该值所在最长序列的长度
     *
     * @param arr
     * @return
     */
    public int longestConsecutive(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 1, len = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
                if (map.containsKey(arr[i] - 1)) {
                    // 合并 arr[i] 和 arr[i]-1 成一个序列
                    max = Math.max(max, merge(map, arr[i] - 1, arr[i]));
                }
                if (map.containsKey(arr[i] + 1)) {
                    max = Math.max(max, merge(map, arr[i], arr[i] + 1));
                }
            }
        }
        return max;
    }

    /**
     * 合并 small  和 big 所在的序列
     * big = small + 1
     * <p>
     * 更新新序列的两端在 map 中value值 = 新序列的长度
     *
     * @param map
     * @param small
     * @param big
     * @return
     */
    public int merge(HashMap<Integer, Integer> map, int small, int big) {
        int left = small - map.get(small) + 1; // 新序列最小值
        int right = big + map.get(big) - 1; // 新序列最大值
        int len = right - left + 1;
        map.put(left, len);
        map.put(right, len);

        return len;
    }

}
