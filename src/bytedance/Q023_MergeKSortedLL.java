package bytedance;

import 程序员代码面试指南.ListNode;
import org.junit.Test;
import 程序员代码面试指南.ListUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * created by Ethan-Walker on 2019/2/13
 */
public class Q023_MergeKSortedLL {
    // 分析，建立k个元素（每个链表头）的最小堆，优先级队列


    public ListNode mergeKLists(ListNode[] lists) {
        ListNode resHead = null;
        if (lists == null || lists.length == 0) return resHead;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.add(lists[i]);
            }
        }
        if (!pq.isEmpty()) {
            resHead = pq.poll();
            if (resHead.next != null) {
                pq.offer(resHead.next);
            }
            ListNode prev = resHead, temp;
            while (!pq.isEmpty()) {
                temp = pq.poll();
                if (temp.next != null) {
                    pq.offer(temp.next);
                }
                prev.next = temp;
                prev = temp;

            }
            prev.next = null;

        }
        return resHead;
    }

    @Test
    public void test() {

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);

        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);

        ListNode[] lists = new ListNode[3];
        lists[0] = head1;
        lists[1] = head2;
        lists[2] = head3;

        ListNode res = mergeKLists(lists);

        ListUtil.print(res);
    }

}
