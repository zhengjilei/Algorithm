package 程序员代码面试指南;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * created by Ethan-Walker on 2018/12/18
 */
public class Q007_MaxInWindow {


    /**
     * @param array 数组
     * @param size  窗口长度
     * @return
     */
    public int[] getMaxInWindow(int[] array, int size) {

        if (array == null || size > array.length) return null;
        if (array.length == 0) return new int[0];

        int resultLength = array.length - size + 1;
        int[] result = new int[resultLength];
        int resultIndex = 0;

        ArrayDeque<Integer> deque = new ArrayDeque<>();// 双端队列，存放当前窗口中的最大值索引

        for (int index = 0; index < array.length; index++) {
            // 弹出不可能是窗口中最大的元素
            while (!deque.isEmpty() && array[index] >= array[deque.peekLast()]) { //等于可取可不取
                deque.pollLast();
            }
            deque.offerLast(index);

            // 判断窗口中最大值是否需要弹出
            if (index - deque.peekFirst() == size) {
                deque.pollFirst();
            }
            // 窗口中满 size 个元素时，需要获取最大值
            if (index >= size - 1) {
                result[resultIndex++] = array[deque.peekFirst()];

            }
        }
        return result;
    }

    //[1,3,-1,-3,5,3,6,7]
    //3
    //Output
    //[3,3,5,5,6,0]
    //Expected
    //[3,3,5,5,6,7]

    @Test
    public void test() {
        int[] result = getMaxInWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(Arrays.toString(result));

    }
}
