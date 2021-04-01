package stackqueue;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/3/6
 */
public class MArrayDeque {
    int[] items;
    int head;
    int tail;

    int count;
    private static final int DEFAULT_SIZE = 20;

    public MArrayDeque() {
        items = new int[DEFAULT_SIZE];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    public void offerLast(int val) {
        if (count == items.length) {
            resize();
        }
        items[tail] = val;
        tail = (tail + 1) % items.length;
        count++;
    }

    public void offerFirst(int val) {
        if (count == items.length) {
            resize();
        }
        head = (head + items.length - 1) % items.length;// 当 head==0 时，+ items.length 不会出错
        items[head] = val;
        count++;
    }

    public int pollLast() {
        if (count == 0)
            throw new RuntimeException("队列为空");
        tail = (tail + items.length - 1) % items.length; // 当 tail ==0 时，+ items.length 不会出错
        int res = items[tail];
        count--;
        return res;
    }

    public int pollFirst() {
        if (count == 0)
            throw new RuntimeException("队列为空");
        int res = items[head];
        head = (head + 1) % items.length;
        count--;
        return res;
    }

    public int peekFirst() {
        if (count == 0)
            throw new RuntimeException("队列为空");
        return items[head];
    }

    public int peekLast() {
        if (count == 0)
            throw new RuntimeException("队列为空");
        return items[(tail + items.length - 1) % items.length]; // 注意这里：tail+items.length1-1
    }

    public void resize() {
        int[] newArray = new int[items.length * 2];
        System.arraycopy(items, head, newArray, 0, count - head);
        System.arraycopy(items, 0, newArray, count - head, tail);
        items = newArray;
        head = 0;
        tail = count;
    }


    public boolean isEmpty() {
        return count == 0;
    }

    public void offer(int val) {
        offerLast(val);
    }

    public int poll() {
        return pollFirst();
    }


    public void push(int val) {
        offerFirst(val);
    }

    public int pop() {
        return pollFirst();
    }


    @Test
    public void test() {
        MArrayDeque deque = new MArrayDeque();
        for (int i = 0; i < 20; i++) {
            deque.offerLast(i);
        }
        for (int j = 10; j < 15; j++) {
            deque.offerFirst(j);
        }
        System.out.println(deque.pollLast());
        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());

        System.out.println(deque.pollLast());
        while (!deque.isEmpty()) {
            System.out.print(deque.pollFirst() + " , ");
        }
    }

    @Test
    public void test2() {
        System.out.println(-1 % 10);
    }

}
