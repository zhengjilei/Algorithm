package stackqueue;

/**
 * 静态顺序栈
 * <p>
 * created by Ethan-Walker on 2019/1/15
 */

public class ArrayStack {

    int[] nums;
    int count;
    int size;

    public ArrayStack(int size) {
        this.size = size;
        nums = new int[size];
        count = 0;
    }

    public boolean push(int val) {
        if (count == size) return false;
        nums[count++] = val;

        return true;
    }

    public int pop() {
        if (isEmpty()) throw new RuntimeException("stack is null");
        return nums[--count];
    }

    public boolean isEmpty() {
        return count == 0;
    }

}
