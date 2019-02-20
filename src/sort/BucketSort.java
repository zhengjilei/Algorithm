package sort;

import org.junit.Test;

import java.util.Random;

/**
 * created by Ethan-Walker on 2019/2/20
 */
public class BucketSort {


    //n个[0~2000]之间的整数，将它们从小到大排序
    public void bucketSort(int[] nums) {
        int[] bucket = new int[2001];
        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i]]++;
        }

        int resultIndex = 0;
        for (int i = 0; i <= 2000; i++) {
            while (bucket[i] > 0) {
                nums[resultIndex++] = i;
                bucket[i]--;
            }
        }
    }

    @Test
    public void test() {

        for (int k = 0; k < 10000; k++) {
            Random random = new Random();
            int length = random.nextInt(2000);
            int[] a = new int[length];

            for (int i = 0; i < length; i++) {
                a[i] = random.nextInt(2000);
            }
            bucketSort(a);
            boolean judge = SortJudge.judge(a);
            if (!judge) {
                System.out.println(judge);
            }
        }
    }
}
