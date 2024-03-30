package leetcode;

import org.junit.Test;

import java.util.*;
import java.util.function.IntFunction;

/**
 * created by Ethan-Walker on 2019/2/27
 */
class EntryNode {
    int frequencyCount;
    int value;

    public EntryNode(int frequencyCount, int value) {
        this.frequencyCount = frequencyCount;
        this.value = value;
    }
}

public class Q0347_TopKFrequent2 {
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<EntryNode> minHeap = new PriorityQueue<>(new Comparator<EntryNode>() {
            @Override
            public int compare(EntryNode o1, EntryNode o2) {
                return o1.frequencyCount - o2.frequencyCount;
            }
        });

        HashMap<Integer, Integer> frequencyCount = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int frequency = frequencyCount.getOrDefault(nums[i], 0);
            frequencyCount.put(nums[i], ++frequency);
        }

        Set<Map.Entry<Integer, Integer>> entrySet = frequencyCount.entrySet();
        int count = 0;

        int[] res = new int[k];

        for (Map.Entry<Integer, Integer> e : entrySet) {
            int value = e.getKey();
            int frequency = e.getValue();

            if (count < k) {
                minHeap.offer(new EntryNode(frequency, value));
            } else {
                if (frequency > minHeap.peek().frequencyCount) {
                    minHeap.poll();
                    minHeap.offer(new EntryNode(frequency, value));
                }
            }
            count++;
        }

        int i = 0;
        while (!minHeap.isEmpty()) {
            res[i++] = minHeap.poll().value;
        }

        return res;
    }

    @Test
    public void test() {
        int[] a = new int[]{2, 3, 6, 7};
        int target = 3;
        System.out.println(topKFrequent(a, target));
        int[] b = new int[]{2, 3, 5};
        int target2 = 2;
        System.out.println(topKFrequent(b, target2));
    }
}
