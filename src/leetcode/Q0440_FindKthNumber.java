package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * created by Ethan-Walker on 2019/2/27
 */
public class Q0440_FindKthNumber {
    // 超时
    public int findKthNumber(int n, int k) {
        PriorityQueue<String> maxHeap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        for (int i = 1; i <= k; i++) {
            maxHeap.add(i + "");
        }
        String s;
        for (int i = k + 1; i <= n; i++) {
            s = i + "";
            if (s.compareTo(maxHeap.peek()) < 0) {
                maxHeap.poll();
                maxHeap.add(s);
            }
        }

        return Integer.valueOf(maxHeap.poll());
    }

    public int findKthNumber2(int n, int k) {
        return 0;

    }
}
