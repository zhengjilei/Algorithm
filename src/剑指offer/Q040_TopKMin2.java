package 剑指offer;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 最小的 k 个数
 * <p>
 * 时间复杂度: O(nlogk)
 * 空间复杂度: O(k)
 * <p>
 * created by Ethan-Walker on 2018/12/9
 */
public class Q040_TopKMin2 {


    public ArrayList<Integer> getTopKMinNum(int[] array, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array == null || array.length == 0 || k <= 0 || k > array.length) return list;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1); // 从大到小的顺序
        for (int i = 0; i < array.length; i++) {
            maxHeap.offer(array[i]);
            if (maxHeap.size() > k) {
                maxHeap.poll();// 弹出最大的元素，保留剩余的 k 个最小数
            }
        }
        return new ArrayList<>(maxHeap);

    }
}