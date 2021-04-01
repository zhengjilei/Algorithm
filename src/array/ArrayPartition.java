package array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * created by Ethan-Walker on 2018/12/22
 */
public class ArrayPartition {

    /**
     * 给定一个数组a，和数值key.  key 可能不在数组a中
     * 要求将数组划分成左中右三部分（左边小于 key,中间等于 key，右边大于key）
     *
     * @param a
     * @param key
     */
    public void partition(int[] a, int key) {

        int index = 0, smallIndex = 0, bigIndex = a.length - 1;
        while (index <= bigIndex) { //必须有等于号，假设是 < 号，arr = [19, 8] key=13， index++,smallIndex++，跳出循环，得到的结果错误
            if (a[index] < key) {
                if (index != smallIndex) {
                    swap(a, index, smallIndex);
                }
                index++;
                smallIndex++;
            } else if (a[index] == key) {
                index++;
            } else {
                swap(a, index, bigIndex--);
                // 注意，这里 index 没有++, 因为只能保证bigIndex 位置的元素确定
                // index 位置交换过来的数可能仍然大于 key
            }
        }
    }

    public void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    //9, 13, 19, 6, 8, 4, 18, 5, 17, 18, 6, 8, 18, 2, 8
    // 19
    // [9, 13, 6, 8, 4, 18, 5, 17, 18, 6, 8, 18, 2, 19, 8]
    @Test
    public void test() {


        for (int h = 0; h < 10000; h++) {
            Random random = new Random();
            int n = random.nextInt(50) + 10;
            int key = random.nextInt(50) + 10;
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = random.nextInt(50) + 10;
            }
            int[] b = Arrays.copyOf(arr, arr.length);
            partition(arr, key);
            if (!judge(arr, key)) {
                System.out.println(Arrays.toString(b));
                System.out.println("key=" + key);
                System.out.println(Arrays.toString(arr));
                System.out.println("---------");
            }
        }

    }

    /**
     * 检验划分结果是否正确
     *
     * @param arr
     * @param key
     * @return
     */
    public boolean judge(int[] arr, int key) {
        int equalIndex = -1, bigIndex = -1;
        int i = 0;
        for (i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                equalIndex = i;
                break;
            } else if (arr[i] > key) {
                bigIndex = i;
                break;
            }
        }
        if (i == arr.length) return true;
        if (equalIndex != -1) {
            while (equalIndex < arr.length && arr[equalIndex] == key) {
                equalIndex++;
            }
            if (equalIndex == arr.length) return true;
            else bigIndex = equalIndex;
        }
        // bigIndex 一定不等于-1
        while (bigIndex != -1 && bigIndex < arr.length && arr[bigIndex] > key) {
            bigIndex++;
        }
        if (bigIndex != arr.length) return false;
        return true;
    }

    @Test
    public void testA() {

        int[] a = new int[]{9, 13, 19, 6, 8, 4, 18, 5, 17, 18, 6, 8, 18, 2, 8};
        int key = 19;
        partition(a, key);
        System.out.println(Arrays.toString(a));

    }

}
