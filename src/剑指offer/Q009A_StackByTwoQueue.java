package 剑指offer;

/**
 * 两个队列实现栈
 * created by Ethan-Walker on 2018/12/2
 */
public class Q009A_StackByTwoQueue<T> {

    MQueue<T> queue1;
    MQueue<T> queue2;


    public Q009A_StackByTwoQueue() {
        queue1 = new MQueue<>();
        queue2 = new MQueue<>();
    }

    public void push(T t) {
        MQueue<T> q;
        if (!queue2.isEmpty()) {
            q = queue2;
        } else {
            q = queue1;
        }
        boolean r = q.offer(t);
        if (!r) System.out.println("其中某个队列已满，" + t + "无法压入栈");
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return null;
        }
        MQueue<T> p, q; // p 指向非空队列， q 指向空队列
        if (!queue1.isEmpty()) {
            p = queue1;
            q = queue2;
        } else {
            p = queue2;
            q = queue1;
        }
        T t = null;

        // 将非空队列的前 n-1 个元素退出并加入到空队列中
        // 最后一个元素弹出返回
        while (!p.isEmpty()) {
            t = p.poll();
            if (p.isEmpty()) {
                return t;
            } else {
                q.offer(t);
            }
        }
        return t;
    }

    public boolean isEmpty() {
        if (queue1.isEmpty() && queue2.isEmpty()) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Q009A_StackByTwoQueue<String> stack = new Q009A_StackByTwoQueue<>();
        stack.push("ac21");
        stack.push("ac31");
        stack.push("ac41");
        System.out.println(stack.pop());
        stack.push("ac51");
        stack.push("ac61");
        System.out.println(stack.pop());
        stack.push("ac71");
        stack.push("ac81");
        stack.push("ac91");

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    class MQueue<T> {

        int maxSize;
        T[] items;
        final int DEFAULT_SIZE = 10;
        int count;

        int head, tail;  //  tail 为待插入元素位置，head 为堆首元素位置

        public MQueue() {
            this.maxSize = DEFAULT_SIZE;
            items = (T[]) new Object[maxSize];
            this.count = 0;
            head = 0;
            tail = 0;
        }

        public boolean offer(T t) {
            if (count < maxSize) {
                items[tail] = t;
                tail = (tail + 1) % maxSize;
                count++;
                return true;
            }
            return false;
        }

        public T poll() {
            if (count == 0) {
                return null;
            }
            T t = items[head];
            head = (head + 1) % maxSize;
            count--;
            return t;
        }

        public boolean isEmpty() {
            return count == 0;
        }
    }
}
