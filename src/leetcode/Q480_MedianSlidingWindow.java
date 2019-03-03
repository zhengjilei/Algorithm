package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * created by Ethan-Walker on 2019/3/2
 */
public class Q480_MedianSlidingWindow {

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) return new double[0];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1); // 注意这里不能使 o2-o1 否则可能会导致溢出 出现错误
            }
        });
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        double[] res = new double[nums.length - k + 1];
        int resultIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxHeap.size() == minHeap.size()) {
                // 压入一个值到 minHeap 中
                if (!maxHeap.isEmpty() && nums[i] < maxHeap.peek()) {
                    maxHeap.add(nums[i]);
                    minHeap.add(maxHeap.poll());
                } else {
                    minHeap.add(nums[i]);
                }
            } else { // maxHeap = minHeap - 1
                // 压入一个值到 maxHeap 中
                if (!minHeap.isEmpty() && nums[i] > minHeap.peek()) {
                    minHeap.add(nums[i]);
                    maxHeap.add(minHeap.poll());
                } else {
                    maxHeap.add(nums[i]);
                }
            }


            if (i >= k - 1) {
                // 获取窗口中的中位数
                if ((k & 1) == 0) { // 窗口中一共有偶数位
                    long rightMin = minHeap.peek();// long 接受，防止  rightMin+leftMax  超出int 范围
                    long leftMax = maxHeap.peek();

                    res[resultIndex++] = (rightMin + leftMax) / 2.0;

                    // 弹出位置为 i-k+1 的数， 使得最大堆、最小堆总数处于 k-1 的状态, 下一次循环就可以直接插入了

                    if (!maxHeap.remove(nums[i - k + 1])) {  // 尝试在 maxHeap 中删，返回是否删除成功
                        minHeap.remove(nums[i - k + 1]);
                        // 在 minHeap 中删除之后导致  maxHeap: i , minHeap:i-1
                        minHeap.add(maxHeap.poll()); // 使得 minHeap 中的元素始终 >= maxHeap
                    }

                } else { // 奇数个 ,maxHeap: i ，minHeap:i+1
                    res[resultIndex++] = minHeap.peek();
                    if (!minHeap.remove(nums[i - k + 1])) { // 尝试在 minHeap 中删，返回是否删除成功
                        maxHeap.remove(nums[i - k + 1]);
                        // 在maxHeap 删除之后 导致 maxHeap:i-1 minHeap:i+1
                        maxHeap.add(minHeap.poll()); //使得 maxHeap:i minHeap:i
                    }
                }
            }

        }
        return res;


    }


    @Test
    public void test() {
        double[] doubles = medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(Arrays.toString(doubles));

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
    }
}
