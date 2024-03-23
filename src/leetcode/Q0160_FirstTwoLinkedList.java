package leetcode;

/**
 * 找到两个单链表相交的起始节点。
 * 不考虑出现环
 * created by Ethan-Walker on 2018/12/23
 */
public class Q0160_FirstTwoLinkedList {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        if (headA == headB) return headA;

        int lengthA = getLength(headA);
        int lengthB = getLength(headB);

        ListNode fast = headA, slow = headB;
        if (lengthB > lengthA) {
            fast = headB;
            slow = headA;
        }
        int step = Math.abs(lengthA - lengthB);
        while (step > 0) {
            fast = fast.next;
            step--;
        }
        while (fast != null && slow != null) {
            if (fast == slow) {  // 必须放在前面判断， 考虑链表 A:2->3  B:3 的情况，在3点相交
                return fast;
            }
            fast = fast.next;
            slow = slow.next;

        }
        return null;


    }

    private int getLength(ListNode head) {
        int count = 0;
        while (head != null) {
            head = head.next;
            count++;
        }
        return count;
    }
}
