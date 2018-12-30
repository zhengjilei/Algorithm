package 腾讯面试.循环buffer;

/**
 * https://www.cnblogs.com/DarrenChan/p/9535557.html
 * Created by Ethan-Walker on 2018/4/21.
 * 实现简单的循环Buffer
 * 用定长数组实现，队列结构，先入先出
 */
public class RingBuffer<T> {
    private int head;       // head 作为队列头，作为新插入元素的位置
    private int tail;       // tail 队列尾，作为最早插入的元素位置，删除元素时， 先删除 tail 所在位置的元素
    private int maxSize;
    private int nowCount;   // 当前队列中元素个数
    private T[] buffer;     // 循环 buffer

    public RingBuffer(int maxSize) {
        head = tail = 0; // 空元素队列 ，head=0 表示下次插入的元素 放入位置为 0
        nowCount = 0;
        this.maxSize = maxSize;
        buffer = (T[]) new Object[maxSize];
    }

    // 插入一个数据
    public void add(T data) {
        if (nowCount == maxSize) {
            // 溢出，覆盖/丢弃
            //这里选择覆盖，将 队尾元素丢弃
            remove();
            if (head == maxSize) {
                head = 0;
            }
            buffer[head++] = data;
            nowCount++;
        } else {
            if (head == maxSize) {
                head = 0;
            }
            buffer[head++] = data;
            nowCount++;
        }

    }


    /**
     * 删除并返回队尾元素
     *
     * @return
     */
    public T remove() {

        if (isEmpty()) {
            return null;
        }
        if (tail == maxSize - 1) {
            // 数组最大索引处
            tail = 0;
            nowCount--;
            return buffer[maxSize - 1];

        } else {
            nowCount--;
            return buffer[tail++];
        }

    }

    public T getTail() {
        return buffer[tail];
    }

    public T getHead() {
        if (head == 0) {
            return buffer[maxSize - 1];
        }
        return buffer[head - 1];
    }
    public T[] getAll(){
        if(isEmpty()){
            return (T[])(new Object[0]);
        }else{
            T[] results = (T[])new Object[nowCount];
            int n = 0;
            if(tail<head){
                // 正常顺序
                for(int i=tail;i<head;i++){
                    results[n++] = buffer[i];
                }
            }else{
                // 先从 tail -> maxSize
                for(int i=tail;i<maxSize;i++){
                    results[n++]=buffer[i];
                }
                // 再从 0 -> head-1
                for(int i=0;i<head;i++){
                    results[n++] =buffer[i];
                }
            }
            return results;
        }
    }
    public boolean isEmpty() {
        if (nowCount == 0) {
            return true;
        } else return false;
    }
}
