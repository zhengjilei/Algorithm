package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/15
 */
public class Q0442_DupNUms {

    /**
     * 遍历到位置 i ，对应的数 nums[i], 其应该在数组中的位置是 nums[i]-1 ， 设置 nums[nums[i]- 1] = 其相反数，表示num[i]-1 该位置应该对应的数出现过
     * 如果发现 nums[ nums[i]-1] <0 , 表示位置为 nums[i] -1 应该放置的的数已经出现过， 即数 nums[i]-1+1  已经存在过
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null) return res;
        int m;
        for (int i = 0; i < nums.length; i++) {
            m = Math.abs(nums[i]); // nums[i] 可能是负数，是负数表明位置为 i 应该放置的值 i+1 前面已经出现过，故 nums[i] 被设置成了 -nums[i]
            if (nums[m - 1] > 0) { // 说明 i位置 对应的数 nums[i] 即m，应该放置在位置 m-1 上，该位置上还是正数，故还没有出现过 m 这个数
                nums[m - 1] *= -1; // 设置成相反数
            } else {
                // <=0, 说明 m-1 位置上的数被设置成了 相反数，故数 m 出现过
                res.add(m);
            }
        }
        return res;
    }


    @Test
    public void test() {
        int[] ints = {3, 1, 2, 5, 4, 2, 3};
        System.out.println(findDuplicates(ints));
    }
}
