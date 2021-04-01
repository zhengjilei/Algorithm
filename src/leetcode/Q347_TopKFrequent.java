package leetcode;

import java.util.*;

/**
 * created by Ethan-Walker on 2019/2/27
 */
public class Q347_TopKFrequent {
    public List<Integer> topKFrequent(int[] nums, int k) {
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int t : nums) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            maxHeap.add(entry);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(maxHeap.poll().getKey());
        }
        return res;
    }
}
