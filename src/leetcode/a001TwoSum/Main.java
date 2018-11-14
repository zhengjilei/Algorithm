package leetcode.a001TwoSum;

import edu.princeton.cs.algs4.StdRandom;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class Main {

    public static void main(String[] args) {
/*        Random random = new Random();
        int n = random.nextInt(100);
        int target = random.nextInt(2000);

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = random.nextInt(1000);
        }
        int[] results = twoSum(nums, target);
        System.out.println(Arrays.toString(results));*/
        testTwoSumCount2();
    }

    /**
     * 解决思路: 用hashMap<key,value> key 为当前遍历元素 a 的所需值 target-a, value 为当前元素的索引位置
     * 每遍历到一个元素，检查其是否包含在 hashMap 的key 中，如果包括说明该元素是之前某个元素的所需要的值，相加=target
     * 时间复杂度 O(n)
     *
     * @param nums
     * @param target
     * @return int[2] 返回两个元素的索引
     */
    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> hashMap = new HashMap<>();
        int[] results = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (hashMap.containsKey(cur)) {
                results[0] = hashMap.get(cur);
                results[1] = i;
                return results;
            } else {
                hashMap.put(target - cur, i);
            }
        }
        return results;
    }

    /**
     * 数组中一共有多少组元素之和等于 target （当 target=4时， 1 1 3 表示有两对）
     * 假设没有重复的元素
     * 排序 + 二分查找
     */
    public static int twoSumCount(int[] nums, int target) {
        Arrays.sort(nums);
        int length = nums.length;
        int cnt = 0;
        for (int i = 0; i < length; i++) {
            int index = Arrays.binarySearch(nums, target - nums[i]);
            if (index > i) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 数组中一共有多少组元素之和等于 target （当 target=4时， 1 1 3 表示有两对）
     * 排序 + 二分查找
     * 考虑重复的元素
     */
    public static int twoSumCount2(int[] nums, int target) {
        Arrays.sort(nums);
        int length = nums.length;
        int cnt = 0;
        for (int i = 0; i < length; i++) {
            int index = Arrays.binarySearch(nums, target - nums[i]);
            if (index > i) {
                cnt++;
                int left = index - 1;
                int right = index + 1;
                while (left > i && nums[left] == nums[index]) {
                    left--;
                    cnt++;
                }
                while (right < length && nums[right] == nums[index]) {
                    right++;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void testTwoSumCount2() {
        Random random = new Random();
        int n = StdRandom.uniform(20,30);
        int target = 11;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = random.nextInt(10);
        }
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int t = twoSumCount2(nums, target);
        System.out.println(t);

    }
}