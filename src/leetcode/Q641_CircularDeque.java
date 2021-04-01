package leetcode;

/**
 * created by Ethan-Walker on 2019/3/17
 */
public class Q641_CircularDeque {
    int[] vals;
    int head;
    int tail;
    int count;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public Q641_CircularDeque(int k) {
        this.vals = new int[k];
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (count == vals.length) return false;
        head = (head + vals.length - 1) % vals.length;
        vals[head] = value;
        count++;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (count == vals.length) return false;
        vals[tail] = value;
        tail = (tail + 1) % vals.length;
        count++;

        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (count == 0) return false;
        head = (head + 1) % vals.length;
        count--;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (count == 0) return false;
        tail = (tail + vals.length - 1) % vals.length;
        count--;
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (count == 0) return -1;
        return vals[head];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (count == 0) return -1;
        int index = (tail + vals.length - 1) % vals.length;
        return vals[index];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return count == vals.length;
    }
}
