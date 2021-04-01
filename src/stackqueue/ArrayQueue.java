package stackqueue;

/**
 * 数组实现循环队列，缺点：受数组长度限制，且不易进行动态调整
 */
public class ArrayQueue<T> {

    T[] items;
    private final int DEFAULT_SIZE = 20;
    private int size = DEFAULT_SIZE;
    private int count;

    int head;  // 第一个元素位置
    int tail;  //待插入元素的位置

    public ArrayQueue() {
        items = (T[]) new Object[size];
    }

    public ArrayQueue(int size) {
        this.size = size;
        items = (T[]) new Object[size];
    }

    public boolean offer(T t) {
        if (count >= size) {
            // 数组已满
            return false;
        }
        items[tail] = t;
        tail = (tail + 1) % size;
        count++;
        return true;

    }

    public T poll() {
        if (!isEmpty()) {
            T t = items[head];
            head = (head + 1) % size;
            count--;
            return t;
        }
        return null;
    }


    public boolean isEmpty() {
        return count == 0;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(30);
        // 加 0~14
        for (int i = 0; i < 15; i++) {
            queue.offer(i);
        }
        // 退出 0~6, 剩余 7~14
        for (int i = 0; i < 7; i++) {
            System.out.printf("%d ", queue.poll());
        }
        System.out.println("\n长度=" + queue.count);

        // 再加 15~34，剩余 7~34
        for (int i = 15; i < 35; i++) {
            queue.offer(i);
        }
        System.out.println("长度=" + queue.count);

        while (!queue.isEmpty()) {
            System.out.printf("%d ", queue.poll());
        }
        System.out.println("\n长度=" + queue.count);

    }
}
