package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * created by Ethan-Walker on 2019/1/13
 */
public class Q136_UniqueNum {

    /**
     * 方法一：hash
     * 时间复杂度:O(n)
     * 空间复杂度:O(n)
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> e : entries) {
            if (e.getValue() == 1) return e.getKey();
        }
        return -1;
    }

    /**
     * 方法二: 排序（堆排序）
     * 时间复杂度: O(nlogn)
     * 空间复杂度: O(1)
     *
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        // 参见 programmer_interview Q079
        return 0;
    }

    // 要求时间复杂度:O(n) 空间复杂度: O(1)
    // 充分利用条件：除了某个元素只出现一次以外，其余每个元素均出现两次。

    /**
     *
     * 方法三：异或
     * a^a=0
     * a^0=a
     * a^b^c^b^c = a
     *
     * @param nums
     * @return
     */
    public int singleNumber3(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }




    @Test
    public void test() {
        int a = -321, b = -12131;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
    }
}
