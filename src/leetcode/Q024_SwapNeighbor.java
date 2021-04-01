package leetcode;

/**
 * created by Ethan-Walker on 2019/3/14
 */
public class Q024_SwapNeighbor {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode first = head, second;
        ListNode next;
        ListNode prevTail = null;
        while (first != null && first.next != null) {
            second = first.next;
            next = second.next;
            if (prevTail != null) {
                prevTail.next = second;
            } else {
                head = second;
            }
            // 反转
            second.next = first;
            first.next = next;

            prevTail = first; // 设置尾节点

            first = next;

        }
        return head;
    }
}
