package 剑指offer;

import java.util.ArrayDeque;

/**
 * 实现一个能 O(1)时间内求得队列中的最大值
 * <p>
 * 方法二: 两个双端队列，dataDeque 和 maxDeque
 * created by Ethan-Walker on 2018/12/15
 */
public class Q059_MaxQueue {


    ArrayDeque<InternalData> dataDeque = new ArrayDeque<>();
    ArrayDeque<InternalData> maxDeque = new ArrayDeque<>();

    int currentIndex = 0; // 每个数值插入时，要绑定一个索引
    // 这样做的目的是，从队列弹出一个元素时，判断该元素绑定的索引和 最大队列队首是否相同，相同说明最大队列队首元素也要弹出


    public void offer(int val) {
        InternalData data = new InternalData(currentIndex, val);

        //保证maxDeque 队头始终是最大值， 插入时从最大队列末尾删除掉不可能成为最大值的元素
        while (!maxDeque.isEmpty() && val >= maxDeque.peekLast().val) {
            maxDeque.pollLast();
        }
        maxDeque.offerLast(data);
        dataDeque.offerLast(data);

        ++currentIndex;
    }

    public int poll() {
        if (dataDeque.isEmpty()) {
            throw new RuntimeException("队列为空");
        }

        if (dataDeque.peekFirst().index == maxDeque.peekFirst().index) {
            maxDeque.pollFirst();
        }
        return dataDeque.pollFirst().val;
    }

    public int getMax() {
        if (maxDeque.isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return maxDeque.peekFirst().val;
    }


    class InternalData {
        int index;
        int val;

        InternalData(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

}
