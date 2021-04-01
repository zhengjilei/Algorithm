package a_review.sort2;

import org.junit.Test;
import sort.SortJudge;

import java.util.Random;

/**
 * created by Ethan-Walker on 2019/3/11
 */
public class Sort {

    public void bubbleSort(int[] nums) {
        boolean change = true;
        for (int i = 0; i < nums.length - 1; i++) {
            change = false;
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    change = true;
                }
            }
            if (!change) {
                break;
            }
        }

    }


    public void selectSort(int[] nums) {
        int minIndex;
        for (int i = 0; i < nums.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
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
        int insert, j;
        for (int i = 1; i < nums.length; i++) {
            // nums[i] 为插入值
            insert = nums[i];
            for (j = i - 1; j >= 0 && insert < nums[j]; j--) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = insert;
        }
    }

    public void binaryInsertSort(int[] nums) {
        int insert, j, l, r, mid;
        for (int i = 1; i < nums.length; i++) {
            insert = nums[i];
            l = 0;
            r = i - 1;
            while (l <= r) {
                mid = l + r >> 1;
                if (nums[mid] <= insert) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }

            // l 指向第一个比 insert 大的数
            for (j = i - 1; j >= l; j--) {
                nums[j + 1] = nums[j];
            }
            nums[l] = insert;
        }
    }

    public void shellSort(int[] nums) {
        int insert, i, j, k, gap = nums.length >> 1;

        while (gap != 0) {
            for (i = 0; i < gap; i++) {              // 总共分成 gap 个序列，每个序列中的元素位置相隔 gap 步
                // nums[i] 为 其中当前序列的起始位置
                for (j = i + gap; j < nums.length; j += gap) {
                    insert = nums[j];
                    for (k = j - gap; k >= i && insert < nums[k]; k -= gap) {
                        nums[k + gap] = nums[k];
                    }
                    nums[k + gap] = insert;
                }
            }
            gap >>= 1;
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
//            bubbleSort(a);
//            selectSort(a);
//            insertSort(a);
            shellSort(a);
//            binaryInsertSort(a);
            boolean judge = SortJudge.judge(a);
            if (!judge) {
                System.out.println(judge);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

}
