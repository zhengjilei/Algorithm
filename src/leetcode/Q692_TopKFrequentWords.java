package leetcode;

import java.util.*;

/**
 * created by Ethan-Walker on 2019/2/27
 */
public class Q692_TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0 || k <= 0 || k > words.length) return res;

        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>((o1, o2) -> (
                o1.getValue() == o2.getValue() ? o1.getKey().compareTo(o2.getKey()) : o2.getValue() - o1.getValue())
        );

        HashMap<String, Integer> map = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            maxHeap.add(entry);
        }

        for (int i = 0; i < k; i++) {
            res.add(maxHeap.poll().getKey());
        }

        return res;

    }
}
