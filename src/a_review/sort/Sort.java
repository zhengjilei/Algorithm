package a_review.sort;

import org.junit.Test;
import sort.SortJudge;

import java.util.Random;

/**
 * created by Ethan-Walker on 2019/2/20
 */
public class Sort {

    public void bubbleSort(int[] nums) {
        boolean flag;
        int i, j;
        for (i = 0; i < nums.length - 1; i++) { // 第一次，将最大的数冒泡到最右边
            flag = false;
            for (j = 0; j < nums.length - i - 1; j++) { // 注意这里是 nums.length - i -1
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }

    public void selectSort(int[] nums) {
        int i, j, minIndex;

        for (i = 0; i < nums.length - 1; i++) {
            // 从[i..length-1] 选择最小的数，然后跟 a[i] 交换
            minIndex = i;
            for (j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(nums, i, minIndex);
            }
        }
    }

    public void insertSort(int[] nums) {
        int i, j, insert;
        for (i = 1; i < nums.length; i++) {
            insert = nums[i];
            for (j = i - 1; j >= 0 && nums[j] > nums[j + 1]; j--) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = insert;
        }
    }

    public void binaryInsertSort(int[] nums) {
        int i, j, insert;
        int left, right, mid;
        for (i = 1; i < nums.length; i++) {
            insert = nums[i];
            left = 0;
            right = i - 1;
            while (left <= right) {
                mid = (left + right) >> 1;
                if (insert < nums[mid]) {
                    right = mid - 1;
                } else {
                    // insert=a[mid] 归为这一类，保证稳定排序（相等元素，保证位置不变化）
                    left = mid + 1;
                }
            }
            // 最终插入位置一定是 left
            for (j = i - 1; j >= left; j--) {
                nums[j + 1] = nums[j];
            }
            nums[left] = insert;

        }
    }

    public void shellSort(int[] nums) {
        int i, j, insert, gap = nums.length, group;
        while (gap != 0) {
            gap /= 2;
            // 分成 gap 组
            for (group = 0; group < gap; group++) {
                // 每组从 group 开始
                for (i = group + gap; i < nums.length; i += gap) {
                    insert = nums[i];
                    for (j = i - gap; j >= 0 && insert < nums[j]; j -= gap) {
                        nums[j + gap] = nums[j];
                    }
                    nums[j + gap] = insert;
                }
            }

        }
    }


    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
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
//            bubbleSort(a);
//            selectSort(a);
//            shellSort(a);
            binaryInsertSort(a);
            boolean judge = SortJudge.judge(a);
            if (!judge) {
                System.out.println(judge);
            }
        }
    }
}
