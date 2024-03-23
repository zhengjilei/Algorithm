package leetcode;

/**
 * created by Ethan-Walker on 2019/3/6
 */
public class Q0622_CircleDeque {

    int[] items;
    int head;
    int tail;
    int count;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public Q0622_CircleDeque(int k) {
        this.items = new int[k];
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (count == items.length) {
            return false;
        }
        items[tail] = value;
        tail = (tail + 1) % items.length;
        count++;
        return true;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (count == 0)
            return false;
        head = (head + 1) % items.length;
        count--;
        return true;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (count == 0) return -1;
        return items[head];
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        if (count == 0) return -1;
        return items[(tail + items.length - 1) % items.length];
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return count == items.length;
    }
}
