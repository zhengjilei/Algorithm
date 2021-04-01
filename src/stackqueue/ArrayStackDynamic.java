package stackqueue;

import java.util.Arrays;

/**
 * 动态顺序栈：支持扩容
 * created by Ethan-Walker on 2019/1/15
 */
public class ArrayStackDynamic {
    int[] nums;
    int size;
    int count;

    public ArrayStackDynamic(int size) {
        this.size = size;
        this.nums = new int[size];
        this.count = 0;
    }

    public void push(int val) {
        if (count == size) {
            // 数组已满，扩容
            nums = Arrays.copyOf(nums, size *= 2);
        }
        nums[count++] = val;
    }

    public int pop() {
        if (isEmpty()) throw new RuntimeException("stack is empty");
        return nums[--count];
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
