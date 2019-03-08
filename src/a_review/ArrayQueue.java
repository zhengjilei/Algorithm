package a_review;

/**
 * 可扩展队列
 * created by Ethan-Walker on 2019/3/5
 */
public class ArrayQueue {
    int[] item;
    int head;
    int tail; // head==tail 可能为空，可能为满
    int count;

    public ArrayQueue(int maxCount) {
        this.item = new int[maxCount];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    boolean offer(int val) {
        if (count == item.length) {
            resize();
        }
        item[tail] = val;
        tail = (tail + 1) % item.length;
        count++;
        return true;
    }

    // 扩展到两倍大
    private void resize() {
        int newLen = item.length * 2;
        int[] newArray = new int[newLen];
        System.arraycopy(item, head, newArray, 0, count - head);
        System.arraycopy(item, 0, newArray, count - head, tail);
        item = newArray;
        head = 0;
        tail = count;
    }

    public int poll() {
        if (count == 0) {
            throw new RuntimeException("队列为空");
        }
        int res = item[head];
        head = (head + 1) % item.length;
        count--;
        return res;
    }

    int size() {
        return count;
    }

    public int peek() {
        if (count == 0) {
            throw new RuntimeException("队列为空");
        }
        return item[head];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(20);
        for (int i = 0; i < 21; i++) {
            queue.offer(i);
        }
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        for (int i = 22; i < 30; i++) {
            queue.offer(i);
        }
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + ", ");
        }
        System.out.println();
    }
}
