package leetcode2;

import leetcode.ListNode;

import java.util.PriorityQueue;

/**
 * created by Ethan-Walker on 2019/3/14
 */
public class Q0023 {
    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                minHeap.add(lists[i]);
            }
        }
        if (minHeap.isEmpty()) {
            return null;
        }
        ListNode head;
        ListNode node = minHeap.poll();
        head = node;
        ListNode tail = head;
        node = node.next;

        if (node != null) {
            minHeap.add(node);
        }
        while (!minHeap.isEmpty()) {
            node = minHeap.poll();
            tail.next = node;
            tail = node;

            node = node.next;
            if (node != null) {
                minHeap.add(node);
            }
        }

        tail.next = null;
        return head;

    }
}