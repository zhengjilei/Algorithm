package sort;

import java.util.Random;

/**
 * created by Ethan-Walker on 2019/3/16
 */
public class Shuffle {

    public static void shuffle(int[] nums) {
        int j, tmp;
        Random r = new Random();
        for (int i = 0, len = nums.length; i < len; i++) {
            j = r.nextInt(i + 1); // 生成 [0,i]之间随机下标
            if (j != i) { // 交换
                tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
    }
}
